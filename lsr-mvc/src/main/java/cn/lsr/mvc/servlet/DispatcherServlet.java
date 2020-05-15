package cn.lsr.mvc.servlet;

import cn.lsr.mvc.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Class.forName;

/**
 * @Description: 核心控制器
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
@WebServlet(name = "dispatcherServlet",urlPatterns = "/*",loadOnStartup = 1,
initParams = {@WebInitParam(name = "base-package",value = "cn.lsr")})
public class DispatcherServlet extends HttpServlet {
    /**
     * @WebServlet是什么？
     *
     * 其实，以前我们定义一个Servlet，需要在web.xml中去配置，不过在Servlet3.0后出现了基于注解的Servlet。
     *
     * 仔细观察，你会发现，这个DispatcherServlet是自启动，而且传入了一个参数。
     *
     * 要知道，在Spring MVC中，要想基于注解，需要在配置中指明扫描的包路径，就像这个样子：
     *
     * <context:component-scan base-package="cn.lsr">
     *
     * </context:component-scan>
     *
     * 为了方便，我这里就通过初始化参数直接将需要扫描的基包路径传入。
     *
     */
    /**
     * 扫描基包
     */
    private String basePackage="";
    /**
     * 基包下所有的带包路径权限定类名
     */
    private List<String> packageNames = new ArrayList<String>();
    /**
     * 注解实例化，注解上的名称：实例化对象
     */
    private Map<String,Object> instanceMap = new HashMap<String,Object>();
    /**
     * 带包路径的权限定名称：注解上的名称
     */
    private Map<String,String> nameMap = new HashMap<String,String>();
    /**
     * url地址，和方法的映射关系，SpringMVC就是方法的调用链路
     */
    private Map<String, Method> urlMethodMap = new HashMap<String, Method>();
    /**
     * method 和权限定类名的映射关系，主要是通过method找到该方法的对象，通过反射调用执行
     */
    private Map<Method,String> methodPackageMap = new HashMap<Method, String>();

    @Override
    public void init(ServletConfig config)throws ServletException{
        /**
         * 获取注解里面定义的base-package的值
         */
        basePackage = config.getInitParameter("base-package");
        try {
            scanBasePackage(basePackage);
            instance(packageNames);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e){
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }
    }

    /**
     * 扫描基包得到全部的带包路径的权限定名
     * @param basePackage
     */
    public void scanBasePackage(String basePackage){
        /**
         * 要得到基包下面的url路径，需要对basePackage进行转换：将 . 转换为 /
         */
        URL url = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.","/"));
        File basePackageFile = new File(url.getPath());
        System.out.println("scan:"+basePackage);
        File[] childFiles = basePackageFile.listFiles();
        for (File file : childFiles) {
            if (file.isDirectory()){
                //递归扫描
                scanBasePackage(basePackage+"."+file.getName());
            }else if(file.isFile()){
                //类似这种 cn.lsr.xxx.class 去掉class
                packageNames.add(basePackage+"."+file.getName().split("\\.")[0]);
            }
        }
    }

    /**
     * 实例化
     * @param packageNames
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void instance(List<String> packageNames)throws ClassNotFoundException,IllegalAccessException,InstantiationException{
         if (packageNames.size()<1){
             return;
         }
        for (String packageName : packageNames) {
            Class c = Class.forName(packageName);
            if (c.isAnnotationPresent(LSRController.class)){
                LSRController controller = (LSRController) c.getAnnotation(LSRController.class);
                String cName = controller.value();
                instanceMap.put(cName,c.newInstance());
                nameMap.put(packageName,cName);
                System.out.println("LSRController:"+packageName+"vale:"+cName);
            }else if (c.isAnnotationPresent(LSRService.class)){
                LSRService service = (LSRService) c.getAnnotation(LSRService.class);
                String sName = service.value();
                instanceMap.put(sName,c.newInstance());
                nameMap.put(packageName,sName);
                System.out.println("LSRService:"+packageName+"vale:"+sName);
            }else if (c.isAnnotationPresent(LSRRepository.class)){
                LSRRepository repository = (LSRRepository) c.getAnnotation(LSRRepository.class);
                String rName = repository.value();
                instanceMap.put(rName,c.newInstance());
                nameMap.put(packageName,rName);
                System.out.println("LSRRepository:"+packageName+"vale:"+rName);
            }
        }
    }

    /**
     * 依赖注入
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    public void sprinhIOC()throws ClassNotFoundException,IllegalAccessException{
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(LSRQualifier.class)){
                    String name = field.getAnnotation(LSRQualifier.class).value();
                    field.setAccessible(true);
                    field.set(entry.getValue(),instanceMap.get(name));
                }
            }
        }
    }

    /**
     * URL映射处理
     * @throws ClassNotFoundException
     */
    public void handlerUrlMethodMap()throws ClassNotFoundException{
        if (packageNames.size()<1){
            return;
        }
        for (String packageName : packageNames) {
            Class c = Class.forName(packageName);
            if (c.isAnnotationPresent(LSRController.class)){
                Method[] methods = c.getMethods();
                StringBuffer url = new StringBuffer();
                if (c.isAnnotationPresent(LSRRequestMapping.class)){
                    LSRRequestMapping requestMapping = (LSRRequestMapping) c.getAnnotation(LSRRequestMapping.class);
                    url.append(requestMapping.value());
                }
                for (Method method : methods) {
                    if (method.isAnnotationPresent(LSRRequestMapping.class)){
                        LSRRequestMapping requestMapping = method.getAnnotation(LSRRequestMapping.class);
                        url.append(requestMapping.value());
                        urlMethodMap.put(url.toString(),method);
                        methodPackageMap.put(method,packageName);
                    }
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path =url.replaceAll(contextPath,"");
        /**
         * 通过path找到method
         */
        Method method = urlMethodMap.get(path);
        String packageName = methodPackageMap.get(method);
        String controllerName = nameMap.get(packageName);
        
    }
}
