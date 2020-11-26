package com.example.redislock.secondkill;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecKillController {


    @RequestMapping("/seckill")
    public String seckill() {
        SeckillInterface seckill = new SecKillImpl();
        seckill.secKill("testUser", 10000001L);
        seckill.secKill("testUser", 10000002L);

        System.out.println(SecKillImpl.inventory.get(10000001L)); // commidityId1
        System.out.println(SecKillImpl.inventory.get(10000002L)); // commidityId2

        return "end";
    }

}
