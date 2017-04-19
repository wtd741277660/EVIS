package com.ly.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.entity.Role;
import com.ly.entity.User;
import com.ly.service.LogService;
import com.ly.service.RoleService;
import com.ly.service.UserService;
import com.ly.util.Pagination;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	@Resource
	private LogService logService;
	@Resource
	private RoleService roleService;
	
	@RequestMapping("/userList")
	public String userList(){
		return "/user/userList";
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public JSONObject getList(HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONObject result = new JSONObject();
		String pageIndexStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		int pageIndex = 1;
		int pageSize = 20;
		if (pageIndexStr != null && !pageIndexStr.isEmpty()) {
			pageIndex = Integer.parseInt(pageIndexStr);
		}
		if (pageSizeStr != null && !pageSizeStr.isEmpty()) {
			pageSize = Integer.parseInt(pageSizeStr);;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		String account = request.getParameter("account");
		String name = request.getParameter("name");
		if (account != null && !account.isEmpty()) {
			params.put("account", account);
		}
		if (name != null && !name.isEmpty()) {
			params.put("name", name);
		}
		
		Pagination<User> pagination = userService.queryList(params, pageIndex, pageSize);
		JSONArray array = new JSONArray();
		if (pagination.getItems() != null) {
			for(User user:pagination.getItems()){
				JSONObject obj = new JSONObject();
				obj.put("account", user.getAccount());
				obj.put("name", user.getName());
				obj.put("roleName", user.getRole() == null?"":user.getRole().getRoleName());
				obj.put("createUser", user.getCreateUser());
				if (user.getCreateDate() != null) {
					obj.put("createDate", sdf.format(user.getCreateDate()));
				}else{
					obj.put("createDate", "");
				}
				array.add(obj);
			}
		}
		result.put("total", pagination.getRowsCount());
		result.put("rows", array);
		return result;
	}
	
	@RequestMapping("addUser")
	public String addUser(Model model){
		return "/user/addUser";
	}
	
	@RequestMapping("/saveUser")
	@ResponseBody
	public JSONObject saveUser(User user,HttpServletRequest request){
		JSONObject result = new JSONObject();
		String roleId = request.getParameter("roleId");
		if(roleId != null && !roleId.isEmpty()){
			Role role = roleService.getById(roleId);
			user.setRole(role);
		}
		Subject subject = SecurityUtils.getSubject();//获取当前用户
		User currentUser = (User) subject.getSession().getAttribute("currentUser");
		user.setCreateDate(new Date());
		user.setCreateUser("aaa");
		user.setCreateUser(currentUser.getAccount());
		result.put("success", "true");
		try {
			userService.saveUser(user);
			logService.saveLog("新建用户[" + user.getAccount() + "]", "2",request);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "false");
			result.put("errorMsg", "新建用户失败：" + e.getMessage());
			logService.saveLog("新建用户[" + user.getAccount() + "]失败:" + e.getMessage(), "2",request);
		}
		
		return result;
	}
	
	@RequestMapping("editUser")
	public String editUser(HttpServletRequest request,Model model){
		String account = request.getParameter("account");
		if (account != null && !account.isEmpty()) {
			User user = userService.getByAccount(account);
			model.addAttribute("user",user);
		}
		return "/user/editUser";
	}
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public JSONObject updateUser(HttpServletRequest request){
		JSONObject result = new JSONObject();
		result.put("success", "true");
		String account = request.getParameter("account");
		String name = request.getParameter("name");
		String roleId = request.getParameter("roleId");
		if (account == null || account.isEmpty()) {
			result.put("success", "false");
			result.put("errorMsg", "账号为空，编辑用户失败！");
		}
		User user = userService.getByAccount(account);
		if (user == null) {
			result.put("success", "false");
			result.put("errorMsg", "用户为空，编辑用户失败！");
		}
		user.setUpdateDate(new Date());
		user.setName(name);
		if(roleId != null && !roleId.isEmpty() && !roleId.equals(user.getRole().getId())){
			Role role = roleService.getById(roleId);
			user.setRole(role);
		}
		try {
			userService.updateUser(user);
			logService.saveLog("编辑用户[" + user.getAccount() + "]", "2",request);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "false");
			result.put("errorMsg", "编辑用户失败：" + e.getMessage());
			logService.saveLog("编辑用户[" + user.getAccount() + "]失败:" + e.getMessage(), "2",request);
		}
		return result;
	}
	
	@RequestMapping("/checkRepeat")
	@ResponseBody
	public JSONObject checkRepeat(HttpServletRequest request){
		JSONObject result = new JSONObject();
		result.put("repeat", "false");
		String account = request.getParameter("account");
		if (account == null || account.isEmpty()) {
			return result;
		}
		User user = userService.getByAccount(account);
		if (user != null) {
			result.put("repeat", "true");
		}
		return result;
	}
}
