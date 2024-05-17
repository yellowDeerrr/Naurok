package org.example.naurok.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main");

        registry.addViewController("/auth/login").setViewName("auth/login");
        registry.addViewController("/auth/register").setViewName("auth/register");

        registry.addViewController("/test/create").setViewName("test/createTest");
        registry.addViewController("/test/get/{uuid}").setViewName("test/test");
        registry.addViewController("/test/complete/{uuid}").setViewName("test/testComplete");

        registry.addViewController("/account").setViewName("account/account");
        registry.addViewController("/account/tests").setViewName("account/tests");

    }

}