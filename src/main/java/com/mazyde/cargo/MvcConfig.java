package com.mazyde.cargo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

//    @Bean
//    public ViewResolver viewResolver() {
//        ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
//        resolver.setBasename("views");
//        // Thymeleaf is running at -20
//        resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 40);
//        return resolver;
//    }

//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/home").setViewName("home");
//        registry.addViewController("/").setViewName("home");
//        registry.addViewController("/hello").setViewName("hello");
//        registry.addViewController("/login").setViewName("login");
//    }

}
