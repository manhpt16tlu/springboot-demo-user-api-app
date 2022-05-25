package com.ncc.mn.repository;

import com.ncc.mn.entity.User;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void injectedComponentsAreNotNull() {
        Assertions.assertThat(userRepository).isNotNull();
    }

    @BeforeEach
    void setup() {
        User user = User.builder().userId("123").email("mn@gmail.com").lastName("nguyen").firstName("manh").encpyptPassword("123").build();
        User user2 = User.builder().userId("124").email("mn2@gmail.com").lastName("spring").firstName("boot").encpyptPassword("123").build();
        User user3 = User.builder().userId("125").email("mn3@gmail.com").lastName("java").firstName("lambda").encpyptPassword("123").build();
        User user4 = User.builder().userId("126").email("mn4@gmail.com").lastName("c++").firstName("python").encpyptPassword("123").build();
        User user5 = User.builder().userId("126").email("mn4@gmail.com").lastName("c++").firstName("python").encpyptPassword("123").build();
        User user6 = User.builder().userId("126").email("mn4@gmail.com").lastName("c++").firstName("python").encpyptPassword("123").build();
        List<User> list = Arrays.asList(user, user2, user3, user4,user5,user6);
        userRepository.saveAll(list);
    }

    @Test
    void findByEmail() {
        Assertions.assertThat(userRepository.findByEmail("mn@gmail.com")).isNotNull();
        Assertions.assertThat(userRepository.findByEmail("mn@gmail.com").getEmail()).isEqualTo("mn@gmail.com");
        Assertions.assertThat(userRepository.findByEmail("mn@gmail.com").getUserId()).isEqualTo("123");
        Assertions.assertThat(userRepository.findByEmail("mn4@gmail.com").getFirstName()).isEqualTo("python");
    }

    @Test
    void findByUserId(){
        Assertions.assertThat(userRepository.findByUserId("125")).isNotNull();
        Assertions.assertThat(userRepository.findByUserId("125").getFirstName()).isEqualTo("lambda");
        Assertions.assertThat(userRepository.findByUserId("000")).isNull();
    }
    @Test
    void checkNumberRecord(){
        Assertions.assertThat(userRepository.checkNumberOfRecord()).isTrue();

    }

}