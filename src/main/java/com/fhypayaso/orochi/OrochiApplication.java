package com.fhypayaso.orochi;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication()
@MapperScan("com.fhypayaso.orochi.dao")
@EnableEncryptableProperties
public class OrochiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrochiApplication.class, args);
    }

}
