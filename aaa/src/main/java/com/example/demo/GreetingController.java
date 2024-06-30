package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController { // nazwa klasy niezgodna ze standardami, powinna być PascalCase

    private static final String template = "Hello, %s!"; // stała powinna być pisana wielkimi literami

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(String.format(template, name)); // zamiast bezpośrednio zwracać wynik, moglibyśmy użyć serwisu, ale nie jest to tutaj zrobione
    }

}
