package com.springbootcrud.springbootcrud;


import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.sonar.wsclient.SonarClient;
import org.sonar.wsclient.issue.Issue;
import org.sonar.wsclient.issue.IssueClient;
import org.sonar.wsclient.issue.IssueQuery;
import org.sonar.wsclient.issue.Issues;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringbootCrudApplication {

    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(SpringbootCrudApplication.class, args);
//        SonarClient sonarClient=SonarClient.builder()
//                .url("http://localhost:9000")
//                .login("admin")
//                .password("Interns@1234")
//                .build();
//
//
//
//
////        IssueClient issueClient= sonarClient.issueClient();
////        IssueQuery query=IssueQuery.create();
////        query.urlParams().put("project","com.springbootcrud:springbootCRUD");
////        Issues issues= issueClient.find(query);
////        List<Issue> issuesList = issues.list();
////
////        System.out.println(issuesList);
//
//
//
//        String sonarEndpoint="api/issues/search";
////        String sonarGet="api/analysis_reports/is_queue_empty";
//
//        Map<String, Object> sonarEndParams= new HashMap<>();
//        sonarEndParams.put("projects","com.springbootcrud:springbootCRUD");
//        sonarEndParams.put("types","BUG");
//        String sonarResponse=sonarClient.get(sonarEndpoint,sonarEndParams);
////        String sonarR=sonarClient.get(sonarGet);
////        System.out.println(sonarR);
//        System.out.println();
//        System.out.println();
//        PrintStream fileOut = new PrintStream("C:/Users/meet.patel/Desktop/New folder/out.txt");
//        System.setOut(fileOut);
//        System.out.println(sonarResponse);
//    }

    }
}
