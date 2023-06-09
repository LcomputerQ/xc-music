package com.example.yin.dao;

import com.example.yin.domain.Consumer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerMapper {
//    改变用户在线状态
    int updataUserCode(@Param("statusCode") int statusCode,@Param("id") Integer id);
//    返回所有用户地址
    List<Consumer>getUserRegion();
//    返回男女性别总数
    List<Consumer>getUserSex();
//  返回在线人数
    List<Consumer>getUserCode();
    int deleteByPrimaryKey(Integer id);

    int insert(Consumer record);

    /**
     * 增加新用户
     * @param record - 用户信息
     * @return int
     */
    int insertSelective(Consumer record);
// 根据手机号码返回用户信息
    List<Consumer> numberAll(@Param("phoneNum") String phoneNum);
//    根据名字返回用户信息
    List<Consumer> nameAll(@Param("username") String username);
    Consumer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Consumer record);

    int updateByPrimaryKey(Consumer record);

    int verifyPassword(String username, String password);

    int existUsername(String username);

    int addUser(Consumer consumer);

    int updateUserMsg(Consumer record);

    int updateUserAvator(Consumer record);
// 修改密码
    int updatePassword(Consumer record);

    int deleteUser(Integer id);

    List<Consumer> allUser();

    List<Consumer> userOfId(Integer id);

    List<Consumer> loginStatus(String username);

}
