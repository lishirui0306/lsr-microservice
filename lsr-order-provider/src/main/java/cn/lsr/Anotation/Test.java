package cn.lsr.Anotation;

import java.lang.reflect.Method;

public class Test {
    @Mytest(value ="1",age = 100,sorce = {77,88,99})
    public String test(int t){
        System.out.println("1111");
        return  new String("s");
    }

    /**
     * 获取注解值
     * @param args
     */
    public static void main(String[] args) {
        try {
            //获取到test 的class对象
            Class c = Class.forName("cn.lsr.Anotation.Test");
            //
            Method method = c.getMethod("test", int.class);
            if (method.isAnnotationPresent(Mytest.class)){
                System.out.println("获取到了");
                Mytest mytest  = method.getAnnotation(Mytest.class);
                System.out.println("name:"+mytest.value()+",+"+"age:"+mytest.age()+"int:"+mytest.sorce()[0]);
            }else {
                System.out.println("没有获取到!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
