package com.yarm.task.service;

import com.yarm.task.pojo.dao.TaskGroupDO;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/7
 * Time:11:03
 * Des:
 */
public interface TaskGroupService {
    TaskGroupDO insert(TaskGroupDO taskGroupDO);
    boolean exist(TaskGroupDO taskGroupDO);
    void delect(int id);
    void delect(TaskGroupDO taskGroupDO);
    TaskGroupDO update(TaskGroupDO taskGroupDO);
}