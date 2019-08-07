package com.yarm.task.api.controller;

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
 * Des:任务
 */
@RestController
@RequestMapping("job")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("create")
    public ResponseApi<Boolean> createJob(TaskDO taskDo){
        ResponseApi<Boolean> responseApi = new ResponseApi<>();
        boolean insert = taskService.insert(taskDo);
        responseApi.setData(insert);
        return responseApi;
    }

    @PostMapping("stop/{jobId}")
    public ResponseApi<Boolean> stopJob(@PathVariable("jobId") String jobId){
        ResponseApi<Boolean> responseApi = new ResponseApi<>();
        boolean isOk = taskService.stopJob(jobId);
        responseApi.setData(isOk);
        return responseApi;
    }

    @PostMapping("run/once/{jobId}")
    public ResponseApi<Boolean> runOnce(@PathVariable("jobId") String jobId){
        ResponseApi<Boolean> responseApi = new ResponseApi<>();
        boolean isOk = taskService.runOnce(jobId);
        responseApi.setData(isOk);
        return responseApi;
    }
}