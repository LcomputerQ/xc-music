package com.example.yin.service.impl;

import com.example.yin.dao.DynamicsMapper;
import com.example.yin.domain.Dynamics;
import com.example.yin.service.DynamicService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DynamicServiceImpl implements DynamicService {
    @Autowired
    DynamicsMapper dynamicsMapper;
    //    返回所有动态信息
    @Override
    public List<Dynamics> DynamicAll() {
        return dynamicsMapper.DynamicAll();
    }
    //    返回用户个人动态
    @Override
    public List<Dynamics> DynamicById(Integer uid) {
            return  dynamicsMapper.DynamicById(uid);
    }

    @Override
    public int deleteDynamic(Integer id) {
        return dynamicsMapper.deleteDynamic(id);
    }

}
