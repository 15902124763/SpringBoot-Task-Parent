package com.yarm.task.service;

import com.alibaba.fastjson.JSON;
import com.yarm.task.common.utils.QuartzUtils;
import com.yarm.task.dao.TaskDao;
import com.yarm.task.dao.TaskGroupDao;
import com.yarm.task.pojo.dao.TaskDO;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/6
 * Time:12:46
 * Des:
 */
public abstract class TaskServiceAbstract implements TaskService {

    private static Logger log = LoggerFactory.getLogger(TaskServiceAbstract.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private TaskDao taskDao;

    @Transactional
    @Override
    public boolean createScheduleJob(TaskDO taskDO) {

        // jobId不存在
        if(StringUtils.isBlank(taskDO.getJobId())) return false;

        boolean isOk = QuartzUtils.createScheduleJob(scheduler, taskDO);
        if(isOk) {
            taskDao.updateStatusByJobId(taskDO.getJobId(), 1);
            return true;
        }
        return false;
    }


    @Override
    public boolean resumeJobs() {
        // 获取原来启动的任务
        List<TaskDO> allByStatus = taskDao.getAllByStatus(1);
        if (Objects.isNull(allByStatus)) return false;
        for (TaskDO taskDo : allByStatus) {
            if(Objects.isNull(taskDo)) continue;
            boolean isOk = QuartzUtils.createScheduleJob(scheduler, taskDo);
            if (isOk) {
                log.info("任务启动初始化成功:" + JSON.toJSON(taskDo).toString());
            } else {
                // 邮件提示 TODO
                log.warn("任务启动初始化失败:" + JSON.toJSON(taskDo).toString());
            }
        }
        return true;
    }

}