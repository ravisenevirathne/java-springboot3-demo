package com.ravi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/123")
    public GreetResponse greet(){
        GreetResponse response = new GreetResponse(
             "Hello",
            List.of("Java","Go","JScript"),
            new Person("Alex",28,1000)
        );
        return response;
    }

    record Person (String name, int age, double savings){}

    record GreetResponse(
        String greet,
        List<String> ProgLang,
        Person person){}

}
