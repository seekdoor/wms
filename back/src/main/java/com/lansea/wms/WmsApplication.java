package com.lansea.wms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WmsApplication {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(WmsApplication.class, args);
    }

}
