package com.study.lxl.springboot.task.proxy;

/**
 * @author Lukas
 * @since 2019/6/3 17:28
 **/
public class PhinexBird extends Bird {

    private Integer age;

    String address;

    private void reGenerate() {
        System.out.println("重生-------");
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
