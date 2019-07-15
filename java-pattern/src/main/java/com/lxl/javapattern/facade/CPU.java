package com.lxl.javapattern.facade;

/**
 * @ClassName CPU
 * @Author @lvxile
 * @Date 2019/7/15 10:35
 * @Description TODO
 */
public class CPU implements ComputerLifeCycle {
    @Override
    public void start() {
        System.out.println("CPU 启动了");
    }
}
