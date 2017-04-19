package com.ly.dao;

import java.util.List;

import com.ly.entity.SysMenus;

public interface SysMenuDao extends BaseDao<SysMenus>{

	/**
	 * 根据菜单等级获取对应的菜单集合
	 * @param menuLevel 菜单等级 1：一级菜单，2：二级菜单
	 * @return
	 */
	public List<SysMenus> getMenusByLevel(int menuLevel);
	
	/**
	 * 获取当前登录用户对应的角色的菜单
	 * @param userId 用户id
	 * @return
	 */
	public List<SysMenus> getMenusByUser(String userId);
}
