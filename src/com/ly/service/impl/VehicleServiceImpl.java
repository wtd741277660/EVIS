package com.ly.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ly.dao.VehicleDao;
import com.ly.entity.BrakeSystem;
import com.ly.entity.CeilingSystem;
import com.ly.entity.ElectronicControlSystem;
import com.ly.entity.FrontSuspensionSystems;
import com.ly.entity.RearSuspensionSystems;
import com.ly.entity.User;
import com.ly.entity.VehicleBrake;
import com.ly.entity.VehicleElecControl;
import com.ly.entity.VehicleFrontSuspension;
import com.ly.entity.VehicleInfo;
import com.ly.entity.VehicleRearSuspension;
import com.ly.entity.VehicleWindshield;
import com.ly.entity.WindshieldSystem;
import com.ly.service.VehicleService;
import com.ly.util.AppTools;
import com.ly.util.Pagination;
import com.ly.util.ResourceUtil;

@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private VehicleDao vehicleDao;

	@Override
	public Pagination<VehicleInfo> queryList(HttpServletRequest request,int pageIndex,int pageSize) {
		String vehicleNum = request.getParameter("vehicleNum");
		String vehicleType = request.getParameter("vehicleType");
		String customerName = request.getParameter("customerName");
		String produceDate = request.getParameter("produceDate");
		String leaveData = request.getParameter("leaveData");
		String examineUser = request.getParameter("examineUser");
		String recordUser = request.getParameter("recordUser");
		
		Map<String, Object> params = new HashMap<String, Object>();
		if (vehicleNum != null && !vehicleNum.isEmpty()) {
			params.put("vehicleNum", vehicleNum);
		}
		if (vehicleType != null && !vehicleType.isEmpty()) {
			params.put("vehicleType", vehicleType);
		}
		if (customerName != null && !customerName.isEmpty()) {
			params.put("customerName", "%" + customerName + "%");
		}
		if (produceDate != null && !produceDate.isEmpty()) {
			params.put("produceDate", produceDate);
		}
		if (leaveData != null && !leaveData.isEmpty()) {
			params.put("leaveData", leaveData);
		}
		if (examineUser != null && !examineUser.isEmpty()) {
			params.put("examineUser", "%" + examineUser + "%");
		}
		if (recordUser != null && !recordUser.isEmpty()) {
			params.put("recordUser", recordUser);
		}
		Pagination<VehicleInfo> pagination = vehicleDao.queryList(params, pageIndex, pageSize);
		return pagination;
	}

	@Override
	public VehicleInfo getByNum(String vehicleNum) {
		return vehicleDao.getByNum(vehicleNum);
	}

	@Override
	public void saveInfo(VehicleInfo vehicleInfo,HttpServletRequest request) {
		VehicleInfo vehicle = getVehicleByInfo(request);
		vehicleDao.save(vehicle);
	}

	@Override
	public void updateInfo(VehicleInfo vehicleInfo,HttpServletRequest request) {
		VehicleInfo vehicle = getVehicleByInfo(request);
		vehicleDao.update(vehicle);
	}
	
	private VehicleInfo getVehicleByInfo(HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		VehicleInfo vehicle = null;
		String type = request.getParameter("type");
		if (type == null || type.isEmpty()) {
			type = "add";
		}
		JSONObject data = JSONObject.parseObject(request.getParameter("data"));
		String vehicleNum = data.getString("vehicleNum");
		vehicle = getByNum(vehicleNum);
		if (vehicle == null) {
			vehicle = new VehicleInfo();
		}
		if (type.equals("add")) {
			vehicle.setVehicleNum(vehicleNum);
			User currentUser = AppTools.getCurrentUser();
			vehicle.setRecordUser(currentUser);
			
			vehicle.setCreateDate(new Date());
		}else{
			vehicle.setUpdateDate(new Date());
		}
		String vehicleType = data.getString("vehicleType");
		String customerName = data.getString("customerName");
		String produceDateStr = data.getString("produceDate");
		String leaveDateStr = data.getString("leaveDate");
		String examineUser = data.getString("examineUser");
		String vehicleSystem = data.getString("vehicleSystem");
		String carframeSystem = data.getString("carframeSystem");
		
		String brakeSystemId = data.getString("brakeSystem");
		String vehicleBrake_brakeReturnSpring = data.getString("vehicleBrake.brakeReturnSpring");
		String vehicleBrake_accelerateReturnSpring = data.getString("vehicleBrake.accelerateReturnSpring");
		String brakeSystemRemark = data.getString("brakeSystemRemark");
		
		String ceilingSystemId = data.getString("ceilingSystem");
		
		String electronicControlSystemId = data.getString("electronicControlSystem");
		String vehicleElecControl_controllerNum = data.getString("vehicleElecControl.controllerNum");
		String vehicleElecControl_controllerType = data.getString("vehicleElecControl.controllerType");
		String vehicleElecControl_DCNum = data.getString("vehicleElecControl.DCNum");
		String vehicleElecControl_DCType = data.getString("vehicleElecControl.DCType");
		String vehicleElecControl_acceleratorNum = data.getString("vehicleElecControl.acceleratorNum");
		String vehicleElecControl_acceleratorType = data.getString("vehicleElecControl.acceleratorType");
		String vehicleElecControl_stopLightSwitch = data.getString("vehicleElecControl.stopLightSwitch");
		String vehicleElecControl_LFLight = data.getString("vehicleElecControl.LFLight");
		String vehicleElecControl_RFLight = data.getString("vehicleElecControl.RFLight");
		String vehicleElecControl_LRLight = data.getString("vehicleElecControl.LRLight");
		String vehicleElecControl_RRLight = data.getString("vehicleElecControl.RRLight");
		String elecControlRemark = data.getString("elecControlRemark");
		
		String frontSuspensionSystemId = data.getString("frontSuspensionSystem");
		String vehicleFrontSuspension_LFDamper = data.getString("vehicleFrontSuspension.LFDamper");
		String vehicleFrontSuspension_RFDamper = data.getString("vehicleFrontSuspension.RFDamper");
		String vehicleFrontSuspension_steeringEngine = data.getString("vehicleFrontSuspension.steeringEngine");
		String vehicleFrontSuspension_cardanJoint = data.getString("vehicleFrontSuspension.cardanJoint");
		String vehicleFrontSuspension_LAzibi = data.getString("vehicleFrontSuspension.LAzibi");
		String vehicleFrontSuspension_RAzibi = data.getString("vehicleFrontSuspension.RAzibi");
		String vehicleFrontSuspension_wheelHub = data.getString("vehicleFrontSuspension.wheelHub");
		String frontSuspensionRemark = data.getString("frontSuspensionRemark");
		
		String rearSuspensionSystemId = data.getString("");
		String vehicleRearSuspension_rearAxleNum = data.getString("");
		String vehicleRearSuspension_rearAxleType = data.getString("");
		String vehicleRearSuspension_elecMachineNum = data.getString("");
		String vehicleRearSuspension_elecMachineType = data.getString("");
		String vehicleRearSuspension_afterPlateSpring = data.getString("");
		String vehicleRearSuspension_afterDamping = data.getString("");
		String rearSuspensionRemark = data.getString("");
		
		String windshieldSystemId = data.getString("windshieldSystem");
		String vehicleWindshield_windshield = data.getString("vehicleWindshield.windshield");
		String vehicleWindshield_rearviewMirror = data.getString("vehicleWindshield.rearviewMirror");
		String vehicleWindshield_leftRightMirror = data.getString("vehicleWindshield.leftRightMirror");
		String vehicleWindshield_scoreBoard = data.getString("vehicleWindshield.scoreBoard");
		String vehicleWindshield_windshieldHolder = data.getString("vehicleWindshield.windshieldHolder");
		String windshieldRemark = data.getString("windshieldRemark");
		
		String accessory = data.getString("accessory");
		String remark = data.getString("remark");
				
		
		vehicle.setVehicleType(vehicleType);
		vehicle.setCustomerName(customerName);
		try {
			if (produceDateStr != null && !produceDateStr.isEmpty()) {
				vehicle.setProduceDate(sdf.parse(produceDateStr));
			}
			if (leaveDateStr != null && !leaveDateStr.isEmpty()) {
				vehicle.setLeaveDate(sdf.parse(leaveDateStr));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		vehicle.setExamineUser(examineUser);
		vehicle.setVehicleSystem(vehicleSystem);
		vehicle.setCarframeSystem(carframeSystem);
		//处理前悬系统信息
		Map<String, Object> map = ResourceUtil.params.get("01");
		vehicle.setFrontSuspensionSystem((FrontSuspensionSystems)map.get(frontSuspensionSystemId));
		VehicleFrontSuspension frontSuspension = new VehicleFrontSuspension();
		frontSuspension.setCardanJoint(vehicleFrontSuspension_cardanJoint);
		frontSuspension.setLAzibi(vehicleFrontSuspension_LAzibi);
		frontSuspension.setLFDamper(vehicleFrontSuspension_LFDamper);
		frontSuspension.setRAzibi(vehicleFrontSuspension_RAzibi);
		frontSuspension.setRFDamper(vehicleFrontSuspension_RFDamper);
		frontSuspension.setSteeringEngine(vehicleFrontSuspension_steeringEngine);
		frontSuspension.setWheelHub(vehicleFrontSuspension_wheelHub);
		vehicle.setVehicleFrontSuspension(frontSuspension);
		vehicle.setFrontSuspensionRemark(frontSuspensionRemark);
		
		//处理后悬系统
		map = ResourceUtil.params.get("02");
		vehicle.setRearSuspensionSystem((RearSuspensionSystems)map.get(rearSuspensionSystemId));
		VehicleRearSuspension rearSuspension = new VehicleRearSuspension();
		rearSuspension.setAfterDamping(vehicleRearSuspension_afterDamping);
		rearSuspension.setAfterPlateSpring(vehicleRearSuspension_afterPlateSpring);
		rearSuspension.setElecMachineNum(vehicleRearSuspension_elecMachineNum);
		rearSuspension.setElecMachineType(vehicleRearSuspension_elecMachineType);
		rearSuspension.setRearAxleNum(vehicleRearSuspension_rearAxleNum);
		rearSuspension.setRearAxleType(vehicleRearSuspension_rearAxleType);
		vehicle.setVehicleRearSuspension(rearSuspension);
		vehicle.setRearSuspensionRemark(rearSuspensionRemark);
		
		//处理电控系统
		map = ResourceUtil.params.get("03");
		vehicle.setElectronicControlSystem((ElectronicControlSystem)map.get(electronicControlSystemId));
		VehicleElecControl elecControl = new VehicleElecControl();
		elecControl.setAcceleratorNum(vehicleElecControl_acceleratorNum);
		elecControl.setAcceleratorType(vehicleElecControl_acceleratorType);
		elecControl.setControllerNum(vehicleElecControl_controllerNum);
		elecControl.setControllerType(vehicleElecControl_controllerType);
		elecControl.setDCNum(vehicleElecControl_DCNum);
		elecControl.setDCType(vehicleElecControl_DCType);
		elecControl.setLFLight(vehicleElecControl_LFLight);
		elecControl.setLRLight(vehicleElecControl_LRLight);
		elecControl.setRFLight(vehicleElecControl_RFLight);
		elecControl.setRRLight(vehicleElecControl_RRLight);
		elecControl.setStopLightSwitch(vehicleElecControl_stopLightSwitch);
		vehicle.setVehicleElecControl(elecControl);
		vehicle.setElecControlRemark(elecControlRemark);
		
		//处理刹车系统
		map = ResourceUtil.params.get("04");
		vehicle.setBrakeSystem((BrakeSystem)map.get(brakeSystemId));
		VehicleBrake brake = new VehicleBrake();
		brake.setAccelerateReturnSpring(vehicleBrake_accelerateReturnSpring);
		brake.setBrakeReturnSpring(vehicleBrake_brakeReturnSpring);
		vehicle.setVehicleBrake(brake);
		vehicle.setBrakeSystemRemark(brakeSystemRemark);
		
		//处理风挡系统
		map = ResourceUtil.params.get("05");
		vehicle.setWindshieldSystem((WindshieldSystem)map.get(windshieldSystemId));
		VehicleWindshield windshield = new VehicleWindshield();
		windshield.setLeftRightMirror(vehicleWindshield_leftRightMirror);
		windshield.setRearviewMirror(vehicleWindshield_rearviewMirror);
		windshield.setScoreBoard(vehicleWindshield_scoreBoard);
		windshield.setWindshield(vehicleWindshield_windshield);
		windshield.setWindshieldHolder(vehicleWindshield_windshieldHolder);
		vehicle.setVehicleWindshield(windshield);
		vehicle.setWindshieldRemark(windshieldRemark);
		
		//处理顶棚系统
		map = ResourceUtil.params.get("06");
		vehicle.setCeilingSystem((CeilingSystem)map.get(ceilingSystemId));
		
		vehicle.setAccessory(accessory);
		vehicle.setRemark(remark);
		
		return vehicle;
	}
}
