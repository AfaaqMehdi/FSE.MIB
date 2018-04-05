package com.mindtree.mib.springbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.mindtree.mib.springbootapp","com.mindtree.mib.playerstats.detailinfo","com.mindtree.mib.playerstats.detailinfo.controller","com.mindtree.mib.playerstats.detailinfo.dao","com.mindtree.mib.playerstats.detailinfo.exception"}) 
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}