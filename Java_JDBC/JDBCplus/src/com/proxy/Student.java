package com.proxy;

/**
 * @author KevinWilliams
 */
public class Student implements Studentinterface{
    public void eat(String name) {
        System.out.println("学生吃" + name);
    }

    public void study() {
        System.out.println("在家自学");

    }
}
