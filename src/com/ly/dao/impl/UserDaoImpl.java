package com.ly.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ly.dao.UserDao;
import com.ly.entity.User;
import com.ly.util.Pagination;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User getByAccount(String account) {
		String hql = "from User where account=:account";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", account);
		return super.get(hql, map);
	}

	@Override
	public Pagination<User> queryList(Map<String, Object> params,
			int pageIndex, int pageSize) {
		String hql = "from User where isAdmin is null ";
		for(String key:params.keySet()){
			if(key.equals("account")){
				hql += " and account like:account ";
				params.put("account", "%" + params.get("account") + "%");
			}else if(key.equals("name")){
				hql += " and name like:name ";
				params.put("name", "%" + params.get("name") + "%");
			}else{
				hql += " and " + key +"=:" + key;
			}
		}
		Pagination<User> pagination = super.findPagination(hql,params,pageIndex, pageSize);
		return pagination;
	}

	@Override
	public void saveUser(User user) {
		
	}

	@Override
	public void updateUser(User user) {
		
	}

}
