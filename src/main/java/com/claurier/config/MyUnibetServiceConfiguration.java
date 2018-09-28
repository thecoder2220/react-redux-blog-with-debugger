package com.claurier.config;


import com.claurier.modules.user.services.UserService;
import com.claurier.modules.user.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyUnibetServiceConfiguration {

    @Bean
    public UserService getUserService() {
        return new UserServiceImpl();
    }

}
