package com.daimler.emst2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@SpringBootApplication
@EnableAspectJAutoProxy
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FhiApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FhiApplication.class);
        app.run(args);
    }

}
