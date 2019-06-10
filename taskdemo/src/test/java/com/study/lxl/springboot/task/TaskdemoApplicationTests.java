package com.study.lxl.springboot.task;

import com.study.lxl.springboot.task.mapper.SysUserMapper;
import com.study.lxl.springboot.task.proxy.Bird;
import com.study.lxl.springboot.task.proxy.BirdProxy;
import com.study.lxl.springboot.task.proxy.Fly;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskdemoApplicationTests {


    @Autowired
    private SysUserMapper mapper;

    @Test
    public void contextLoads() {

        System.out.println(mapper.queryList().size());

    }
}
