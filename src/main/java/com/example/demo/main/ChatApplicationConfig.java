package com.example.demo.main;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.example.demo"})
@EntityScan({"com.example.demo.domain"})
@EnableJpaRepositories({"com.example.demo.repository"})
@Import(SpringDataRestConfiguration.class)
public class ChatApplicationConfig {
}
