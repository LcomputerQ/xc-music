package com.example.yin.service;

import com.example.yin.domain.Send;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SendService {
    //    返回好友聊天记录
    List<Send> mySendAll(Integer uid,Integer frid);
    //    我发送信息
    int addMySend(Integer uid,Integer frid,String mycontent);
    //    收到好友信息
    int addfrSend(Integer uid,Integer frid,String frcontent);
}
