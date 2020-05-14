package com.yourBouquet.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Date;

@Configuration
public class CommonConfig {
    @Bean
    public ObjectMapper objectMapper(){
        //System.out.println("CREATING WorkShiftOptaplannerRestService BEAN");
        //AOP logging
        return new ObjectMapper();
    }

    @Bean
    @Scope("prototype")
    public Date date(){
        //System.out.println("CREATING WorkShiftOptaplannerRestService BEAN");
        //AOP logging
        return new Date();
    }

}
