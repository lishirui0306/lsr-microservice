package cn.lsr.user.controller.login;

import cn.lsr.user.entity.user.User;
import cn.lsr.user.service.user.UserService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * = = 登录控制器
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
@Api(tags = "登录控制器")
@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    /**
     * 注入service
     */
    @Autowired
    private UserService userService ;
    /**
     * 注入验证码
     */
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    /**
     * session中的验证码
     */
    private String SHIRO_VERIFY_SESSION = "verifySessionCode";
    /**
     * 验证失败提示
     */
    private String ERROR_PASSWORD = "密码不正确";
    private String ERROR_ACCOUNT = "账户不存在";
    private String ERROR_STATUS = "状态不正常";
    private String ERROR_KAPTCHA = "验证码不正确";
    private String SUCCESS_MSG = "登录成功";

    /**
     * 公用的url头部
     */
    private String prefix = "common/";
    /**
     * 跳转制定页面首页
     * @return
     */
    @GetMapping("/")
    public String index(){
        return prefix+"index";
    }
    @GetMapping("/index")
    public String toindex(){
        return prefix+"index";
    }
    @GetMapping("/login")
    public String login(){
        return prefix+"login";
    }
   /**
    * 功能描述: <br>
    * 〈〉登录认证模块 shiro
    * @Param: [username 用户名, password 密码, captcha 验证码, rememberMe 是否记住我, model]
    * @Return: java.lang.String
    * @Author: Hacker_lsr@126.com
    */
    @PostMapping("/tologin")
    public String login(String username, String password,String captcha, boolean rememberMe, Model model){

        Subject subject = SecurityUtils.getSubject();
        //获取当前登录对象的username subject.getPrincipal().toString()
        User user = userService.selectUserByName(username);
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        // 获取session中的验证码
        String verCode = (String) subject.getSession().getAttribute(SHIRO_VERIFY_SESSION);
        if("".equals(captcha)||(!verCode.equals(captcha))){
            model.addAttribute("msg",ERROR_KAPTCHA);
            return prefix+"login";
        }
        try {
            //主体提交登录请求到SecurityManager
            subject.login(token);
        }catch (IncorrectCredentialsException ice){
            model.addAttribute("msg",ERROR_PASSWORD);
        }catch(UnknownAccountException uae){
            model.addAttribute("msg",ERROR_ACCOUNT);
        }catch(AuthenticationException ae){
            model.addAttribute("msg",ERROR_STATUS);
        }
        if(subject.isAuthenticated()){
            Session session = subject.getSession();
            //这里设置session过期时间
            //session.setTimeout(25000);
            session.setAttribute("user", user);
            return  "redirect:/index";
        }else{
            token.clear();
            return prefix+"login";
        }
    }
    @RequestMapping("/logout")
    public String OutLogin(){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return prefix+"login";
    }
    /**
     * 功能描述: <br>
     * 〈〉 获取验证码
     * @Param: [response, request]
     * @Return: void
     * @Author: Hacker_lsr@126.com
     */
    @GetMapping("/getCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        byte[] verByte = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute(SHIRO_VERIFY_SESSION,createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge,"jpg",jpegOutputStream);
        } catch (IllegalArgumentException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (IOException e){
            e.printStackTrace();
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        verByte = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(verByte);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
    @RequestMapping("/user/lislt")
    public String userList(){
        return "user/user";
    }
    //admin角色才能访问
    @RequestMapping("/admin/a")
    @ResponseBody
    public String admin() {
        return "恭喜你有这个权限";
    }
    //有delete权限才能访问
    @RequestMapping("/edit")
    @ResponseBody
    public String edit() {
        return "edit success";
    }
    //有select权限才能访问
    @RequestMapping("/find")
    @ResponseBody
    public String find() {
        return "error/404";
    }
    @RequestMapping("/toMain")
    public String toMain(){
        return "common/main";
    }
}
