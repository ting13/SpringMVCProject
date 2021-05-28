package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {


    //@RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session){
            if(!StringUtils.isEmpty(username) && "123456".equals(password)){
                //登入成功，防止表單重複提交，可以重定向到主頁
                //登入成功，用戶就會在session存在
                session.setAttribute("loginUser", username);
                return "redirect:/main.html";
            }else {
                //登入失敗
                map.put("msg", "用戶名密碼錯誤");
                return "index";
            }
        }
}
