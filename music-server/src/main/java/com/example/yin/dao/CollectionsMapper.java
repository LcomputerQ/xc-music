package com.example.yin.dao;

import com.example.yin.domain.Collections;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionsMapper {
//用户添加收藏歌曲
int userAddCollection(@Param("uId") Integer uId,@Param("songId") Integer songId,@Param("type")Integer type);
//返回指定用户的收藏歌曲
List<Collections> selectAll(Integer uId);
// 用户指定删除收藏歌曲
int delSongByUId(@Param("uId") Integer uId,@Param("songId") Integer songId);
//搜索收藏歌曲
String CollectionSongById(@Param("uId") Integer uId,@Param("songId") Integer songId);
}
