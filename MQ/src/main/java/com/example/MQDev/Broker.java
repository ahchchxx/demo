package com.example.MQDev;

import org.apache.rocketmq.broker.BrokerStartup;

public class Broker {

    // Configuration Setting in IDE:
    //  1, Environment Variables:
    //      ROCKETMQ_HOME=D:\workstation\demo\MQ
    //  2, Program Arguments:
    //      -c D:\workstation\demo\MQ\conf\broker.conf
    public static void main(String[] args) {
        BrokerStartup.main(args);
    }

}
