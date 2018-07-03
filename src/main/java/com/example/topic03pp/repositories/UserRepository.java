package com.example.topic03pp.repositories;

import com.example.topic03pp.models.Role;
import com.example.topic03pp.models.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    @Select("select id, username, password, status, profile_img " +
            "from tb_user where username=#{username}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "profileImg", column = "profile_img"),
            @Result(property = "roles", column = "id", many = @Many(select = "getRolesByUserId"))
    })
    User loadUserByUsername(@Param("username") String username);


    @Select("select * " +
            "from tb_role tr inner join tb_user_role tur on tr.id = tur.role_id\n" +
            "where tur.user_id=#{id}")
    List<Role> getRolesByUserId(@Param("id") Integer id);




}
