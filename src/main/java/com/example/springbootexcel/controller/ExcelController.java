package com.example.springbootexcel.controller;

import com.example.springbootexcel.dto.ExcelData;
import com.example.springbootexcel.dto.UserDto;
import com.example.springbootexcel.service.UserService;
import com.example.springbootexcel.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Value("${excel.table.head}")
    private String tableHead;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void excel(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("用户信息数据");
        String[] split = tableHead.split(",");
        List<String> strings = Arrays.asList(split);
        //添加表头
        List<String> titles = new ArrayList();
        //for(String title: excelInfo.getNames())
        strings.forEach(f -> {
            titles.add(f);
        });
        data.setTitles(titles);
        //添加列
        List<List<Object>> rows = new ArrayList();
        List<Object> row = null;
        long selectStartTime = new Date().getTime();
        List<UserDto> userInfo = userService.getUserInfo();
        long selectEndTime = new Date().getTime();
        System.out.println("查询一共用时：" + (selectEndTime - selectStartTime));
        for (UserDto userDto : userInfo) {
            row = new ArrayList();
            row.add(userDto.getName());
            row.add(userDto.getAge());
            row.add(userDto.getAddress());
            row.add(userDto.getPhone());
            row.add(userDto.getHeight());
            row.add(userDto.getGender());
            row.add(userDto.getCreateTime());
            rows.add(row);
        }
        data.setRows(rows);
        SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String fileName = fdate.format(new Date()) + ".xls";
        long startTime = new Date().getTime();
        ExcelUtils.exportExcel(response, fileName, data);
        long endTime = new Date().getTime();
        System.out.println("一共用时间：" + (endTime - startTime));
    }
}
