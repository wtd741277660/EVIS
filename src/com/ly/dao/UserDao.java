package com.ly.dao;

import java.util.Map;

import com.ly.entity.User;
import com.ly.util.Pagination;

public interface UserDao extends BaseDao<User>{

	/**
	 * 根据用户名获取User对象
	 * @param account 用户名
	 * @return
	 */
	public User getByAccount(String account);
	
	/**
	 * 根据查询参数获取分页后的用户列表
	 * @param params 查询参数
	 * @param pageIndex 页数
	 * @param pageSize 每页大小
	 * @return
	 */
	public Pagination<User> queryList(Map<String, Object> params,int pageIndex,int pageSize);
	
	/**
	 * 保存用户信息
	 * @param user
	 */
	public void saveUser(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 */
	public void updateUser(User user);
}
