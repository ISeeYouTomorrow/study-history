package com.study.lxl.springboot.task;

import com.study.lxl.springboot.task.job.TaskJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan("com.study.lxl.springboot.task.job")
@RestController
public class TaskdemoApplication extends SpringBootServletInitializer {

    @Autowired
    private TaskJob taskJob;

    public static void main(String[] args) {
        SpringApplication.run(TaskdemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TaskdemoApplication.class);
    }


    @RequestMapping("job")
    public void jobTest() {
        taskJob.task1();
        taskJob.task2();
        taskJob.task3();
    }

}
