package com.ksc.mybatis.service;

import java.util.List;

import com.ksc.mybatis.model.User;

public interface UserService {
	/**
     * ����û�
     * @param user
     */
    void addUser(User user);
    
    /**
     * �����û�id��ȡ�û�
     * @param userId
     * @return
     */
    User getUserById(String userId);
    
    /**ȡ�������û��б�
     * @return List<User>
     */
    List<User> getAllUser();

}
