package coba.daily.you.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("file:${C:/Users/lenovo//Documents/INTAN/TUGAS/MP/you/src/main/resources/}clients.properties")
public class WebConfig implements WebMvcConfigurer
{
    @Value("*")
    private String allowedHosts;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(allowedHosts.split(",")).allowedMethods("POST", "PUT", "DELETE", "GET");

    }
}
