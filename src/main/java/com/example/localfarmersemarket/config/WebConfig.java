package com.example.localfarmersemarket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/login-images/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/login-images/");
        registry.addResourceHandler("/smart-basket/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/smart-basket/");
        registry.addResourceHandler("/atta-images/**")
                .addResourceLocations("file:"+System.getProperty("user.dir")+"/atta-images/");
        registry.addResourceHandler("/dals-images/**")
                .addResourceLocations("file:"+System.getProperty("user.dir")+"/dals-images/");
        registry.addResourceHandler("/exotic-images/**")
                .addResourceLocations("file:"+System.getProperty("user.dir")+"/exotic-images/");
        registry.addResourceHandler("/fruits-images/**")
                .addResourceLocations("file:"+System.getProperty("user.dir")+"/fruits-images/");
        registry.addResourceHandler("/veggies-images/**")
                .addResourceLocations("file:"+System.getProperty("user.dir")+"/veggies-images/");
        registry.addResourceHandler("/rice-images/**")
                .addResourceLocations("file:"+System.getProperty("user.dir")+"/rice-images/");

    }
}
