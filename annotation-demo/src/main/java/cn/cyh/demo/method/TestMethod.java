package cn.cyh.demo.method;

import cn.cyh.demo.annotation.Check;

/**
 * @Program simple-framework
 * @Description 一个用来作为测试对象的方法
 * @Author cyh
 * @Date 2019/3/16 21:32
 * @Version V1.0
 **/
public class TestMethod {

    @Check
    public void add(){
        System.out.println(" 1 + 1 = " + (1+1));
    }

    @Check
    public void sub(){
        String string = null;
        string.toString();
        System.out.println(" 2 - 1 = " + (2-1));
    }

    @Check
    public void mul(){
        System.out.println(" 3 * 2 = " + (3*2));
    }

    @Check
    public void div(){
        System.out.println(" 1 / 0 = " + (1/0));
    }

}
