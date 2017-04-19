package com.ly.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="VEHICLE_REAR_SUSPENSION")
public class VehicleRearSuspension implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8864583402328289191L;
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	private String id;
	
//	@OneToOne
//	@JoinColumn(name="VEHICLE_NUM")
//	private VehicleInfo vehicleInfo;//车辆信息编号
	
	@Column(name="REAR_AXLE_NUM")
	private String rearAxleNum;//后桥编号
	
	@Column(name="REAR_AXLE_TYPE")
	private String rearAxleType;//后桥型号
	
	@Column(name="ELEC_MACHINE_NUM")
	private String elecMachineNum;//电机编号
	
	@Column(name="ELEC_MACHINE_TYPE")
	private String elecMachineType;//电机型号
	
	@Column(name="AFTER_PLATE_SPRING")
	private String afterPlateSpring;//后板簧
	
	@Column(name="AFTER_DAMPING")
	private String afterDamping;//后减震

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public VehicleInfo getVehicleInfo() {
//		return vehicleInfo;
//	}
//
//	public void setVehicleInfo(VehicleInfo vehicleInfo) {
//		this.vehicleInfo = vehicleInfo;
//	}

	public String getRearAxleNum() {
		return rearAxleNum;
	}

	public void setRearAxleNum(String rearAxleNum) {
		this.rearAxleNum = rearAxleNum;
	}

	public String getRearAxleType() {
		return rearAxleType;
	}

	public void setRearAxleType(String rearAxleType) {
		this.rearAxleType = rearAxleType;
	}

	public String getElecMachineNum() {
		return elecMachineNum;
	}

	public void setElecMachineNum(String elecMachineNum) {
		this.elecMachineNum = elecMachineNum;
	}

	public String getElecMachineType() {
		return elecMachineType;
	}

	public void setElecMachineType(String elecMachineType) {
		this.elecMachineType = elecMachineType;
	}

	public String getAfterPlateSpring() {
		return afterPlateSpring;
	}

	public void setAfterPlateSpring(String afterPlateSpring) {
		this.afterPlateSpring = afterPlateSpring;
	}

	public String getAfterDamping() {
		return afterDamping;
	}

	public void setAfterDamping(String afterDamping) {
		this.afterDamping = afterDamping;
	}

}
