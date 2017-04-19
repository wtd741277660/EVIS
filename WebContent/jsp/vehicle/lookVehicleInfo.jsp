<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/jsp/main/resource.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
body{padding:10px}
</style>
<script src=<%= request.getContextPath() + "/js//vehicle/editVehicleInfo.js" %> type="text/javascript"></script>
</head>
<body>
	<div align="center" style="margin-left:auto;margin-right:auto;width:100%">
		<form id="addForm" action="updateVehicle">
			<table style="border-collapse: separate;border-spacing:10px;">
				<tr>
					<td align="right">
						<label>整机编号</label>
					</td>
					<td>
						<input id="vehicleNum" name="vehicleNum" value="${vehicle.vehicleNum}" readonly="readonly" class="easyui-textbox"/>
					</td>
					<td align="right">
						<label style="margin-left:50px">整机型号</label>
					</td>
					<td>
						<input id="vehicleType" name="vehicleType" value="${vehicle.vehicleType}" class="easyui-textbox"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>客户名称</label>
					</td>
					<td>
						<input id="customerName" name="customerName" value="${vehicle.customerName}" class="easyui-textbox"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>生产日期</label>
					</td>
					<td>
						<input id="produceDate" name="produceDate" value="${vehicle.produceDate}" class="easyui-datebox"/>
					</td>
					<td align="right">
						<label>出厂日期</label>
					</td>
					<td>
						<input id="leaveDate" name="leaveDate" value="${vehicle.leaveDate}" class="easyui-datebox"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>车架系统</label>
					</td>
					<td colspan="3">
						<div style="display:inline">
							<div class="upload clearfix">
								<input id="carFrameSystem" name="carFrameSystem" value="${vehicle.carFrameSystem}" class="easyui-textbox" style="float:left"/>
								<a class="lookPho">照片预览</a>
						        <input class="savedFiles" id="carFrameSystemPho" value="${vehicle.carFrameSystemPho}" name="carFrameSystemPho" type="hidden" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>刹车系统</label>
					</td>
					<td colspan="3">
						<div style="display:inline">
							<div class="upload clearfix">
								<input id="brakeSystem" name="brakeSystem" value="${vehicle.brakeSystem}" class="easyui-textbox" style="float:left"/>
						        <input class="savedFiles" id="brakeSystemPho" value="${vehicle.brakeSystemPho}" name="brakeSystemPho" type="hidden" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>前悬系统</label>
					</td>
					<td colspan="3">
						<div style="display:inline">
							<div class="upload clearfix">
								<input id="frontSuspensionSystem" value="${vehicle.frontSuspensionSystem}" name="frontSuspensionSystem" class="easyui-textbox" style="float:left"/>
						        <input class="savedFiles" id="frontSuspensionSystemPho" value="${vehicle.frontSuspensionSystemPho}" name="frontSuspensionSystemPho" type="hidden" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>挡风玻璃系统</label>
					</td>
					<td colspan="3">
						<div style="display:inline">
							<div class="upload clearfix">
								<input id="windshieldSystem" value="${vehicle.windshieldSystem}" name="windshieldSystem" class="easyui-textbox" style="float:left"/>
						        <input class="savedFiles" value="${vehicle.windshieldSystemPho}" id="windshieldSystemPho" name="windshieldSystemPho" type="hidden" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>后悬系统</label>
					</td>
					<td colspan="3">
						<div style="display:inline">
							<div class="upload clearfix">
								<input id="rearSuspensionSystem" value="${vehicle.rearSuspensionSystem}" name="rearSuspensionSystem" class="easyui-textbox" style="float:left"/>
						        <input class="savedFiles" value="${vehicle.rearSuspensionSystemPho}" id="rearSuspensionSystemPho" name="rearSuspensionSystemPho" type="hidden" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>顶棚系统</label>
					</td>
					<td colspan="3">
						<div style="display:inline">
							<div class="upload clearfix">
								<input id="ceilingSystem" value="${vehicle.ceilingSystem}" name="ceilingSystem" class="easyui-textbox" style="float:left"/>
						        <input class="savedFiles" value="${vehicle.ceilingSystemPho}" id="ceilingSystemPho" name="ceilingSystemPho" type="hidden" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>电控系统</label>
					</td>
					<td colspan="3">
						<div style="display:inline">
							<div class="upload clearfix">
								<input id="electronicControlSystem" value="${vehicle.electronicControlSystem}" name="electronicControlSystem" class="easyui-textbox" style="float:left"/>
						        <input class="savedFiles" value="${vehicle.electronicControlSystemPho}" id="electronicControlSystemPho" name="electronicControlSystemPho" type="hidden" />
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div align="center">
		<input id="closeBtn" class="orangeBtn" value="关闭" type="button"/>
	</div>
</body>
</html>