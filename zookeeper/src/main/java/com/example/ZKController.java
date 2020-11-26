package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("zk/")
public class ZKController {
    @Autowired
    ZKService zkService;

    @RequestMapping("/create")
    private String create(String path, String data) {
        path = "/app1/" + path;
        if (zkService.exists("/app1", new WatcherService()) == null) {
            zkService.createNode("/app1", "");
        }
        if (zkService.exists(path, new WatcherService()) != null) {
            return "node exsit";
        }
        if (zkService.createNode(path, data)) {
            return "create successfully";
        }
        return "create failed";
    }
    @RequestMapping("/update")
    private String update(String path, String data) {
        path = "/app1/" + path;
        if (zkService.exists(path, new WatcherService()) == null) {
            return "node not exsit";
        }
        if (zkService.updateNode(path, data)) {
            return "update successfully";
        }
        return "update failed";
    }

    @RequestMapping("/get")
    private String update(String path) {
        path = "/app1/" + path;
        if (zkService.exists(path, new WatcherService()) == null) {
            return "node not exsit";
        }
        return zkService.getData(path, new WatcherService());
    }

}
