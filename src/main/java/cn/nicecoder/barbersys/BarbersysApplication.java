package cn.nicecoder.barbersys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.nicecoder.barbersys.mapper")
public class BarbersysApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarbersysApplication.class, args);
    }

}
