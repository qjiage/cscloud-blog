package com.jiayin.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jiayin.test.bean.User;
import com.jiayin.test.daomap.UserDaoMapper;

@Service
public class UserService {

	@Autowired
	private UserDaoMapper userDaoMapper;
	
	/**
	 * 测试 redis 缓存
	 * @return
	 */
	@Cacheable(value="getAllUser")
	public List<User> getAllUser(){
		System.out.println("如果第二次请求没有打印此方法，说明走了缓存，没有查询DB!");
		return userDaoMapper.getAllUser();
	}
	
}
