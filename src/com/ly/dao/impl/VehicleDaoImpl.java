package com.ly.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ly.dao.VehicleDao;
import com.ly.entity.VehicleInfo;
import com.ly.util.Pagination;

@Repository
public class VehicleDaoImpl extends BaseDaoImpl<VehicleInfo> implements VehicleDao {

	@Override
	public VehicleInfo getByNum(String vehicleNum) {
		return super.getById(vehicleNum);
	}

	@Override
	public Pagination<VehicleInfo> queryList(Map<String, Object> params,
			int pageIndex, int pageSize) {
		String hql = "from VehicleInfo v where 1=1 ";
		for(String key:params.keySet()){
			if (key.equals("recordUser")) {
				hql += " and recordUser.id =:" + key;
			}else if(key.equals("examineUser") || key.equals("customerName") || key.equals("vehicleNum")){
				hql += " and " + key + " like:" + key;
			}else{
				hql += " and " + key + ":" + key + " ";
			}
		}
		Pagination<VehicleInfo> pagination = super.findPagination(hql, params, pageIndex, pageSize);
		return pagination;
	}

	@Override
	public void saveInfo(VehicleInfo vehicleInfo) {
		super.save(vehicleInfo);
	}

	@Override
	public void updateInfo(VehicleInfo vehicleInfo) {
		super.update(vehicleInfo);
	}

}
