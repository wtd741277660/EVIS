package com.ly.util;

import java.util.ArrayList;
import java.util.List;

public enum ParamTypeEnum {

	FrontSuspension("01","FRONT_SUSPENSION_SYSTEM"),
	RearSuspension("02","REAR_SUSPENSION_SYSTEM"),
	ElecControl("03","ELECTRONIC_CONTROL_SYSTEM"),
	Brake("04","BRAKE_SYSTEM"),
	Windshield("05","WINDSHIELD_SYSTEM"),
	Ceiling("06","CEILING_SYSTEM");
	
	private String code;
	
	private String tableName;

	private ParamTypeEnum(String code, String tableName) {
		this.code = code;
		this.tableName = tableName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public static String getTableNameByCode(String code){
		if (code == null || code.isEmpty()) {
			return null;
		}
		for(ParamTypeEnum type:ParamTypeEnum.values()){
			if (type.getCode().equals(code)) {
				return type.getTableName();
			}
		}
		return null;
	}
	
	public static List<String> getAllCodes(){
		List<String> codes = new ArrayList<String>();
		for(ParamTypeEnum type:ParamTypeEnum.values()){
			codes.add(type.getCode());
		}
		return codes;
	}
}
