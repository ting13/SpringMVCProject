package com.example.springboot.controller;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/*
 * 這個類得所有方法返回的數據直接寫給瀏覽器,
 * 如果是對象轉為json
 */
@Controller
public class HelloController {

    //改為在MyMvcConfig添加
//    @RequestMapping({"/","/index.html"})
//    public String index(){
//        return "index";
//    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "hello world quick start";
    
    }
    
  @RequestMapping("/success")
  public String success(Map<String, Object> map) {
      map.put("hello", "你好");
      map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu"));
      return "success";
  }
  
  @Bean
  public ViewResolver myViewResolver() {
    return new MyViewResolver();
      
  }
  
  //
  private class MyViewResolver implements ViewResolver{
     
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        return null;
    }
      
  } 
  
}
