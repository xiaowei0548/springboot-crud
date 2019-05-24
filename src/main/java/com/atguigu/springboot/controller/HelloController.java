package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    /*@RequestMapping({"/","/index.html"})
    public String index() {
        return "index";
    }*/

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        //classpath:/templates/success.html
        map.put("hello","<h1>你好 小伟</h1>");
        map.put("users", Arrays.asList("zhangsan","李四","陈红伟"));
        return  "success";
    }

}
