package com.study.lxl.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xile.lv
 * @since 2020/6/28 14:46
 **/
@SpringBootApplication
@RestController
@RequestMapping("/boot")
public class BootstartAspect {
    public static void main(String[] args) {
        SpringApplication.run(BootstartAspect.class, args);
    }

    @Autowired
    private SearchService searchService;

    @RequestMapping("/query/{name}")
    @ResponseBody
    public String quickSearch(@PathVariable("name") String name) {
        return searchService.testCache(name);
    }
}
