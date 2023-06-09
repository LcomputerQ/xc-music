package com.example.yin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int addUser(@Param("userName")String userName,@Param("password")String password,@Param("userPhoneNumber")String userPhoneNumber);
}
