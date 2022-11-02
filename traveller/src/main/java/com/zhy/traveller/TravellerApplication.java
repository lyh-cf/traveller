package com.zhy.traveller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j//由lombok提供
@SpringBootApplication
public class TravellerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravellerApplication.class, args);
        //输出日志
        log.info("traveller项目启动成功...");
    }

}
