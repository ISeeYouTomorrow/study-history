package com.study.lxl.springboot.task;

import com.study.lxl.springboot.task.job.TaskJob;
import com.study.lxl.springboot.task.mapper.SysUserMapper;
import com.study.lxl.springboot.task.model.SysUser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.study.lxl.springboot.task.*")
@RestController
@MapperScan("com.study.lxl.springboot.task.mapper")
public class TaskdemoApplication extends SpringBootServletInitializer {

    @Autowired
    private TaskJob taskJob;

    @Autowired
    private SysUserMapper mapper;

    public static void main(String[] args) {
        SpringApplication.run(TaskdemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TaskdemoApplication.class);
    }

    @RequestMapping("user")
    @ResponseBody
    public List<SysUser> getUser() {
        return mapper.queryList();
    }


    @GetMapping("one/{id}")
    @ResponseBody
    public SysUser getOne(@PathVariable("id") Integer id) {
        return mapper.queryById(id);
    }

    @RequestMapping("job")
    public void jobTest() {
        taskJob.task1();
        taskJob.task2();
        taskJob.task3();
    }

}
