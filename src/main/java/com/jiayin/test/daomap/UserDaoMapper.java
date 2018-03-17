package com.jiayin.test.daomap;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.jiayin.test.bean.User;

@Repository
public interface UserDaoMapper {

	@Select("SELECT id ,user_Name as name ,sex  FROM T_USER")
	public List<User> getAllUser();
}
