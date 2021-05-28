package com.example.springboot.config;

import com.example.springboot.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig  implements   WebMvcConfigurer{

    /*
    自动配置类WebMvcAutoConfiguration中最上面有一个@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
这个的意思是只有当WebMvcConfigurationSupport类不存在的时候才会生效WebMvc自动化配置。
    extends WebMvcConfigurationSupport
    */

    ///springboot請求去success頁面
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/springboot").setViewName("success");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    //註冊攔截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //SpringBoot已經做好靜態資源映射, 靜態資源不用攔截
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/index.html", "/user/login");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    }
    //    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("/webjars/");
////        registry.addResourceHandler("/asserts/**")
////                .addResourceLocations("/asserts/");
//            registry.addResourceHandler("/img/**")
//                    .addResourceLocations("/img/");
//        registry.addResourceHandler("/**")
//                .addResourceLocations("/");
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//    }
}
