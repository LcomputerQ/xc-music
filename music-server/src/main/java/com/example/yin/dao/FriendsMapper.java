package com.example.yin.dao;

import com.example.yin.domain.Friends;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FriendsMapper {
//    返回用户所有好友信息
    List<Friends> friAll(@Param("uid") Integer uid);

//    添加好友
    int addfris(@Param("uid") Integer uid,@Param("frid") Integer frid);
    int addfri(@Param("frid") Integer frid,@Param("uid") Integer uid);
}
