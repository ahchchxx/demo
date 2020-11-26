package com.example.test1;

import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.util.Date;

public class OrderService1 {
    private String serviceName = "order-service";

    public void init() throws InterruptedException {
        String serverList = "192.168.118.131:2181";
        String path = "/config";
        ZkClient zkClient = new ZkClient(serverList);
        if (!zkClient.exists(path)) {
            zkClient.createPersistent(path);
        }
        if (!zkClient.exists(path + "/" + serviceName)) {
            zkClient.createPersistent(path + "/" + serviceName);
        }
        if (!zkClient.exists(path + "/" + serviceName + "/order1")) {
            zkClient.createPersistent(path + "/" + serviceName + "/order1");
            System.out.println("create order success");
        }
        zkClient.writeData(path + "/" + serviceName, new Date().getTime()); //  + "/order2"
        System.out.println("node updated");

        {
            Thread.sleep(1000);
            zkClient.delete(path + "/" + serviceName + "/order1");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        OrderService1 service1 = new OrderService1();
        service1.init();

        System.in.read();
    }
}
