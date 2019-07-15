package com.lxl.javapattern.facade;

/**
 * @ClassName FacadeTest
 * @Author @lvxile
 * @Date 2019/7/15 10:39
 * @Description TODO
 */
public class FacadeTest {

    public static void main(String[] args) {
        FacadeUI facadeUI = new FacadeUI();
        facadeUI.startCPU();

        facadeUI.startSDCard();
    }
}
