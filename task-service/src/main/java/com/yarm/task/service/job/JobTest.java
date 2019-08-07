package com.yarm.task.service.job;

import com.alibaba.fastjson.JSON;
import com.yarm.task.pojo.dao.TaskDO;
import com.yarm.task.service.TaskService;
import com.yarm.task.common.utils.QuartzUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/6
 * Time:17:50
 * Des:
 */
@Service
public class JobTest implements Job {
    private static Logger log = LoggerFactory.getLogger(JobTest.class);
    @Autowired
    private TaskService taskService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("goood job");

//        boolean isOk = taskService.createScheduleJob(taskDO);
//        if(isOk)
//            log.info("任务开始执行：" + JSON.toJSON(taskDO));
    }
}