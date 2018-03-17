package com.jiayin.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiayin.test.bean.User;
import com.jiayin.test.daomap.UserDaoMapper;

@Service
public class UserService {

	@Autowired
	private UserDaoMapper userDaoMapper;
	
	public List<User> getAllUser(){
		return userDaoMapper.getAllUser();
	}
	
}
