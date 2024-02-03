package pl.sda.carrental.security;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/manager").setViewName("manager");
        registry.addViewController("/employee").setViewName("employee");
    }
}