package com.ksc.mybatis.service;

import java.util.List;

import com.ksc.mybatis.model.User;

public interface UserService {
	/**
     * 添加用户
     * @param user
     */
    void addUser(User user);
    
    /**
     * 根据用户id获取用户
     * @param userId
     * @return
     */
    User getUserById(String userId);
    
    /**取得所有用户列表
     * @return List<User>
     */
    List<User> getAllUser();

}
