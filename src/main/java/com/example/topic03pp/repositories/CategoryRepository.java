package com.example.topic03pp.repositories;

import com.example.topic03pp.models.Book;
import com.example.topic03pp.models.Category;
import com.example.topic03pp.repositories.providers.CategoryProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository {

    @Select("select * from tb_category order by id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(property = "books", column = "id", many = @Many(select = "getBookByCateId"))
    })
    List<Category> getAll();


    @SelectProvider(method = "getBookByCateIdProvider", type = CategoryProvider.class)
    List<Book> getBookByCateId(@Param("id") Integer id);


    @Select("select count(*) from tb_category")
    Integer count();

}
