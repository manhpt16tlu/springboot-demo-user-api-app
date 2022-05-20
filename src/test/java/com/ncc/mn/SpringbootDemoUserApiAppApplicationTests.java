package com.ncc.mn;

import com.ncc.mn.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SpringbootDemoUserApiAppApplicationTests {

    @Autowired
    private UserController userController;
    @Test
    void contextLoad(){
        assertThat(userController).isNotNull();
    }

}
