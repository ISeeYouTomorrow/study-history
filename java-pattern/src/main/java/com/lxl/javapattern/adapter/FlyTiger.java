package com.lxl.javapattern.adapter;

/**
 * @ClassName FlyTiger
 * @Author @lvxile
 * @Date 2019/7/13 13:12
 * @Description 适配器模式 适配器继承或依赖已有的对象，实现想要的目标接口,可以让任何两个没有关联的类一起运行
 */
public class FlyTiger implements Fly {

    private Tiger tiger;
    public FlyTiger(Tiger tiger) {
        this.tiger = tiger;
    }

    @Override
    public void fly() {
        tiger.jump();
        System.out.println("老虎可以飞了");
    }
}
