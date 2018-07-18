package com.example.topic03pp.controllers.restcontrollers;


import com.example.topic03pp.models.Book;
import com.example.topic03pp.models.Category;
import com.example.topic03pp.services.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryRestController {


    private CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    @ApiOperation(value = "Get All Categories", authorizations = {@Authorization(value = "basicAuth")})
    public Map<String, Object> getAll() {

        Map<String, Object> response = new HashMap<>();

        List<Category> categories = this.categoryService.getAll();

        if (categories != null) {
            response.put("categories", categories);
            response.put("record_count", categories.size());
            response.put("status", true);
        }
        else {
            response.put("record_count", categories.size());
            response.put("status", false);
        }


        return response;
    }


}
