package com.elt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PlaneApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(PlaneApp.class,args);
    }
}
