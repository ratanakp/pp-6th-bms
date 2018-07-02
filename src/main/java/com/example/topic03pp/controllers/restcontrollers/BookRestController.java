package com.example.topic03pp.controllers.restcontrollers;


import com.example.topic03pp.models.Book;
import com.example.topic03pp.models.filters.BookFilter;
import com.example.topic03pp.services.BookService;
import com.example.topic03pp.services.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/book")
@Api(description = "Book Rest Controller Description Custom")
@ApiResponses({
        @ApiResponse(code = 200, message = "OK Custom"),
        @ApiResponse(code = 404, message = "Not Found Custom")
})
public class BookRestController {

    private BookService bookService;
    private UploadService uploadService;

    public BookRestController(BookService bookService, UploadService uploadService) {
        this.bookService = bookService;
        this.uploadService = uploadService;
    }


    @GetMapping("")
    public List<Book> getAll() {
        return this.bookService.getAll();
    }

//    @ApiIgnore
    @GetMapping("/all/map")
    public Map<String, Object> getAllUsingMap() {

        Map<String, Object> response = new HashMap<>();

        List<Book> books = this.bookService.getAll();

        response.put("books", books);
        response.put("record_count", books.size());

        return response;
    }



    @GetMapping("/filter")
    @ApiOperation(value = "Search By CateId and Book Title")
    public Map<String, Object> getFilterUsingMap(BookFilter bookFilter) {

        Map<String, Object> response = new HashMap<>();

        List<Book> books = this.bookService.bookFilter(bookFilter);

        response.put("books", books);
        response.put("record_count", books.size());

        return response;
    }


    @PostMapping("")
    public Map<String, Object> create(@RequestBody Book book){

        Map<String, Object> response = new HashMap<>();

        boolean status = this.bookService.create(book);

        if (status) {
            response.put("status", status);
            response.put("book", book);
            response.put("message", "Create book ok looking good!!!");
        }
        else {
            response.put("status", status);
            response.put("message", "Create book not ok, not looking good!!!");
        }

        return response;
    }



    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable("id") Integer id){

        Map<String, Object> response = new HashMap<>();

        boolean status = this.bookService.remove(id);

        if (status) {
            response.put("status", status);
            response.put("message", "Delete book ok looking good!!!");
        }
        else {
            response.put("status", status);
            response.put("message", "Delete book not ok, not looking good!!!");
        }
        return response;
    }

    @GetMapping("/{id}")
    public Map<String, Object> findOne(@PathVariable("id") Integer id){

        Map<String, Object> response = new HashMap<>();

        Book book = this.bookService.findOne(id);

        if (book != null) {
            response.put("status", true);
            response.put("book", book);
            response.put("message", "Found book ok looking good!!!");
        }
        else {
            response.put("status", false);
            response.put("message", "Book not Found book not ok, not looking good!!!");
        }

        return response;
    }



    @PutMapping("")
    public Map<String, Object> update(@RequestBody Book book){

        Map<String, Object> response = new HashMap<>();

        boolean status = this.bookService.update(book);

        if (status) {
            response.put("status", true);
            response.put("message", "Update book ok looking good!!!");
        }
        else {
            response.put("status", false);
            response.put("message", "Update book not ok, not looking good!!!");
        }

        return response;
    }



    @PostMapping("/upload")
    public Map<String, Object> uploadFile(@RequestParam("home_file") MultipartFile file){

        Map<String, Object> response = new HashMap<>();

        String filename = this.uploadService.upload(file);

        if (filename != null) {
            response.put("status", true);
            response.put("message", "Upload file ok looking good!!!");
            response.put("filename", filename);
        }
        else {
            response.put("status", false);
            response.put("message", "Upload file not ok, not looking good!!!");
        }

        return response;
    }


    @PostMapping("/multi")
    public Map<String, Object> creates(@RequestBody List<Book> books){

        Map<String, Object> response = new HashMap<>();

        boolean status = this.bookService.creates(books);

        if (status) {
            response.put("status", status);
            response.put("books", books);
            response.put("message", "Create book ok looking good!!!");
        }
        else {
            response.put("status", status);
            response.put("message", "Create book not ok, not looking good!!!");
        }

        return response;
    }


}
