package com.example.a_middlewares.quartz;

import org.quartz.*;

@DisallowConcurrentExecution
public class ExampleJob implements Job {
    @Override
    public void execute(JobExecutionContext ctx) throws JobExecutionException {
        JobDataMap jobDataMap = ctx.getJobDetail().getJobDataMap();
        String taskId = jobDataMap.getString("taskId");

        System.out.println(taskId);
    }
}
