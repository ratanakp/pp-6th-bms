package com.example.topic03pp.services;

import com.example.topic03pp.models.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Integer count();
}
