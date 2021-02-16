package com.example.a_middlewares.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Quartz 案例
 * 核心概念：
 * 1，Job → JobDetail 定义任务内容
 * 2，Trigger         任务调度的规则，重点是 cron 表达式
 * 3，Scheduler       调度器 scheduler.scheduleJob(jobDetail, trigger)
 */
public class QuartzApp {
    public static SchedulerFactory schedFact = new StdSchedulerFactory();
    public static Scheduler sched;

    public static void main(String[] args) {
        //定时规则,跟普通crontable的差不多
        String rule = "0/3 * * * * ?";

        test1("type1", "task1", rule);
    }

    public static void startSched() throws SchedulerException {
        try {
            QuartzApp.sched = QuartzApp.schedFact.getScheduler();
            QuartzApp.sched.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean test1(String type, String taskId, String rule) {
        try {
            // 若sched未赋值或者未启动，则先在全局中启动它
            if (QuartzApp.sched == null || !QuartzApp.sched.isStarted()) {
                QuartzApp.startSched();
            }
            //设置组名，和任务名
            String quartz_name = taskId;
            String quartz_group = type;
            // 创建jobDetail实例，指定job名以及所属组
            JobDetail jobDetail = JobBuilder.newJob(ExampleJob.class)
                    .withIdentity(quartz_name, quartz_group).build();
            jobDetail.getJobDataMap().put("taskId", taskId);// 这里也可以放上 Spring IOC 容器

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(quartz_name, quartz_group)
                    .withSchedule(CronScheduleBuilder.cronSchedule(rule))
                    .startNow().build();

            QuartzApp.sched.scheduleJob(jobDetail, trigger);

            System.out.println("[已添加定时获取进度任务, taskID:" + taskId + ", type:" + type + "]");
            return true;
        } catch (Exception e) {
            System.out.println("[添加定时任务出错,任务号:" + taskId + "]");
            System.out.println(e.toString());
            return false;
        }
    }

    /**
     * 对 job 的一些操作
     */
    public static void jobOperate() {
        // 创建 jobKey，唯一定位 job
        // JobKey.jobKey(jobName, jobGroup)

        // 暂停 job
        // sched.pauseJob( jobKey );

        // 恢复 job
        // sched.resumeJob( jobKey );

        // 删除 job
        // sched.deleteJob( jobKey )

        // 触发任务
        // sched.triggerJob( jobKey );

        // 检查是否存在
        // sched.checkExists( jobKey )

        // 创建任务
        // sched.scheduleJob( jobDetails, trigger )

        // 更新任务时，先删，后新增
    }
}
