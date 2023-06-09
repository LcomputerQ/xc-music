package com.example.yin.controller;

import com.example.yin.domain.Friends;
import com.example.yin.service.impl.FriendsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FriendsController {
    @Autowired
    private FriendsServiceImpl friendsService;
    @ResponseBody
    @RequestMapping(value = "/friends/all", method = RequestMethod.GET)
    public List<Friends> FriendAll(@RequestParam("uid") Integer uid){
       return friendsService.friAll(uid);
    }
}
