package com.lansea.wms;

import com.lansea.wms.util.BeanUtil;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import sun.awt.AppContext;

@SpringBootApplication
public class WmsApplication {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(WmsApplication.class, args);
    }

}
