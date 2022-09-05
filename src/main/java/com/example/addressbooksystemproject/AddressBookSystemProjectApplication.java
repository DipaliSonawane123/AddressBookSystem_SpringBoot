package com.example.addressbooksystemproject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@Slf4j
public class AddressBookSystemProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressBookSystemProjectApplication.class, args);
        System.out.println("Hello!!");
        log.info("Application Loaded Successfully.");
    }

}
