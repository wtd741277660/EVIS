package com.ly.service;

import java.util.List;

import com.ly.entity.Permission;

public interface PermissionService {

	public List<Permission> getByUserId(String userId);
}
