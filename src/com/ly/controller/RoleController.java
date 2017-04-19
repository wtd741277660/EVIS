package com.ly.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.entity.Role;
import com.ly.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Resource
	private RoleService roleService;
	
	@RequestMapping("/getRoles")
	@ResponseBody
	public JSONArray getRoleCombobox(){
		List<Role> roles = roleService.all();
		JSONArray array = new JSONArray();
		for(Role role:roles){
			JSONObject obj = new JSONObject();
			obj.put("id", role.getId());
			obj.put("name", role.getRoleName());
			array.add(obj);
		}
		return array;
	}
}
