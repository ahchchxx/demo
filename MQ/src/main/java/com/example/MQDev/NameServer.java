package com.example.MQDev;

import org.apache.rocketmq.namesrv.NamesrvStartup;

public class NameServer {

    // Configuration Setting in IDE:
    //  Environment Variables:
    //      ROCKETMQ_HOME=D:\workstation\demo\MQ
    //      系统会检查该参数的位置，不写直接报错
    //         org.apache.rocketmq.common.namesrv.NamesrvConfig 里获取:
    //          private String rocketmqHome = System.getProperty("rocketmq.home.dir", System.getenv("ROCKETMQ_HOME"));
    //             System.getenv("ROCKETMQ_HOME") instanceof String  判断类型
    public static void main(String[] args) {
        NamesrvStartup.main(args);
    }

}
