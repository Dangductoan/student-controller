package com.cybersoft.demoSpring.config;

import com.cybersoft.demoSpring.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoBeanConfig {
    @Bean
//    @Qualifier("fefe") goi ten bean voi ten chi dinh
    public User createBeanUser() {
        User user= new User();
        user.setFullname("cybersoft");
        user.setAge(5);
        user.setGender(true);
        return user;

    }
}


