package com.example.springbootexcel.dao;

import com.example.springbootexcel.model.User;
import org.junit.Assert;
import org.junit.Test;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUserTest() {
        User user = new User();
        user.builder().name("陈鑫")
                .age(24)
                .address("鹤岗")
                .gender(1)
                .height(168.5)
                .phone("15600035913")
                .createTime(new Date());
        User save = userRepository.save(user);
        Assert.assertNotNull(save);
    }

}