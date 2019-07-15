package com.lxl.javapattern.strategy;

/**
 * @ClassName StrategyAdd
 * @Author @lvxile
 * @Date 2019/7/11 14:59
 * @Description 策略实现类Reduce
 */
public class StrategyReduce implements Strategy{
    @Override
    public int calculate(int a, int b) {
        return a-b;
    }
}