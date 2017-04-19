package com.ly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.dao.PermissionDao;
import com.ly.entity.Permission;
import com.ly.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<Permission> getByUserId(String userId) {
		List<Permission> permissions = permissionDao.getByUserId(userId);
		return permissions;
	}

}
