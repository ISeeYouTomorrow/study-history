package com.study.lxl.springboot.task.proxy;

/**
 * @author Lukas
 * @since 2019/5/27 18:54
 **/
public class ProxyTest {

    public void testClass() {



    }


    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Fly fly = new Bird();
        BirdProxy proxy = new BirdProxy(fly);
        Fly proxyFly = proxy.getProxy();
        proxyFly.fly();

//        proxyFly.run();
    }

}
