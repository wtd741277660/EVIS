package com.ly.entity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.Entity;

@Entity
@Table(name="SYS_ROLE_MENUS")
public class SysRoleMenus {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
	private Role role;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
	private SysMenus sysMenu;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public SysMenus getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenus sysMenu) {
		this.sysMenu = sysMenu;
	}
	
}
