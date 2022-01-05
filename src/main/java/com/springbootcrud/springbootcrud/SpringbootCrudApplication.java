package com.springbootcrud.springbootcrud;


import org.sonar.wsclient.SonarClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCrudApplication.class, args);
        SonarClient sonarClient=SonarClient.builder()
                .url("http://localhost:9000")
                .login("admin")
                .password("Interns@1234")
                .build();
        String sonarEndpoint="/api/webservices/list";

        String sonarResponse=sonarClient.get(sonarEndpoint);
        System.out.println(sonarResponse);
    }

}
