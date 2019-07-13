package com.lxl.javapattern.builder;

/**
 * @ClassName Director
 * @Author @lvxile
 * @Date 2019/7/12 17:37
 * @Description 监工
 */
public class Director { //将一个复杂的构建过程与其表示相分离

    private Builder builder;//针对接口编程

    public Director (Builder builder) {
        this.builder = builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public void build() {
        builder.buildEnginee();

        for (int i = 0; i < 4; i++) {
            builder.buildWheel();
        }

        builder.buildBottom();
    }


}
