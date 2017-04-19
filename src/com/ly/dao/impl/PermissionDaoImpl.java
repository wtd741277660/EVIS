package com.ly.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ly.dao.PermissionDao;
import com.ly.entity.Permission;

@Repository
public class PermissionDaoImpl extends BaseDaoImpl<Permission>implements PermissionDao {

	@Override
	public List<Permission> getByUserId(String userId) {
		String hql = "select perm from Permission perm left join RolePermission rp on rp.permissionId = perm.id " +
				" left join Role role on role.id = rp.roleId left join User user on user.roleId = role.id " +
				" where user.id=:userId";
		List<Permission> permissions = super.findList(hql, userId);
		return permissions;
	}

}
