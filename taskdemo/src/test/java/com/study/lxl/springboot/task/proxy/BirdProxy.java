package com.study.lxl.springboot.task.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类的程序处理,每一个动态实例都具有一个关联的调用处理程序。
 * @author Lukas
 * @since 2019/5/27 18:33
 **/
public class BirdProxy implements InvocationHandler {

    /**
     * 被代理对象
     */
    private Fly fly;

    public BirdProxy(Fly fly) {
        this.fly = fly;
    }

    /**
     * 获取代理对象
     * @return
     */
    public <T> T getProxy() {
        return (T)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Fly.class},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("飞之前需要做的事情");
        Object result = method.invoke(fly, args);//此处注意第一个参数不能写成proxy，否则就是无限循环，自己代理自己。
        System.out.println("飞之后需要做的事情");

        return result;
    }
}
