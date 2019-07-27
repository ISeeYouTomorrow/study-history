package com.lxl.javapattern.filter;

import lombok.Data;

/**
 * @ClassName Person
 * @Author @lvxile
 * @Date 2019/7/16 19:21
 * @Description 在该类上应用标准即过滤器
 */
@Data
public class Person {
    private String name;
    private String gender;
    private String maritalStatus;

    public Person(String name, String gender, String maritalStatus) {
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }




}
