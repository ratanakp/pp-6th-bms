package com.example.topic03pp.repositories.providers;

import org.apache.ibatis.jdbc.SQL;

public class CategoryProvider {


    public String getBookByCateIdProvider(Integer id) {
        return new SQL(){{
            SELECT("*");
            FROM("tb_book b");
            WHERE("b.cate_id=#{id}");
        }}.toString();
    }
}
