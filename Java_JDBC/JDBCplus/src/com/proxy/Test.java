package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author KevinWilliams
 */
public class Test {
    public static void main(String[] args) {
        Student stu = new Student();
        /*stu.eat("米饭");
        stu.study();*/


        //需求：在不改变Student类的前提下，将自学改成去学校学习/
        Studentinterface proxyStu = (Studentinterface) Proxy.newProxyInstance(stu.getClass().getClassLoader(), new Class[]{Studentinterface.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equals("study")){
                    System.out.println("来学校学习");
                    return null;
                }else {
                    return method.invoke(stu,args);
                }
            }
        });

        proxyStu.eat("米饭");
        proxyStu.study();
    }
}
