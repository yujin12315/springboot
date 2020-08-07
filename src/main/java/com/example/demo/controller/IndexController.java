package com.example.demo.controller;

import com.example.demo.common.Vo.R;
import com.example.demo.common.Vo.ResponseJson;
import com.example.demo.service.CourseService;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/admin/index")
public class IndexController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private FileService fileService;


    @RequestMapping("/getinfo")
    public ResponseJson getById() throws InterruptedException {

            System.out.println(Thread.currentThread().getName() + "-->" );
            //休眠一秒钟
//            try {
//                Thread.sleep(150);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        return courseService.getList();
    }

    @RequestMapping("/getfile")
    public ResponseJson getFile() {

        return fileService.getFile();
    }

    @RequestMapping("/abc")
    public String index() {

        return "hello word";
    }

    @RequestMapping("/test")
    public R result() {

        return R.ok().data("result", "hello");
    }



}
