package com.secure.secureAPP.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping
    ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello World From Secured application");
    }
}
