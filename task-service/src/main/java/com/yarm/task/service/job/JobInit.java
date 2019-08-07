package com.yarm.task.service.job;

import com.alibaba.fastjson.JSON;
import com.yarm.task.dao.TaskDao;
import com.yarm.task.dao.TaskGroupDao;
import com.yarm.task.pojo.dao.TaskGroupDO;
import com.yarm.task.service.TaskGroupService;
import com.yarm.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/7
 * Time:9:53
 * Des:项目启动初始化任务
 */
@Service
public class JobInit implements ApplicationRunner {
    private static Logger log = LoggerFactory.getLogger(JobInit.class);
    // 默认任务组时间
    private static final String DEFAULT_TASK_GROUP_TIME = "2019-01-01 00:00:00";
    @Autowired
    private TaskGroupDao taskGroupDao;
    @Autowired
    private TaskGroupService taskGroupService;
    @Autowired
    private TaskService taskService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 初始化默认组
        this.insertDefaultGroup();
        // 唤醒任务
        taskService.resumeJobs();
    }

    /**
     *　写入默认分组
     */
    private void insertDefaultGroup(){
        TaskGroupDO taskGroupDO = new TaskGroupDO();
        taskGroupDO.setJobGroup("default");
        taskGroupDO.setJobGroupName("默认组");
        taskGroupDO.setType(1);
        taskGroupDO.setStatus(1);
        boolean exist = taskGroupDao.existsByJobGroupAndType(taskGroupDO.getJobGroup(), taskGroupDO.getType());
        if(!exist){
            TaskGroupDO insert = taskGroupService.insert(taskGroupDO);
            log.info("初始化写入一条默认组数据：" + JSON.toJSON(insert));
        }
    }
}