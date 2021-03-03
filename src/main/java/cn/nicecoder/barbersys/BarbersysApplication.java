package cn.nicecoder.barbersys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class BarbersysApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarbersysApplication.class, args);
    }

}
