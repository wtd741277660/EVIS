package com.ly.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="SYS_MENUS")
public class SysMenus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -814519293640491870L;
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	private String id;
	
	@Column(name="code")
	private String code;
	
	@Column(name="name")
	private String name;
	
	@Column(name="parent_id")
	private String parentId;
	
	@Column(name="url")
	private String url;
	
	@Column(name="img_url")
	private String imgUrl;
	
	@Column(name="img_up_url")
	private String imgUpUrl;
	
	@Column(name="menu_level")
	private int menuLevel;
	
	@Column(name="menu_sort")
	private int menuSort;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgUpUrl() {
		return imgUpUrl;
	}

	public void setImgUpUrl(String imgUpUrl) {
		this.imgUpUrl = imgUpUrl;
	}

	public int getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public int getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(int menuSort) {
		this.menuSort = menuSort;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
