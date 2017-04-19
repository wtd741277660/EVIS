package com.ly.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ly.dao.SysMenuDao;
import com.ly.entity.SysMenus;

@Repository
public class SysMenuDaoImpl extends BaseDaoImpl<SysMenus> implements SysMenuDao {

	@Override
	public List<SysMenus> getMenusByLevel(int menuLevel) {
		String hql = "from SysMenus where menuLevel =:menuLevel";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuLevel", menuLevel);
		List<SysMenus> list = super.findList(hql, map);
		return list;
	}

	@Override
	public List<SysMenus> getMenusByUser(String userId) {
		String hql = "select menu from SysMenus menu left join SysRoleMenus roleMenu on roleMenu.sysMenu.id = menu.id " +
				" left join Role role on role.id = sysMenu.role.id " +
				" left join User user on user.role.id = role.id " +
				" where user.id =:userId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		List<SysMenus> list = super.findList(hql, map);
		return list;
	}

}
