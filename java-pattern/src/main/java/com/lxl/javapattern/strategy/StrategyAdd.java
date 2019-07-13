package com.lxl.javapattern.strategy;

/**
 * @ClassName StrategyAdd
 * @Author @lvxile
 * @Date 2019/7/11 14:59
 * @Description 策略实现类Add
 */
public class StrategyAdd implements Strategy{
    @Override
    public int calculate(int a, int b) {
        return a+b;
    }
}
