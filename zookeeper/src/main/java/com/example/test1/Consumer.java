package com.example.test1;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Consumer {

    private String serviceName = "order-service";
    private List<String> serviceList = new ArrayList<>();

    public void init() {
        String serverList = "192.168.118.131:2181";
        String path = "/config/" + serviceName;
        ZkClient client = new ZkClient(serverList);
        if (client.exists(path)) {
            serviceList = client.getChildren(path);
        } else {
            throw new RuntimeException("zk error");
        }

        client.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("service change:" + s);
                list.forEach(s1 -> System.out.println(s1));

                serviceList = list;
            }
        });
        client.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("data change:" + s + " → " + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("node deleted:" + s);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        Consumer consumer = new Consumer();
        consumer.init();

        System.in.read();
    }
}
