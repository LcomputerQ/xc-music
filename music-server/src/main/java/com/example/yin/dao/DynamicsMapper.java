package com.example.yin.dao;

import com.example.yin.domain.Dynamics;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DynamicsMapper {
//    返回所有动态信息
    List<Dynamics> DynamicAll();
//    用户添加动态
    int addDynamic(@Param("uid")Integer uid,@Param("uimg") String uimg, @Param("content") String content,@Param("type")String type,@Param("topicid") Integer topicid,@Param("contentUrl") String contentUrl);
//    返回用户个人动态
    List<Dynamics> DynamicById(@Param("uid")Integer uid);

    /**
     * 删除帖子
     * @param id
     * @return
     */
    int deleteDynamic(@Param("id")Integer id);
}
