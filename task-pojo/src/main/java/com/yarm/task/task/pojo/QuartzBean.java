package com.yarm.task.task.pojo;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/5
 * Time:18:20
 * Des:
 */
public class QuartzBean {

    private int  id;

    // 任务名称
    private String jobName;

    // 任务id
    private String jobId;

    // 任务状态 启动还是暂停
    private int status;

    // 任务运行时间表达式
    private String cronExpression;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}