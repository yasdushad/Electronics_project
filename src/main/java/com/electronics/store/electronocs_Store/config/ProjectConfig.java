package com.electronics.store.electronocs_Store.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    ModelMapper mapper(){
        return new ModelMapper();
    }
}
