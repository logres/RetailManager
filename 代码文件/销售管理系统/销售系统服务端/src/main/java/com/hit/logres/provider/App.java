package com.hit.logres.provider;

import com.hit.logres.api.entity.*;
import com.hit.logres.api.service.*;
import com.hit.logres.provider.serviceimplement.CustomerServiceImp;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.apache.dubbo.container.Main;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan(basePackages = "com.hit.logres.provider.mapper")
@EnableDubbo
public class App
{
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        Main.main(args);
        System.out.println("sayHello");
    }
}
