package com.study.lxl.springboot.task.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * 多线程任务异步执行
 * @author Lukas
 * @since 2019/5/23 12:34
 **/
@EnableAsync
@Component
public class TaskJob {

    private static final Logger logger = LoggerFactory.getLogger(TaskJob.class);

    @Async
    public void task1() {
        logger.info("执行任务 task1 success ");
    }


    @Async
    public void task2() {
        logger.info("执行任务 task1 success ");
    }

    @Async
    public void task3() {
        logger.info("执行任务 task1 success ");
    }

}
