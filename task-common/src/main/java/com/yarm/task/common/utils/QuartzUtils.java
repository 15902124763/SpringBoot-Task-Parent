package com.yarm.task.common.utils;

import com.alibaba.fastjson.JSON;
import com.yarm.task.pojo.dao.TaskDO;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/5
 * Time:18:15
 * Des:
 */
public class QuartzUtils {
    private static Logger log = LoggerFactory.getLogger(QuartzUtils.class);
    /**
     * 创建定时任务 定时任务创建之后默认启动状态
     * @param scheduler   调度器
     * @param taskDO  定时任务信息类
     * @throws Exception
     */
    public static boolean createScheduleJob(Scheduler scheduler, TaskDO taskDO){
        try {
            // 触发时间点
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(taskDO.getCronExpression().trim());

            // 构建job信息
            Class cls = Class.forName(taskDO.getJobClassName());
            cls.newInstance();
            JobDetail jobDetail = JobBuilder.newJob(cls)
                    .withIdentity(taskDO.getJobId(), taskDO.getJobGroup())
                    .withDescription(taskDO.getJobDescription())
                    .build();

            // 构建触发器trigger
            // 直接启动
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(taskDO.getJobId())
                    .withSchedule(scheduleBuilder)
                    .startNow()
                    .build();

            // 交由Scheduler安排触发
            scheduler.scheduleJob(jobDetail,trigger);
            return true;
        } catch (Exception e) {
            log.warn("创建定时任务失败:" + JSON.toJSON(e.getMessage()).toString());
        }

        return false;
    }

    /**
     * 根据任务ID暂停定时任务
     * @param scheduler  调度器
     * @param jobId    定时任务ID
     * @throws SchedulerException
     */
    public static boolean pauseScheduleJob(Scheduler scheduler, String jobId){
        JobKey jobKey = JobKey.jobKey(jobId);
        try {
            scheduler.pauseJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            log.warn("暂停定时任务出错:" + JSON.toJSON(e.getMessage()).toString());
        }
        return false;
    }

    /**
     * 根据任务ID恢复定时任务
     * @param scheduler  调度器
     * @param id    定时任务ID
     * @throws SchedulerException
     */
    public static boolean resumeScheduleJob(Scheduler scheduler, String id) {
        JobKey jobKey = JobKey.jobKey(id);
        try {
            scheduler.resumeJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            log.warn("启动定时任务出错:" + JSON.toJSON(e.getMessage()).toString());
        }
        return false;
    }

    /**
     * 根据任务ID立即运行一次定时任务
     * @param scheduler     调度器
     * @param jobId       定时任务ID
     * @throws SchedulerException
     */
    public static boolean runOnce(Scheduler scheduler, String jobId){
        JobKey jobKey = JobKey.jobKey(jobId);
        try {
            scheduler.triggerJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            log.warn("运行定时任务出错:" + JSON.toJSON(e.getMessage()).toString());
        }
        return false;
    }

    /**
     * 更新定时任务
     * @param scheduler   调度器
     * @param taskDO  定时任务信息类
     * @throws SchedulerException
     */
    public static boolean updateScheduleJob(Scheduler scheduler, TaskDO taskDO)  {
        try {
            // 获取到对应任务的触发器
            TriggerKey triggerKey = TriggerKey.triggerKey(taskDO.getJobId());

            // 设置定时任务执行方式
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(taskDO.getCronExpression());

            // 重新构建任务的触发器trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            trigger = trigger.getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder)
                    .build();

            // 重置对应的job
            scheduler.rescheduleJob(triggerKey, trigger);
            return true;
        } catch (SchedulerException e) {
            log.warn("更新定时任务出错:" + JSON.toJSON(e.getMessage()).toString());
        }
        return false;
    }

    /**
     * 根据定时任务名称从调度器当中删除定时任务
     * @param scheduler 调度器
     * @param jobId    定时任务ID
     * @throws SchedulerException
     */
    public static boolean deleteScheduleJob(Scheduler scheduler, String jobId) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobId);
            JobKey jobKey = JobKey.jobKey(jobId);
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            log.warn("删除定时任务出错:" + JSON.toJSON(e.getMessage()).toString());
        }
        return false;
    }

    /**
     * 获取UUID生成的jobId
     * @return
     */
    public static String getJobId(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }
}
