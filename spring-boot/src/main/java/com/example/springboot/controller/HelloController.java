package com.example.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 這個類得所有方法返回的數據直接寫給瀏覽器,
 * 如果是對象轉為json
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello world quick start";
    }
}
