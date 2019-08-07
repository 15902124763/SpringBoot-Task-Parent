package com.yarm.task.pojo.dao;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/5
 * Time:19:55
 * Des:映射任务表
 */
@Entity
@Table(name = "t_task")
@EntityListeners(AuditingEntityListener.class)
public class TaskDO extends BaseDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//支持mysql主键自增长
    private int id;
    // 任务名称
    @Column(length = 500)
    @NotNull(message = "任务名称不能为空")
    private String jobName;

    // 任务描述
    @Column(length = 500)
    private String jobDescription;

    @Column(length = 500)
    @NotNull(message = "任务执行类不能为空")
    private String jobClassName;//执行类
    // 任务id
    @Column(length = 500, nullable = false)
    private String jobId;

    // 任务分组
    @Column(length = 500, nullable = false)
    @NotNull(message = "任务分组不能为空")
    private String jobGroup;

    // 任务状态 启动还是暂停,0：启动，1：非启动
    @Column(length = 4)
    private int status;

    // 任务运行时间表达式
    @Column(length = 32, nullable = false)
    @NotNull(message = "任务cron表达式不能为空")
    private String cronExpression;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
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

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }
}