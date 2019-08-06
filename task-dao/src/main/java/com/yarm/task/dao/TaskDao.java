package com.yarm.task.dao;

import com.yarm.task.pojo.dao.TaskDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

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
    @Query(value = "update t_task set status = :status where job_id = :jobId", nativeQuery = true)
    void updateStatusByJobId(@Param("jobId") String jobId, @Param("status") int status);
}