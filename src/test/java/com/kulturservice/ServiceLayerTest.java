package com.kulturservice;

import com.kulturservice.Repository.UserRepository;
import com.kulturservice.model.User;
import com.kulturservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


    @DataJpaTest() // will use H2 database
    public class ServiceLayerTest {
        private UserService userService;
        @Autowired
        UserRepository userRepository;

        @BeforeEach // kaldes før hver @Test
        public void setUp() {
            userService = new UserService(userRepository);
        }

        @Test
        public void testSignup() {
            User user = new User("ole123", "123");
            User savedUser = userService.save(user);
            Assertions.assertNotNull(savedUser);
            Assertions.assertEquals("ole123", savedUser.getUserName());
        }



    }