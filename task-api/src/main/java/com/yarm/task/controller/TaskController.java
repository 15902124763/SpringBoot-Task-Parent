package com.yarm.task.controller;

import com.yarm.task.pojo.ResponseApi;
import com.yarm.task.pojo.dao.TaskDO;
import com.yarm.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/6
 * Time:13:09
 * Des:
 */
@RestController
@RequestMapping("job")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("create")
    public ResponseApi<Boolean> createJob(TaskDO taskDo){
        ResponseApi<Boolean> responseApi = new ResponseApi<>();

        responseApi.setData(false);
        return responseApi;
    }

    @PostMapping("del/{jobId}")
    public ResponseApi<Boolean> delJob(@PathVariable("jobId") String jobId){
        ResponseApi<Boolean> responseApi = new ResponseApi<>();
        boolean scheduleJob = taskService.delScheduleJob(jobId);
        responseApi.setData(scheduleJob);
        return responseApi;
    }
}