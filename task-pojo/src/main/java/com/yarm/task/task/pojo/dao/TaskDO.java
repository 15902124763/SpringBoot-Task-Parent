package com.yarm.task.task.pojo.dao;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

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
public class TaskDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//支持mysql主键自增长
    private int id;
    // 任务名称
    @Column(length = 500)
    private String jobName;

    // 任务id
    @Column(length = 500, nullable = false)
    private String jobId;

    // 任务状态 启动还是暂停,0：非启动，1：启动
    @Column(length = 4)
    private int status;

    // 任务运行时间表达式
    @Column(length = 32, nullable = false)
    private String cronExpression;

    @Column
    @CreatedDate
    private Date createdAt;

    @Column(length = 64)
    @CreatedBy
    private String createdBy = "system";

    @Column
    @LastModifiedDate
    private Date updatedAt;

    @Column(length = 64)
    @LastModifiedBy
    private String updatedBy;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}