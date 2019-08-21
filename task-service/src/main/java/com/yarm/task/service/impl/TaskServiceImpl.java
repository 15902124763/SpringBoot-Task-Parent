package com.yarm.task.service.impl;

import com.yarm.task.common.utils.QuartzUtils;
import com.yarm.task.dao.TaskDao;
import com.yarm.task.dao.TaskGroupDao;
import com.yarm.task.pojo.dao.TaskDO;
import com.yarm.task.pojo.dao.TaskGroupDO;
import com.yarm.task.service.TaskServiceAbstract;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/6
 * Time:17:18
 * Des:
 */
@Service
public class TaskServiceImpl extends TaskServiceAbstract {
    private static Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private TaskGroupDao taskGroupDao;
    @Autowired
    private Scheduler scheduler;
    @Override
    public boolean insert(TaskDO taskDO) {
        if(StringUtils.isNotBlank(taskDO.getJobId())){
            boolean exists = taskDao.existsByJobId(taskDO.getJobId());
            if(exists) return true;
        }

        boolean existsByJobClassName = taskDao.existsByJobClassName(taskDO.getJobClassName());
        if (existsByJobClassName) return true;

        // 生成jobId
        String jobId = QuartzUtils.getJobId();
        taskDO.setJobId(jobId);

        // 去空格
        taskDO.setCronExpression(taskDO.getCronExpression().trim());
        taskDO.setJobClassName(taskDO.getCronExpression().trim());

        // 未分组区默认组
        if(StringUtils.isBlank(taskDO.getJobGroup())){
            // 取默认组
            TaskGroupDO dfGroup = taskGroupDao.getByTypeAndStatus(1, 1);
            if(Objects.isNull(dfGroup))
                taskDO.setJobGroup("default");
            else
                taskDO.setJobGroup(dfGroup.getJobGroup());
        }

        // 非启动状态
        taskDO.setStatus(0);
        TaskDO save = taskDao.save(taskDO);
        if(Objects.isNull(save)) return false;

        return true;
    }


    @Override
    public TaskDO getByJobId(String jobId) {
        return null;
    }

    @Override
    public List<TaskDO> getListByJobClassName(String jobClassName) {
        return null;
    }

    @Override
    public List<TaskDO> getListByJobName(String jobName) {
        return null;
    }

    @Override
    public boolean existByJobId(String jobId) {
        return taskDao.existsByJobId(jobId);
    }

    @Transactional
    @Override
    public boolean stopJob(String jobId) {
        if(StringUtils.isBlank(jobId)) return false;
        taskDao.updateStatusByJobId(jobId, 0);
        return QuartzUtils.deleteScheduleJob(this.scheduler, jobId);
    }

    @Override
    public boolean runOnce(String jobId) {
        if(StringUtils.isBlank(jobId)) return false;

        TaskDO taskDO = taskDao.getTaskDOByJobId(jobId);
        if(Objects.isNull(taskDO)) return false;

        if(StringUtils.isBlank(taskDO.getJobGroup())) return false;

        return QuartzUtils.runOnce(scheduler, jobId, taskDO.getJobGroup());
    }

    @Override
    public void updateByJobId(String jobId) {
        TaskDO taskDOByJobId = taskDao.getTaskDOByJobId(jobId);
        Example<TaskDO> of = Example.of(taskDOByJobId);
    }
}