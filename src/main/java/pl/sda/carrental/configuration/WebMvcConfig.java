package pl.sda.carrental.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.sda.carrental.model.dataTransfer.converters.EmployeeDTOConverter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final EmployeeDTOConverter employeeDTOConverter;

    public WebMvcConfig(EmployeeDTOConverter employeeDTOConverter) {
        this.employeeDTOConverter = employeeDTOConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(employeeDTOConverter);
        WebMvcConfigurer.super.addFormatters(registry);
    }
}
