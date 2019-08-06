package com.study.lxl.bean;

/**
 * @ClassName BeanA
 * @Author @lvxile
 * @Date 2019/8/5 17:26
 * @Description TODO
 */
public class BeanA {
    private BeanB beanB;

    public BeanA() {
    }

    public BeanA(BeanB beanB) {
        this.beanB = beanB;
    }

    public void setBeanB(BeanB beanB) {
        this.beanB = beanB;
    }
}
