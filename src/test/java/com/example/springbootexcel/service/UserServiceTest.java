package com.example.springbootexcel.service;

import com.example.springbootexcel.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories(basePackages = {"com.example.springbootexcel.dao"})
@EntityScan("com.example.springbootexcel.model")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void saveUser() {
        UserDto userDto = new UserDto();
        userDto.builder().name("陈鑫")
                .age(24)
                .address("鹤岗")
                .gender(1)
                .height(168.5)
                .phone("15600035913")
                .createTime(new Date());
        UserDto userDto1 = userService.saveUser(userDto);
    }
}