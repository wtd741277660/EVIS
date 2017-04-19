package com.ly.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.ly.entity.User;

public class AppTools {

	/**
	 * 获取远程客户端的IP地址
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
	
	/**
	 * 获取当前登陆用户
	 * @return
	 */
	public static User getCurrentUser(){
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession(false);
		User currentUser = (User) session.getAttribute("currentUser");
		return currentUser;
	}
}
