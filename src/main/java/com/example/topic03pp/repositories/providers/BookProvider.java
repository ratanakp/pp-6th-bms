package com.example.topic03pp.repositories.providers;

import com.example.topic03pp.models.Book;
import com.example.topic03pp.models.filters.BookFilter;
import org.apache.ibatis.jdbc.SQL;

public class BookProvider {

    public String getAllProvider() {
        return new SQL() {{
            SELECT("*");
            FROM("tb_book b");
            INNER_JOIN("tb_category c ON b.cate_id = c.id");
            ORDER_BY("b.id desc");
        }}.toString();
    }


    public String create(Book book) {
        return new SQL() {{
            INSERT_INTO("tb_book");
            VALUES("title", "#{title}");
            VALUES("author", "#{author}");
            VALUES("publisher", "#{publisher}");
            VALUES("thumbnail", "#{thumbnail}");
            VALUES("cate_id", "#{category.id}");

        }}.toString();
    }


    public String bookFilterProvider(BookFilter bookFilter) {
        return new SQL() {{
            SELECT("*");
            FROM("tb_book b");
            INNER_JOIN("tb_category c ON b.cate_id = c.id");

            if (bookFilter.getCateId() != null)
                WHERE("c.id = #{cateId}");

            if (bookFilter.getBookTitle() != null)
                WHERE("b.title iLIKE '%' || #{bookTitle} || '%'");

            ORDER_BY("b.id desc");

        }}.toString();
    }


}
