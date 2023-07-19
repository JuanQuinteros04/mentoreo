package com.mentoresalumnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroservicioAlumnosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioAlumnosApplication.class, args);
    }

}
