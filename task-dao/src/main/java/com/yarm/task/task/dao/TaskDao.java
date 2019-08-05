package com.yarm.task.task.dao;

import com.yarm.task.task.pojo.dao.TaskDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/5
 * Time:20:11
 * Des:
 */
@Repository
public interface TaskDao extends JpaRepository<TaskDO, Long> {
}