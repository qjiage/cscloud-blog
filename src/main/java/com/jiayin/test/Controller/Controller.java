package com.jiayin.test.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiayin.test.bean.User;
import com.jiayin.test.service.UserService;

@RestController
@RequestMapping("/demo")
public class Controller {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/getAllUser")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}
	
}
