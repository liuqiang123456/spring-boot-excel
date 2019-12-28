package com.example.springbootexcel.controller;

import com.alibaba.fastjson.JSON;
import com.example.springbootexcel.dto.UserDto;
import com.example.springbootexcel.service.UserService;
import com.example.springbootexcel.utils.RandomValue;
import com.example.springbootexcel.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/personnel")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public UserVo saveUserInfo(@RequestBody UserVo userVo) {
        log.info("添加人员信息入参 userVo：{}", JSON.toJSONString(userVo));
        if (ObjectUtils.isEmpty(userVo)) {
            throw new RuntimeException("params is empty.");
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userVo, userDto);
        UserDto userDto1 = userService.saveUser(userDto);
        BeanUtils.copyProperties(userDto1, userVo);
        log.info("返回值vo---userVo：{}", JSON.toJSONString(userVo));
        return userVo;
    }


    @GetMapping("/user/userInfo/all")
    public List<UserVo> getUserInfo() {
        List<UserDto> userInfo = userService.getUserInfo();
        List<UserVo> userVoList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(userInfo)) {
            userInfo.forEach(userDto -> {
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(userDto, userVo);
                userVoList.add(userVo);
            });
        }
        return userVoList;
    }


    @PostMapping("/user/users")
    public void batchSave(){
        List<UserDto> userDtoList = Lists.newArrayList();
        for (int i = 0; i < 10000; i++) {
            UserDto userDto = new UserDto();
            userDto.setAge(new Double(Math.random() * 100).intValue());
            userDto.setAddress(RandomValue.getRoad());
            userDto.setGender(new java.util.Random().nextInt(2) + 1);
            double v = new Double(Math.random() * 100).doubleValue();
            userDto.setHeight((double) Math.round(v * 100) / 100);
            userDto.setPhone(RandomValue.getTel());
            userDto.setName(RandomValue.getChineseName());
            userDto.setCreateTime(new Date());
            userDtoList.add(userDto);
        }
        userService.batchSave(userDtoList);
    }

}
