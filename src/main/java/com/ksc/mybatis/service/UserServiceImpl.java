package com.ksc.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksc.mybatis.dao.UserMapper;
import com.ksc.mybatis.model.User;

/**
 * ʹ��@Serviceע�⽫UserServiceImpl���עΪһ��service
 * service��id��userService
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    /**
     * ʹ��@Autowiredע���עuserMapper������
     * ����Ҫʹ��UserMapperʱ��Spring�ͻ��Զ�ע��UserMapper
     */
    @Autowired
    private UserMapper userMapper;//ע��dao

    public void addUser(User user) {
        userMapper.insert(user);
    }

    public User getUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
    
    public List<User> getAllUser() {
    	return userMapper.getAllUser();
    }
}