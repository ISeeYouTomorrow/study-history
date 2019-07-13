package com.lxl.javapattern.builder;

/**
 * @ClassName WheelBuilder
 * @Author @lvxile
 * @Date 2019/7/12 17:34
 * @Description TODO
 */
public class WheelBuilder extends Builder {
    @Override
    void buildWheel() {
        stringBuilder.append("build 1 wheel\n");
    }

    @Override
    void buildEnginee() {
        stringBuilder.append("build 1 enginee\n");
    }

    @Override
    void buildBottom() {
        stringBuilder.append("build 1 bottom\n");
    }
}
