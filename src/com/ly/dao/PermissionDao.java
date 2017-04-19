package com.ly.dao;

import java.util.List;

import com.ly.entity.Permission;

public interface PermissionDao extends BaseDao<Permission>{

	public List<Permission> getByUserId(String userId);
}
