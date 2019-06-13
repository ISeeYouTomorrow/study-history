package com.study.lxl.springboot.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Lukas
 * @since 2019/5/30 19:36
 **/
@Controller
public class UserController {

    @RequestMapping("/hello")
    public String hello(String name) {
        return "/user/hello";
    }

}
