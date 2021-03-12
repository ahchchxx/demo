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

    public static void main(String[] args) throws Exception {
        //定时规则,跟普通crontable的差不多
        //  判断 cron 是否正确的方法： org.quartz.CronExpression.isValidExpression
        String rule = "0/2 * * * * ?";

        // 组名、任务名
        String quartz_name = "type1";
        String quartz_group = "task1";
        JobKey jobKey = new JobKey(quartz_name, quartz_group);
        // create and start a job
        createScheduleJob(quartz_name, quartz_group, rule);
        Thread.sleep(10 * 1000); // execute 10s


        System.out.println("pause job");
        QuartzApp.sched.pauseJob(jobKey);
        Thread.sleep(6000); // pause 6s


        System.out.println("trigger job");
        QuartzApp.sched.triggerJob(jobKey); // only execute one time
        // QuartzApp.sched.resumeJob(jobKey); // keep executing
    }

    public static void startSched() throws SchedulerException {
        QuartzApp.sched = QuartzApp.schedFact.getScheduler();
        QuartzApp.sched.start();
    }

    public static boolean createScheduleJob(String quartz_name, String quartz_group, String rule) {
        try {
            // 若sched未赋值或者未启动，则先在全局中启动它
            if (QuartzApp.sched == null || !QuartzApp.sched.isStarted()) {
                QuartzApp.startSched();
            }
            // 创建jobDetail实例，指定job名以及所属组
            JobDetail jobDetail = JobBuilder.newJob(ExampleJob.class)
                    .withIdentity(quartz_name, quartz_group).build();
            jobDetail.getJobDataMap().put("taskId", quartz_name);// 这里也可以放上 Spring IOC 容器

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(quartz_name, quartz_group)
                    .withSchedule(CronScheduleBuilder.cronSchedule(rule))
                    .startNow().build();

            QuartzApp.sched.scheduleJob(jobDetail, trigger);

            System.out.println("[已添加定时获取进度任务, taskID:" + quartz_name + ", type:" + quartz_group + "]");

            return true;
        } catch (Exception e) {
            System.out.println("[添加定时任务出错,任务号:" + quartz_name + "]");
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

        // 触发任务（对应前台“手动执行”的功能）
        // sched.triggerJob( jobKey );

        // 检查是否存在
        // sched.checkExists( jobKey )

        // 创建任务
        // sched.scheduleJob( jobDetails, trigger )

        // 更新任务时，先删，后新增
    }

    // SpringBoot 工程里的：
    //   com.xxx.quartz.service.impl.SysJobServiceImpl
    //   项目启动时，就会加载全部 job 到调度器
    //     @Autowired
    //     private Scheduler scheduler;
    //     @Autowired
    //     private SysJobMapper jobMapper;
    //     /**
    //      * 项目启动时，初始化定时器
    //      * 主要是防止手动修改数据库导致未同步到定时任务处理（注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
    //      */
    //     @PostConstruct
    //     public void init() throws SchedulerException, TaskException
    //     {
    //         scheduler.clear();
    //         List<SysJob> jobList = jobMapper.selectJobAll();
    //         for (SysJob job : jobList)
    //         {
    //             ScheduleUtils.createScheduleJob(scheduler, job);
    //         }
    //     }
}
