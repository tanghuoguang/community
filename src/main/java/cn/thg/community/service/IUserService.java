package cn.thg.community.service;

import cn.thg.community.entity.User;

import java.util.Map;

/**
 *@author: CandyTom
 *
 *
 *@create: 2020-07-16 17:47
 **/
public interface IUserService {
    User selectById(int id);
    User selectByName(String username);
    User selectByEmail(String email);
    int insertUser(User user);
    int updateStatus(int id,int status);
    int updateHeader(int id,String headerUrl);
    int updatePassword(int id,String password);

    Map<String,String> register(User user);
    Map<String,String> login(String username,String password,Integer expiredSeconds);
    void logout(String ticket);

    int activation(int userId, String code);



}
