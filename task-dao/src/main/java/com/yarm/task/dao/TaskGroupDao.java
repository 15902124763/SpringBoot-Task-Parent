package com.yarm.task.dao;

import com.yarm.task.pojo.dao.TaskGroupDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/7
 * Time:10:52
 * Des:
 */
@Repository
public interface TaskGroupDao extends JpaRepository<TaskGroupDO, Integer>, QueryByExampleExecutor<TaskGroupDO> {
   boolean existsByJobGroupAndType(@Param("jobGroup") String jobGroup, @Param("type") int type);
   TaskGroupDO getByTypeAndStatus(@Param("type") int type, @Param("status") int status);
}