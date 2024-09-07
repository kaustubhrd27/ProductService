package com.example.productservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// This controller supports REST API'S (HTTP Requests)
// The request coming to endpoint /Hello, transfers to this contoller
@RequestMapping("/Hello")
public class SampleAPIController {
    @GetMapping("/{name}/{city}")
    public String sayHello(@PathVariable("name") String name, @PathVariable("city") String city) {
        return "Hello World" + name + " " + city;
    }
    public void fun() {}
    public void fun2() {}
}
