package com.study.lxl.springboot.task.bean;

import com.study.lxl.bean.BeanA;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName BeanTest
 * @Author @lvxile
 * @Date 2019/8/5 17:30
 * @Description TODO
 */
public class BeanTest {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:bean.xml");

        BeanA a = (BeanA)context.getBean("a");

        System.out.println(a);

    }
}
