package com.example.yin.service;

import com.example.yin.domain.Friends;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendsService {
    //    返回用户所有好友信息
    List<Friends> friAll(Integer uid);
}
