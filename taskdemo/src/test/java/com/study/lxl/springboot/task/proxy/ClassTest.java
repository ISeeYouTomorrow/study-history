package com.study.lxl.springboot.task.proxy;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Lukas
 * @since 2019/6/3 17:30
 **/
public class ClassTest {

    @Test
    public void testField() {
        Field[] declaredFields = PhinexBird.class.getDeclaredFields();//获取当前类或者接口的所有属性：包括公共属性/私有属性
        for (Field field : declaredFields) {
            System.out.println(field.getName());
        }
        System.out.println("----------------------------");
        Field[] fields = PhinexBird.class.getFields();//获取父类或者实现的接口的所有公共属性,不包括类本身的属性
        System.out.println("fields size : "+fields.length);
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }


    @Test
    public void testMethods() {
        try {
            Method[] declaredMethods = Class.forName("com.study.lxl.springboot.task.proxy.PhinexBird").getDeclaredMethods();//获取本身的所有方法，不包括父类的方法
            for (Method declaredField : declaredMethods) {
                System.out.println(declaredField.getName());
            }
            System.out.println("--------------");
            Method[] methods = Class.forName("com.study.lxl.springboot.task.proxy.PhinexBird").getMethods();//获取本身的/父类的所有公共方法
            for (Method declaredField : methods) {
                System.out.println(declaredField.getName());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
