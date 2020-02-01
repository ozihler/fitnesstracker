package com.zihler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.out.println("Starts App with args: "+args);
        SpringApplication.run(Application.class, args);
    }

}
