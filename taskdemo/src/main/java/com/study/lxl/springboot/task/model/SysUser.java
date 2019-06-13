package com.study.lxl.springboot.task.model;

import java.io.Serializable;

/**
 * @author Lukas
 * @since 2019/5/24 11:09
 **/
public class SysUser implements Serializable {

    Integer userId;

    String companyNumber;

    String userName;

    String realName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "userName:"+userName+";realName:"+realName;
    }
}
