package com.example.yin.service.impl;

import com.example.yin.dao.CollectionsMapper;
import com.example.yin.domain.Collections;
import com.example.yin.service.CollectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionsServiceImpl implements CollectionsService {
    @Autowired
    CollectionsMapper collectionMapper;
    @Override
    //用户添加收藏歌曲
    public int AddCollection(Integer uId, Integer songId,Integer type) {
        int flar = 0;
        flar =  collectionMapper.userAddCollection(uId,songId,type);
        return flar;
    }
    //返回指定用户的收藏歌曲
    @Override
    public List<Collections> selectAll(Integer uId) {
        return collectionMapper.selectAll(uId);
    }
    // 用户指定删除收藏歌曲
    @Override
    public int delSongByUId(Integer uId, Integer songId) {
        return collectionMapper.delSongByUId(uId,songId);
    }

    @Override
    public String CollectionSongById(Integer uId, Integer songId) {
        return collectionMapper.CollectionSongById(uId, songId);
    }
}
