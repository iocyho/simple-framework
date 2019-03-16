package cn.cyh.demo.reflect;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @Program simple-framework
 * @Description 读取配置文件中的信息，根据配置文件中的信息实例化一个类，并调用它的成员方法
 * @Author cyh
 * @Date 2019/3/16 22:47
 * @Version V1.0
 **/
public class ReflectDemo {

    public static void main(String[] args) throws Exception {
        //加载配置文件
        Properties properties = loadPropertiesFile("test.properties");

        //读取配置文件内容
        String className = properties.getProperty("className");

        //获取Class类
        Class claz = Class.forName(className);

        //实例化该类，创建对象
        Object o = claz.newInstance();

        //获取该类的成员方法，反射调用
        String methodName = properties.getProperty("methodName");
        Method method = claz.getMethod(methodName);
        method.invoke(o);
    }

    /**
     * @Description 加载配置文件
     * @Author cyh
     * @Date 23:58 2019/3/16
     * @Param []
     * @return void
     **/
    private static Properties loadPropertiesFile(String fileName) throws IOException {
        //加载配置文件
        Properties properties = new Properties();
        ClassLoader classLoader = ReflectDemo.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        properties.load(inputStream);

        return properties;
    }

    public void run(){

    }
}
