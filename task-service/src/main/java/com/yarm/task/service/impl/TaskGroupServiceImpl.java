package com.yarm.task.service.impl;

import com.yarm.task.dao.TaskGroupDao;
import com.yarm.task.pojo.dao.TaskGroupDO;
import com.yarm.task.service.TaskGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/7
 * Time:10:56
 * Des:任务组
 */
@Service
public class TaskGroupServiceImpl implements TaskGroupService {
    @Autowired
    private TaskGroupDao taskGroupDao;

    @Override
    public TaskGroupDO insert(TaskGroupDO taskGroupDO){
        TaskGroupDO save = taskGroupDao.save(taskGroupDO);
        return save;
    }

    @Override
    public boolean exist(TaskGroupDO taskGroupDO) {
        Example<TaskGroupDO> example = Example.of(taskGroupDO);
        boolean exists = taskGroupDao.exists(example);
        return exists;
    }

    @Override
    public void delect(int id) {
        taskGroupDao.deleteById(id);
    }

    @Override
    public void delect(TaskGroupDO taskGroupDO) {
        taskGroupDao.delete(taskGroupDO);
    }

    @Override
    public TaskGroupDO update(TaskGroupDO taskGroupDO) {

        TaskGroupDO result = taskGroupDao.saveAndFlush(taskGroupDO);
        return result;
    }

}