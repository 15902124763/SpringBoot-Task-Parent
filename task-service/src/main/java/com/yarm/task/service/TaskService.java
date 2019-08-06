package com.yarm.task.service;

import com.yarm.task.pojo.dao.TaskDO;

import java.util.List;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/6
 * Time:17:11
 * Des:
 */
public interface TaskService {
    boolean createScheduleJob(TaskDO taskDO);
    boolean delScheduleJob(String jobId);

    /**
     * 数据库记录任务
     * @param taskDO
     */
    void insert(TaskDO taskDO);


    /**
     * 依据jobId查询
     * @param jobId
     * @return
     */
    TaskDO getByJobId(String jobId);

    /**
     * 依据任务类名查询
     * @param jobClassName
     * @return
     */
    List<TaskDO> getListByJobClassName(String jobClassName);

    /**
     * 依据任务名称查询
     * @param jobName
     * @return
     */
    List<TaskDO> getListByJobName(String jobName);

    /**
     * 是否存在
     * @param taskDO
     * @return
     */
    boolean exist(TaskDO taskDO);
}