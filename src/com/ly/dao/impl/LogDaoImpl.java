package com.ly.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ly.dao.LogDao;
import com.ly.entity.Log;
import com.ly.util.Pagination;
import com.sun.corba.se.spi.orbutil.fsm.State;

@Repository
public class LogDaoImpl extends BaseDaoImpl<Log> implements LogDao {

	@Override
	public void saveLog(Log log) {
		super.save(log);
	}

	@Override
	public Pagination<Log> queryList(Map<String, Object> params, int pageIndex,int pageSize) {
		String hql = "from Log where 1=1 ";
		for(String key:params.keySet()){
			if (key.equals("startTime")) {
				hql += " and operateDate >=to_date('" + params.get("startTime") + "','yyyy-mm-dd HH24:mi:ss') ";
				params.put("startTime", null);
			}else if(key.equals("endTime")){
				hql += " and operateDate <=to_date('" + params.get("endTime") + "','yyyy-mm-dd HH24:mi:ss') ";
				params.put("endTime", null);
			}else{
				hql += " and " + key + "=:" + key +" ";
			}
		}
		Pagination<Log> pagination = super.findPagination(hql, params,pageIndex, pageSize);
		return pagination;
	}

}
