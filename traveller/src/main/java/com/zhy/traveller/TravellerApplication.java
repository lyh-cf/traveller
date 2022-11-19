package com.zhy.traveller;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j//由lombok提供
@SpringBootApplication
//@MapperScan("com/zhy/traveller/mapper")//扫描所在包下的mapper接口
public class TravellerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravellerApplication.class, args);
        //输出日志
        log.info("traveller项目启动成功...");
    }

}
