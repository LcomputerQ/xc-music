package com.example.yin.controller;
import com.example.yin.domain.Collections;
import com.example.yin.service.impl.CollectionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CollectionsController {
    @Autowired
    private CollectionsServiceImpl collectionService;

    @ResponseBody
    @RequestMapping(value = "/collections/add", method = RequestMethod.POST)
    public String addCollection(@RequestParam("uid") Integer uId, @RequestParam("songid") Integer songId,@RequestParam("type") Integer type){
        System.out.println("uId = " + uId);
        System.out.println("songId = " + songId);
         if(uId!=null){
             collectionService.AddCollection(uId, songId,type);
             return "收藏成功";
         }
         return "请登录";
    }
    @ResponseBody
    @RequestMapping(value = "/collections/all", method = RequestMethod.POST)
    public List<Collections> All(@RequestParam(value = "uid") Integer uId){
//        System.out.println("uId = " + uId);
        List<Collections> collections = collectionService.selectAll(uId);
        if(collections.size()!=0){
            return collections;
        }
        return null;
    }
    @ResponseBody
    @RequestMapping(value = "/collections/del", method = RequestMethod.POST)
    public int del(@RequestParam("uid") Integer uId, @RequestParam("songid") Integer songId){
//        1.代表删除成功
        int flar = 0 ;
        flar = collectionService.delSongByUId(uId, songId);
        return flar;
    }
    @ResponseBody
    @RequestMapping(value = "/collections/selectById", method = RequestMethod.GET)
    public String selectById(@RequestParam("uid") Integer uId, @RequestParam("songid") Integer songId){
       String flag = "";
        flag = collectionService.CollectionSongById(uId, songId);
        if(flag==null){
            return "0";
        }
        return flag;
    }
}
