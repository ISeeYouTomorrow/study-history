package com.study.lxl.springboot.task.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Lukas
 * @since 2019/5/23 12:38
 **/
@EnableScheduling
@Component
public class ScheduleJob {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleJob.class);

    /**
     * 秒  分 时 日 月 星期 年
     */
    @Scheduled(cron = "0/30 * * * * * *")
    public void cronSchedule() {
        logger.debug("cron schedule ---- 固定时间点(30s)执行任务，如果超出任务周期，则等待到下个时间点执行 ");
    }

    @Scheduled(fixedRate = 10*1000)
    public void fixRateSchedule() {
        logger.debug("fix rate schedule ---- 固定频率执行任务,如果执行任务超出时间间隔，则立即执行下次任务 ");
    }

    @Scheduled(fixedDelay = 5*1000)
    public void fixDelay() {
        logger.debug("fix delay schedule ---- 等待固定时间间隔执行任务,永远都在上次任务完成后等待5s再次执行");
    }
}
