package com.ly.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.Entity;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="VEHICLE_BRAKE")
public class VehicleBrake implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1837170002106033781L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	private String id;
	
//	@OneToOne
//	@JoinColumn(name="VEHICLE_NUM")
//	private VehicleInfo vehicleInfo;//车辆信息编码
	
	@Column(name="BRAKE_RETURN_SPRING")
	private String brakeReturnSpring;//刹车回位簧
	
	@Column(name="ACCELERATE_RETURN_SPRING")
	private String accelerateReturnSpring;//加速回位簧

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

	public String getBrakeReturnSpring() {
		return brakeReturnSpring;
	}

	public void setBrakeReturnSpring(String brakeReturnSpring) {
		this.brakeReturnSpring = brakeReturnSpring;
	}

	public String getAccelerateReturnSpring() {
		return accelerateReturnSpring;
	}

	public void setAccelerateReturnSpring(String accelerateReturnSpring) {
		this.accelerateReturnSpring = accelerateReturnSpring;
	}
	
}
