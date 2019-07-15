package com.lxl.javapattern.facade;

/**
 * @ClassName FacadeUI
 * @Author @lvxile
 * @Date 2019/7/15 10:33
 * @Description 门面类
 */
public class FacadeUI {
    private ComputerLifeCycle cpu ;

    private ComputerLifeCycle SDCard;

    public void startCPU() {
        cpu = new CPU();
        cpu.start();
    }

    public void startSDCard() {
        SDCard = new SDCard();
        SDCard.start();
    }

}
