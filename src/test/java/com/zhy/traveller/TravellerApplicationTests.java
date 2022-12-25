package com.zhy.traveller;

import com.zhy.traveller.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TravellerApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {

    }

}
