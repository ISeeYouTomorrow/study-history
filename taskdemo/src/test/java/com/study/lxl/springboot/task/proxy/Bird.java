package com.study.lxl.springboot.task.proxy;

/**
 * @author Lukas
 * @since 2019/5/27 18:32
 **/
public class Bird implements Fly {

    private Integer color;

    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    @Override
    public void fly() {
        System.out.println("bird fly with swing");
    }

    @Override
    public void run() {
        System.out.println("bird fly with legs");
    }

    @Override
    public String toString() {
        return "color = "+color+"; name= "+name;
    }
}
