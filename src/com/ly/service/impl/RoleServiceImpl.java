package com.ly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.dao.RoleDao;
import com.ly.entity.Role;
import com.ly.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Role getByUserId(String userId){
		Role role = roleDao.getByUserId(userId);
		return role;
	}

	@Override
	public List<Role> all() {
		return roleDao.all();
	}

	@Override
	public Role getById(String roleId) {
		return roleDao.getById(roleId);
	}
}
