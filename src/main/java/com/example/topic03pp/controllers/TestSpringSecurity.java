package com.example.topic03pp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestSpringSecurity {

    @GetMapping("/admin")
    public String adminPage() {
        return "test-security/admin";

    }

    @GetMapping("/dba")
    public String dbaPage() {
        return "test-security/dba";

    }


    @GetMapping("/user")
    public String userPage() {
        return "test-security/user";

    }
}
