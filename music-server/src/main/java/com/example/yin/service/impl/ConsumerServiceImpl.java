package com.example.yin.service.impl;

import com.example.yin.dao.ConsumerMapper;
import com.example.yin.domain.Consumer;
import com.example.yin.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public List<Consumer> numberAll(String phoneNum) {
        String phone = phoneNum+"%";
        return consumerMapper.numberAll(phone);
    }

    //    改变用户在线状态
    @Override
    public int updataUserCode(int statusCode, Integer id) {
        return consumerMapper.updataUserCode(statusCode, id);
    }

    //    返回男女性别总数
    @Override
    public List<Consumer> getUserSex() {
        return consumerMapper.getUserSex();
    }
    //  返回在线人数
    @Override
    public List<Consumer> getUserCode() {
        return consumerMapper.getUserCode();
    }

    //<!--  返回所有用户地址-->
    @Override
    public List<Consumer> getUserRegion() {
        return consumerMapper.getUserRegion();
    }

    /**
     * 新增用户
     */
    @Override
    public boolean addUser(Consumer consumer) {
        return consumerMapper.insertSelective(consumer) > 0;
    }

    @Override
    public boolean updateUserMsg(Consumer consumer) {
        return consumerMapper.updateUserMsg(consumer) > 0;
    }

    @Override
    public boolean updatePassword(Consumer consumer) {
        return consumerMapper.updatePassword(consumer) > 0;
    }

    @Override
    public boolean updateUserAvator(Consumer consumer) {
        return consumerMapper.updateUserAvator(consumer) > 0;
    }

    @Override
    public boolean existUser(String username) {
        return consumerMapper.existUsername(username) > 0;
    }

    @Override
    public boolean veritypasswd(String username, String password) {
        return consumerMapper.verifyPassword(username, password) > 0;
    }

    // 删除用户
    @Override
    public boolean deleteUser(Integer id) {
        return consumerMapper.deleteUser(id) > 0;
    }

    @Override
    public List<Consumer> allUser() {
        return consumerMapper.allUser();
    }

    @Override
    public List<Consumer> userOfId(Integer id) {
        return consumerMapper.userOfId(id);
    }

    @Override
    public List<Consumer> loginStatus(String username) {
        return consumerMapper.loginStatus(username);
    }

    @Override
    public List<Consumer> nameAll(String username) {
        return consumerMapper.nameAll(username);
    }
}
