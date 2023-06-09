package com.example.yin.service.impl;

import com.example.yin.dao.SendMapper;
import com.example.yin.domain.Send;
import com.example.yin.service.SendService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SendServeImpl implements SendService {
    @Autowired
    private SendMapper sendMapper;
    //    返回好友聊天记录
    @Override
    public List<Send> mySendAll(Integer uid, Integer frid) {
        return sendMapper.mySendAll(uid, frid);
    }

    @Override
    public int addMySend(Integer uid, Integer frid, String mycontent) {
        return sendMapper.addMySend(uid, frid, mycontent);
    }

    @Override
    public int addfrSend(Integer uid, Integer frid, String frcontent) {
        return sendMapper.addfrSend(uid, frid, frcontent);
    }
}
