package com.example.yin.service;

import com.example.yin.domain.Dynamics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicService {
    //    返回所有动态信息
    List<Dynamics> DynamicAll();
    //    返回用户个人动态
    List<Dynamics> DynamicById(Integer uid);
    /**
     * 删除帖子
     * @param id
     * @return
     */
    int deleteDynamic(Integer id);
}
