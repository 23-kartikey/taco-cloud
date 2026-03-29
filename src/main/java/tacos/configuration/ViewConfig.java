package tacos.configuration;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViewConfig implements WebMvcConfigurer{
    
    @Override
    public void addViewControllers(@NonNull ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("home");
    }

}
