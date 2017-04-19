package com.ly.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name="users")
@Entity
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6568038572059663938L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	private String id;
	
	@Column(name="account",nullable=false,length=32)
	private String account;//账号
	
	@Column(name="name",nullable=false,length=32)
	private String name;//姓名
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(name="create_date",nullable=false)
	private Date createDate;
	
	@Column(name="create_user",nullable=true)
	private String createUser;
	
	@Column(name="update_date")
	private Date updateDate;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="role_id")
	private Role role;
	
	@Column(name="is_admin",length=5)
	private String isAdmin;//判断是否是超级管理员，超级管理员不能删除

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [account=" + account + ", name=" + name
				+ ", password=" + password + ", createDate=" + createDate
				+ ", createUser=" + createUser + ", updateDate=" + updateDate
				+ ", role=" + role + "]";
	}
}
