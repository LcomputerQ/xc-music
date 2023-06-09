package com.example.yin.service;

import com.example.yin.domain.Collections;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionsService {
    //用户添加收藏歌曲
    int AddCollection(Integer uId,Integer songId,Integer type);
    //返回指定用户的收藏歌曲
    List<Collections> selectAll(Integer uId);
    // 用户指定删除收藏歌曲
    int delSongByUId(Integer uId,Integer songId);

    //搜索收藏歌曲
    String CollectionSongById(Integer uId, Integer songId);
}
