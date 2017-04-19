package com.ly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.dao.SysMenuDao;
import com.ly.entity.SysMenus;
import com.ly.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {
	
	@Autowired
	private SysMenuDao sysMenuDao;

	@Override
	public List<SysMenus> getMenusByLevel(int menuLevel) {
		return sysMenuDao.getMenusByLevel(menuLevel);
	}

	@Override
	public List<SysMenus> getMenusByUser(String userId) {
		return sysMenuDao.getMenusByUser(userId);
	}

}
