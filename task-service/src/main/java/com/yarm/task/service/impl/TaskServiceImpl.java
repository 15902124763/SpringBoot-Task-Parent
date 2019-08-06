package com.yarm.task.service.impl;

import com.yarm.task.dao.TaskDao;
import com.yarm.task.pojo.dao.TaskDO;
import com.yarm.task.service.TaskServiceAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/6
 * Time:17:18
 * Des:
 */
@Service
public class TaskServiceImpl extends TaskServiceAbstract {

    @Autowired
    private TaskDao taskDao;

    @Override
    public void insert(TaskDO taskDO) {
        taskDao.save(taskDO);
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
    public boolean exist(TaskDO taskDO) {
        Example<TaskDO> example = Example.of(taskDO);
        boolean exists = taskDao.exists(example);
        return exists;
    }
}