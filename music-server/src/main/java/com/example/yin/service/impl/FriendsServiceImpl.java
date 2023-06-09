package com.example.yin.service.impl;

import com.example.yin.dao.FriendsMapper;
import com.example.yin.domain.Friends;
import com.example.yin.service.FriendsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FriendsServiceImpl implements FriendsService {
    @Autowired
    private FriendsMapper friendsMapper;
    //    返回用户所有好友信息
    public List<Friends> friAll(Integer uid) {
        System.out.println(" = " +friendsMapper.friAll(uid) );
        return friendsMapper.friAll(uid);
    }
}
