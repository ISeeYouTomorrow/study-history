package com.study.lxl.bean;

/**
 * @ClassName BeanA
 * @Author @lvxile
 * @Date 2019/8/5 17:26
 * @Description TODO
 */
public class BeanB {
    private BeanC beanc;

    public BeanB(BeanC beanc) {
        this.beanc = beanc;
    }

    public void setBeanc(BeanC beanc) {
        this.beanc = beanc;
    }
}
