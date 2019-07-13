package com.lxl.javapattern.builder;

import java.util.Arrays;

/**
 * @ClassName Client
 * @Author @lvxile
 * @Date 2019/7/12 17:40
 * @Description 客户端调用
 */
public class Client {

    public static void main(String[] args) {
        Builder builder = new WheelBuilder();//建造者

        Director director = new Director(builder); // 监工
        director.build(); // 监工指挥建造

        String rs = builder.getResult(); // 建造结果

        System.out.println("rs == "+rs);


        int[] x1 = {1,2,3,4,5,6,7,8};
        int[] x2 = new int[8];

        System.arraycopy(x1,1,x2,0,7);

        Arrays.stream(x2).forEach(k -> System.out.print(k+" "));
    }


}
