package com.ly.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ly.dao.RoleDao;
import com.ly.entity.Role;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{

	@Override
	public Role getByUserId(String userId) {
		String hql = "select role from Role role left join User user on user.roleId = role.id " +
				" where user.id=:id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", userId);
		Role role = super.get(hql, map);
		return role;
	}

	@Override
	public List<Role> all() {
		String hql = "from Role ";
		Query query = getSession().createQuery(hql);
		List<Role> list = query.list();
		return list;
	}

	@Override
	public Role getById(String roleId) {
		return super.getById(roleId);
	}

}
