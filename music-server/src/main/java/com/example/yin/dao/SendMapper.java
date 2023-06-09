package com.example.yin.dao;

import com.example.yin.domain.Send;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SendMapper {
//    返回好友聊天记录
    List<Send> mySendAll(@Param("uid") Integer uid,@Param("frid") Integer frid);
//    我发送信息
    int addMySend(@Param("uid") Integer uid,@Param("frid") Integer frid,@Param("mycontent") String mycontent);
//    收到好友信息
    int addfrSend(@Param("uid") Integer uid,@Param("frid") Integer frid,@Param("mycontent") String frcontent);
}
