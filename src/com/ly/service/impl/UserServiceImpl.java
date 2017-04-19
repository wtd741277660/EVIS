package com.ly.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.dao.BaseDao;
import com.ly.dao.UserDao;
import com.ly.entity.User;
import com.ly.service.UserService;
import com.ly.util.Pagination;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User getByAccount(String account) {
		User user = userDao.getByAccount(account);
		return user;
	}

	@Override
	public Pagination<User> queryList(Map<String, Object> params,
			int pageIndex, int pageSize) {
		String hql = "from User where 1=1 ";
		for(String key:params.keySet()){
			if(key.equals("account")){
				hql += " and account like:account ";
			}else if(key.equals("name")){
				hql += " and name like:name ";
			}else{
				hql += " and " + key +"=:" + key;
			}
		}
		Pagination<User> pagination = userDao.queryList(params, pageIndex, pageSize);
		return pagination;
	}

	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}
	
}
