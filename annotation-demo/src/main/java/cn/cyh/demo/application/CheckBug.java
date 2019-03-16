package cn.cyh.demo.application;

import cn.cyh.demo.annotation.Check;
import cn.cyh.demo.method.TestMethod;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Program simple-framework
 * @Description 对加上@Check注解的方法进行测试，存在异常时记录到文件中
 * @Author cyh
 * @Date 2019/3/16 21:41
 * @Version V1.0
 **/
public class CheckBug {
    public static void main(String[] args){
        TestMethod testMethod = new TestMethod();

        //获取testMethod的字节码文件对象
        Class claz = testMethod.getClass();
        //获取testMethod的方法方法列表
        Method[] clazMethods = claz.getMethods();

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("bug.log"));

            //记录异常数量
            int count = 0;
            for (Method method : clazMethods) {
                //反射调用加上了@Check注解的方法
                if(method.isAnnotationPresent(Check.class)){
                    try {
                        //invoke里的参数是Object obj,而不是字节码文件
                        method.invoke(testMethod);
                    } catch (Exception e) {
                        //方法出现异常时捕获
                        count++;
                        //记录到文件中
                        writer.write(claz.getName() +"类中的"+ method.getName()+"方法出现了异常");
                        writer.newLine();
                        writer.write("异常名称："+e.getCause().getClass().getName());
                        writer.newLine();
                        writer.write("异常原因："+e.getCause().getMessage());
                        writer.newLine();
                        writer.write("------------------------------");
                        writer.newLine();
                    }
                }
            }
            writer.write("本次测试共出现了"+count+"次异常");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null){
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
