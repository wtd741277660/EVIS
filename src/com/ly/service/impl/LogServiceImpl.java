package com.ly.service.impl;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.dao.LogDao;
import com.ly.entity.Log;
import com.ly.entity.User;
import com.ly.service.LogService;
import com.ly.util.AppTools;
import com.ly.util.Pagination;

@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	private LogDao logDao;

	@Override
	public void saveLog(String content,String logLevel,HttpServletRequest request) {
		Log log = new Log();
		Subject subject = SecurityUtils.getSubject();//获取当前用户
		User currentUser = (User) subject.getSession().getAttribute("currentUser");
		log.setContent(content);
		log.setLogLevel(logLevel);
		log.setOperateDate(new Date());
		String remoteIP = AppTools.getIp(request);
		if (remoteIP != null) {
			log.setOperateIP(remoteIP == "0:0:0:0:0:0:0:1"?"127.0.0.1":remoteIP);
		}
		log.setOperateUser(currentUser.getAccount());
		logDao.saveLog(log);
	}

	@Override
	public Pagination<Log> queryList(Map<String, Object> params, int pageIndex,int pageSize) {
		return logDao.queryList(params, pageIndex, pageSize);
	}
}
