package com.lxl.javapattern.builder;

/**
 * @ClassName Builder
 * @Author @lvxile
 * @Date 2019/7/12 17:24
 * @Description 建造者角色-抽象类或接口
 */
public abstract class Builder {

    protected StringBuilder stringBuilder = new StringBuilder();

    /**
     * 创建车轮
     */
    abstract void buildWheel();
    /**
     * 创建发动机
     */
    abstract void buildEnginee();

    /**
     * 创建底盘
     */
    abstract void buildBottom();

    String getResult() {
        return stringBuilder.toString();
    }
}
