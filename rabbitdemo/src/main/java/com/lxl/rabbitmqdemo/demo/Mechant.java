package com.lxl.rabbitmqdemo.demo;

/**
 * @author Lukas
 * @since 2020/5/27 11:25
 **/

public class Mechant {
    private Integer id;
    private String name;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Mechant(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
