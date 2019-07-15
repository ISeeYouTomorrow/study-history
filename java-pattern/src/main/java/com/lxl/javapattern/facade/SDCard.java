package com.lxl.javapattern.facade;

/**
 * @ClassName SDCard
 * @Author @lvxile
 * @Date 2019/7/15 10:33
 * @Description TODO
 */
public class SDCard implements ComputerLifeCycle{
    @Override
    public void start() {
        System.out.println("内存启动");
    }
}
