package com.lxl.javapattern.strategy;

import lombok.Data;

/**
 * @ClassName Context
 * @Author @lvxile
 * @Date 2019/7/11 15:01
 * @Description TODO
 */
@Data
public class Context implements Strategy{
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }



    @Override
    public int calculate(int a, int b) {
        return strategy.calculate(a, b);
    }
}
