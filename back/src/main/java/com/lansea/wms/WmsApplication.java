package com.lansea.wms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
public class WmsApplication {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(WmsApplication.class, args);
        System.out.println("可访问网站地址：http://localhost:9102/");
    }

}
