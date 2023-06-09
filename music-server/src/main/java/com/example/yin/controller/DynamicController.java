package com.example.yin.controller;

import com.example.yin.dao.CommentsMapper;
import com.example.yin.domain.Comments;
import com.example.yin.domain.Dynamics;
import com.example.yin.service.impl.DynamicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DynamicController {
    @Autowired
    private DynamicServiceImpl dynamicService;
    @Autowired
    private CommentsMapper commentsMapper;

    @ResponseBody
    @RequestMapping(value = "/dynamic/all", method = RequestMethod.GET)
    public List<Dynamics> DynamicAll(){
        return dynamicService.DynamicAll();
    }
//返回个人动态信息
    @ResponseBody
    @RequestMapping(value = "/dynamic/byid", method = RequestMethod.GET)
    public List<Dynamics> DynamicAll(@RequestParam("uid")Integer uid){
        return dynamicService.DynamicById(uid);
    }
    @PostMapping("/dynamic/del")
    public String delDynamic(@RequestParam("id") Integer id){
        commentsMapper.deleteCommentById(id);
        return  dynamicService.deleteDynamic(id)==1?"success":"error";
    }

}
