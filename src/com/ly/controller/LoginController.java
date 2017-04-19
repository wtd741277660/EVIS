package com.ly.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ly.entity.SysMenus;
import com.ly.entity.User;
import com.ly.service.SysMenuService;
import com.ly.util.AppTools;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@Resource
	private SysMenuService sysMenuService;

	@RequestMapping("/login")
	public String login(Model model){
		return "login";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(Model model,HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordToken token = new UsernamePasswordToken(username,password); 
		Subject currentUser = SecurityUtils.getSubject(); 
		
//		model.addAttribute("userName",username);
//		model.addAttribute("password",password);
		if(username == null || username.isEmpty()){
			model.addAttribute("errorMsg", "账号不能为空！");
			return "login";
		}
		if(password == null || password.isEmpty()){
			model.addAttribute("errorMsg", "密码不能为空！");
			return "login";
		}
		try {
			currentUser.login(token); 
		} catch (UnknownAccountException ue) {
			ue.printStackTrace();
			model.addAttribute("errorMsg", "账号不存在！");
			return "login";
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			model.addAttribute("errorMsg", "账号或密码不正确！");
			return "login";
		}
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index(Model model){
		//保存登陆人员信息
		User currentUser = AppTools.getCurrentUser();
		model.addAttribute("user",currentUser);
		if (currentUser == null) {
			model.addAttribute("errorMsg", "当前登录人员为空！");
		}
		//加载菜单信息
		List<SysMenus> menus = sysMenuService.getMenusByUser(currentUser.getId());
		//首先获取一级菜单
		
		return "/main/index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute(session.getId());
//		ClientManager.getInstance().removeClinet(session.getId());
		session.invalidate();
		return "redirect:login";
	}
}
