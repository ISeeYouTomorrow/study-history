package com.lxl.javapattern.adapter;

/**
 * @ClassName AdapterTest
 * @Author @lvxile
 * @Date 2019/7/13 13:14
 * @Description TODO
 */
public class AdapterTest {

    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        Fly fly = new FlyTiger(tiger);
        fly.fly();
    }
}
