package com.example.yin.service;

import com.example.yin.domain.Consumer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsumerService {
    // 根据手机号码返回用户信息
    List<Consumer> numberAll(String phoneNum);
    //    改变用户在线状态
    int updataUserCode( int statusCode,Integer id);
    //    返回男女性别总数
    List<Consumer>getUserSex();
    //  返回在线人数
    List<Consumer>getUserCode();
    //    返回所有用户地址
    List<Consumer>getUserRegion();

    boolean addUser(Consumer consumer);

    boolean updateUserMsg(Consumer consumer);

    boolean updateUserAvator(Consumer consumer);

    boolean updatePassword(Consumer consumer);

    boolean existUser(String username);

    boolean veritypasswd(String username, String password);

    boolean deleteUser(Integer id);

    List<Consumer> allUser();

    List<Consumer> userOfId(Integer id);

    List<Consumer> loginStatus(String username);
    List<Consumer> nameAll(String username);
}
