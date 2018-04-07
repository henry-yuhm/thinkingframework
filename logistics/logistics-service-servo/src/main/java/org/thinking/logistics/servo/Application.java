package org.thinking.logistics.servo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thinkingframework.boot.mvc.AppWebMvcConfigurerAdapter;

@SpringBootApplication
public class Application extends AppWebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}