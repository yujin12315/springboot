package com.example.demo.controller;

import com.example.demo.common.ResultCodeEnum;
import com.example.demo.common.Vo.R;
import com.example.demo.common.Vo.ResponseJson;
import com.example.demo.service.CourseService;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private FileService fileService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @RequestMapping("/get")
    public ResponseJson getById() throws InterruptedException {

        return courseService.getList();
    }

    @RequestMapping("/add")
    public R add() {
        return R.ok().data("result", "hello");
    }

    @RequestMapping("/update")
    public ResponseJson getFile() {

        return fileService.getFile();
    }

    @RequestMapping("/delete")
    public R delete() {
        return R.ok().data("result", "hello");
    }

    @RequestMapping("/abc")
    public String index() {

        return "hello word";
    }

    @RequestMapping("/test")
    public R result() {
        //return R.ok().data("result", "hello");
        return R.setResult(ResultCodeEnum.SUCCESS);
    }

    @RequestMapping("/redis")
    public String redis() {
        Long l  = stringRedisTemplate.opsForValue().increment("hello");

        return "success"+l + "count";
    }


}
