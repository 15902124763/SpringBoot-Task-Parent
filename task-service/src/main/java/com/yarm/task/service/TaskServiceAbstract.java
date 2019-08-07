package com.yarm.task.service;

import com.yarm.task.dao.TaskDao;
import com.yarm.task.pojo.dao.TaskDO;
import com.yarm.task.common.utils.QuartzUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/6
 * Time:12:46
 * Des:
 */
public abstract class TaskServiceAbstract implements TaskService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private TaskDao taskDao;

    @Override
    public boolean createScheduleJob(TaskDO taskDO) {

        boolean exist = this.exist(taskDO);
        if(exist) return true;

        taskDO.setStatus(1);
        this.insert(taskDO);
        boolean isOk = QuartzUtils.createScheduleJob(scheduler, taskDO);
        if(isOk){
            // 更新任务为启动状态
            taskDao.updateStatusByJobId(taskDO.getJobId(), 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean delScheduleJob(String jobId){
        return QuartzUtils.deleteScheduleJob(scheduler, jobId);
    }
}