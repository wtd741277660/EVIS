package com.ly.controller;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.ly.entity.Permission;
import com.ly.entity.Role;
import com.ly.entity.User;
import com.ly.service.PermissionService;
import com.ly.service.RoleService;
import com.ly.service.UserService;

public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
		//获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		String currentUsername = (String)super.getAvailablePrincipal(principals);
		
		User user = userService.getByAccount(currentUsername);
		if(user == null){
			throw new AuthenticationException("msg:用户不存在。");
		}
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		
		Role role = roleService.getByUserId(user.getId());
		
		List<Permission> permList = permissionService.getByUserId(user.getId());
		
		if (role != null) {
			simpleAuthorInfo.addRole(role.getId());
		}
		if(permList != null && permList.size() > 0){
			for(Permission perm : permList){
				if(perm.getPermissionCode() != null){
					simpleAuthorInfo.addStringPermission(perm.getPermissionCode());
				}
			}
		}
		return simpleAuthorInfo;
	}

	
	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		//获取基于用户名和密码的令牌
		//实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		
		Session session = getSession();
		//判断验证码
//		String code = (String)session.getAttribute(Constants.VALIDATE_CODE);
//		if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)){
//			throw new AuthenticationException("msg:验证码错误, 请重试.");
//		}
		User user = userService.getByAccount(token.getUsername());
		if(user != null){
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(), this.getName());
			this.setSession("currentUser", user);
			return authcInfo;
		}
		return null;
	}
	
	/**
	 * 保存登录名
	 */
	private void setSession(Object key, Object value){
		Session session = getSession();
		System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
		if(null != session){
			session.setAttribute(key, value);
		}
	}
	
	private Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
		}catch (InvalidSessionException e){
			
		}
		return null;
	}


}