package com.lxl.javapattern.strategy;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.security.Security;
import java.util.Properties;
import java.util.Scanner;

/**
 * @ClassName TestStrategy
 * @Author @lvxile
 * @Date 2019/7/11 15:04
 * @Description 策略模式测试类
 */
public class TestStrategy {

    public static void main(String[] args) {
        Strategy strategy = new StrategyAdd();
        Context context = new Context(strategy);
        int rs = context.calculate(1,11);
        System.out.println("rs = "+rs);

        strategy = new StrategyPower();
        context = new Context(strategy);
        rs = context.calculate(2, 5);
        System.out.println("rs = "+rs);

        context.setStrategy(new StrategyReduce());
        rs = context.calculate(2,5);
        System.out.println("rs = "+rs);

        context.setStrategy(new StrategyMulti());
        rs = context.calculate(2,5);
        System.out.println("rs = "+rs);

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        int by = scanner.nextInt();
        System.out.println(by);
    }

    @Test
    public void testSystem() {

        Properties properties =  System.getProperties();

        properties.entrySet().forEach(entry ->
                        System.out.println(entry.getKey()+" = "+entry.getValue())
                );

    }

}
