package com.ly.dao;

import java.util.List;

import com.ly.entity.Role;

public interface RoleDao extends BaseDao<Role>{

	/**
	 * 获取用户对应的角色对象
	 * @param userId 用户id
	 * @return
	 */
	public Role getByUserId(String userId);
	
	/**
	 * 获取所有角色数据
	 * @return
	 */
	public List<Role> all();
	
	/**
	 * 根据id获取角色对象
	 * @param roleId
	 * @return
	 */
	public Role getById(String roleId);
}
