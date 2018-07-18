package com.example.topic03pp.repositories;

import com.example.topic03pp.models.Book;
import com.example.topic03pp.repositories.providers.BookProvider;
import com.example.topic03pp.utilities.Paginate;
import com.example.topic03pp.utilities.Pagination;
import com.example.topic03pp.utilities.filters.BookFilter;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository {

    @SelectProvider(type = BookProvider.class, method = "getAllProvider")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "cate_id", property = "category.id"),
            @Result(column = "name", property = "category.name")
    })
    List<Book> getAll();

    @SelectProvider(type = BookProvider.class, method = "bookFilterProvider")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "cate_id", property = "category.id"),
            @Result(column = "name", property = "category.name")
    })
    List<Book> bookFilter(BookFilter bookFilter);


    @Select("select * from tb_book b INNER JOIN tb_category c ON b.cate_id = c.id where b.id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "cate_id", property = "category.id"),
            @Result(column = "name", property = "category.name")
    })
    Book findOne(@Param("id") Integer id);


    @Update("update tb_book set title=#{title}, author=#{author}, publisher=#{publisher}, thumbnail=#{thumbnail}, cate_id=#{category.id} where id=#{id}")
    boolean update(Book book);

    @Delete("delete from tb_book where id=#{id}")
    boolean remove(Integer id);


    @Select("select count(*) from tb_book")
    Integer count();


    @InsertProvider(type = BookProvider.class, method = "create")
    @SelectKey(
            resultType = Integer.class,
            before = false,
            statementType = StatementType.PREPARED,
            keyProperty = "id",
            keyColumn = "curr_b_id",
            statement = "select currval('tb_book_id_seq') as curr_b_id"
    )
    boolean create(Book book);


    @Insert({
            "<script>" ,
                    "insert into tb_book(title, author, publisher, thumbnail, cate_id) values" ,
                    "<foreach collection='books' index='index' item='book' separator=','>" ,
                        "(","#{book.title}",
                            ",#{book.author}",
                            ",#{book.publisher}",
                            ",#{book.thumbnail}",
                            ",#{book.category.id}",
                        ")" ,
                    "</foreach>" ,
            "</script>"
    })
    boolean creates(@Param("books") List<Book> books);



    //all about pagination

    @SelectProvider(type = BookProvider.class, method = "countFilter")
    Integer countFilter(BookFilter bookFilter);

    /*
     *
     * TODO: get book filter with Pagination class
     *
     * */

    @SelectProvider(method = "getBookFilterPaginationProvider", type = BookProvider.class)
    @Results({
            @Result(column = "book_id", property = "id"),
            @Result(column = "book_title", property = "title"),
            @Result(column = "book_author", property = "author"),
            @Result(column = "book_publisher", property = "publisher"),
            @Result(column = "book_thumbnail", property = "thumbnail"),
            @Result(column = "category_id", property = "category.id"),
            @Result(column = "category_name", property = "category.name")
    })
    List<Book> getBookFilterPagination(@Param("bookFilter") BookFilter bookFilter,@Param("pagination") Pagination pagination);


    /*
     *
     * TODO: get book filter with Paginate class
     *
     * */

    @SelectProvider(method = "getBookFilterPaginateProvider", type = BookProvider.class)
    @Results({
            @Result(column = "book_id", property = "id"),
            @Result(column = "book_title", property = "title"),
            @Result(column = "book_author", property = "author"),
            @Result(column = "book_publisher", property = "publisher"),
            @Result(column = "book_thumbnail", property = "thumbnail"),
            @Result(column = "category_id", property = "category.id"),
            @Result(column = "category_name", property = "category.name")
    })
    List<Book> getBookFilterPaginate(@Param("bookFilter") BookFilter bookFilter,@Param("paginate") Paginate paginate);





}
