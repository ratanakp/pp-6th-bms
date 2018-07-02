package com.example.topic03pp.services;

import com.example.topic03pp.models.Book;
import com.example.topic03pp.models.filters.BookFilter;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    List<Book> bookFilter(BookFilter bookFilter);

    Book findOne(Integer id);

    boolean update(Book book);

    boolean remove(Integer id);

    Integer count();

    boolean create(Book book);

    boolean creates(List<Book> books);
}
