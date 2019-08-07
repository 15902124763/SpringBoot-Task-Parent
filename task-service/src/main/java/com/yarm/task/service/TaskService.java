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

    /************************ 以下是抽象类实现的方法 ************************/

    /**
     * 创建以及启动任务
     * @param taskDO
     * @return
     */
    boolean createScheduleJob(TaskDO taskDO);

    /**
     * 唤醒Status=1的任务
     * @return
     */
    boolean resumeJobs();



    /************************ 以下是普通类实现的方法 ************************/

    /**
     * 数据库记录任务
     * @param taskDO
     */
    boolean insert(TaskDO taskDO);


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
     * @param jobId
     * @return
     */
    boolean existByJobId(String jobId);

    /**
     * 关闭任务
     * @param jobId
     * @return
     */
    boolean stopJob(String jobId);

    /**
     * 使任务执行一次
     * @param jobId
     * @return
     */
    boolean runOnce(String jobId);

    /**
     * 更新
     * @param jobId
     */
    void updateByJobId(String jobId);
}