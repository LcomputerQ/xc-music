package com.example.yin.dao;

import com.example.yin.domain.orders;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ordersMapper {
//    查看所有订单
    List<orders> ordersAll();
//    查看个人订单
    List<orders> ordersById(@Param("user_id") Integer user_id);
//    删除订单
    int ordersdel(@Param("id")Integer id);
}
