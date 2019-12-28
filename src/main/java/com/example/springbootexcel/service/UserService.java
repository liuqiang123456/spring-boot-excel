package com.example.springbootexcel.service;

import com.alibaba.fastjson.JSON;
import com.example.springbootexcel.dao.UserRepository;
import com.example.springbootexcel.dto.UserDto;
import com.example.springbootexcel.model.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDto saveUser(UserDto userDto) {
        if (ObjectUtils.isEmpty(userDto)) {
            throw new RuntimeException("params is empty.");
        }
        if (ObjectUtils.isEmpty(userDto.getId())) {
            userDto.setId(null);
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        try {
            user.setCreateTime(new Date());
            User saveUser = userRepository.save(user);
            UserDto userDtoResult = new UserDto();
            BeanUtils.copyProperties(saveUser, userDtoResult);
            return userDtoResult;
        } catch (Exception e) {
            log.warn("insert user data exception. userDto:{}  user:{}", JSON.toJSONString(userDto), JSON.toJSONString(user));
            throw new RuntimeException("insert user data exception.");
        }
    }


    public List<UserDto> getUserInfo(){
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = Lists.newArrayList();
        userList.forEach(user -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user,userDto);
            userDtoList.add(userDto);
        });
        return userDtoList;
    }


    public void batchSave(List<UserDto> userDtoList) {
        List<User> users = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(userDtoList)) {
            userDtoList.forEach(userDto -> {
                User user = new User();
                BeanUtils.copyProperties(userDto, user);
                users.add(user);
            });
        }
        userRepository.saveAll(users);
    }
}
