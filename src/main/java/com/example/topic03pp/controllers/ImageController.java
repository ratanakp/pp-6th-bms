package com.example.topic03pp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {


    @GetMapping("/image/add")
    public String showFormAddImage() {
        return "images/add-image";
    }

}
