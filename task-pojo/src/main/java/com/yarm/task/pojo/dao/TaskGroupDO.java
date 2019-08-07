package com.yarm.task.pojo.dao;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/7
 * Time:10:34
 * Des:任务组
 */
@Entity
@Table(name = "t_task_group")
@EntityListeners(AuditingEntityListener.class)
public class TaskGroupDO extends BaseDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//支持mysql主键自增长
    private int id;

    // 任务分组
    @Column(length = 500, nullable = false)
    private String jobGroup;

    // 任务分组
    @Column(length = 500, nullable = false)
    private String jobGroupName;

    // 是否启用 1：是，0：非
    @Column(length = 4)
    private int status;

    // 是否是默认组 1：是，0：非
    @Column(length = 4)
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }
}