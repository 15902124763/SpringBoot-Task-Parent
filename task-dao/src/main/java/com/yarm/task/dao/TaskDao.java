package com.yarm.task.dao;

import com.yarm.task.pojo.dao.TaskDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/5
 * Time:20:11
 * Des:
 */
@Repository
public interface TaskDao extends JpaRepository<TaskDO, Integer>, QueryByExampleExecutor<TaskDO> {

    @Modifying
    @Query(value = "update TaskDO t set t.status = ?2 where t.jobId = ?1")
    void updateStatusByJobId( String jobId, int status);

    boolean existsByJobId(String jobId);

    boolean existsByJobClassName(String jobClassName);

    List<TaskDO> getAllByStatus(int status);

    TaskDO getTaskDOByJobId(String jobId);

}