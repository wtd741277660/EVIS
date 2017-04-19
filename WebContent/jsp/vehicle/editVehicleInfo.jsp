<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/jsp/main/resource.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.easyui-textbox{
	border:1px solid lightgray;
}
.addPhoto{
	cursor:pointer;
	font-size:11px;
	color:blue;
}
body{padding:10px}
/* 上传控件 */
.upload
{
    margin-top:10px;
    width:100%;
    height:30px;
}
.upload .uploadbtnBox
{
    float:left;
    height:20px;
    width:70px;
    margin-left:5px;
    margin-top:-2px;
}
.upload .progress
{
    height:4px;
    line-height:4px;
    *zoom:1;
    background:#fff;
    float:left;
    width:160px;
    border:1px #ccc solid;
    overflow:hidden; text-overflow:ellipsis; white-space:nowrap;
    display:none;
    margin-top:13px;
}
.upload .filestate
{
    float:left;
    height:20px;
    text-align:left;
    width:150px;
    line-height:20px;
    display:none;
    color:#333;
    overflow:hidden;
}
.upload .progresspercent
{
    float:right;
    padding-top:5px;
    height:15px;
    text-align:right;
    font-size:9px;
    line-height:15px;
    color:#333;
}

.upload .uploadbtnBox .a-upload {
    height:20px;
    background:#4090c0;
    border:1px solid #dddddd;color:#ffffff;
    /* line-height:28px; */
    padding:0 3px;
    font-size:0.9em;
    overflow: hidden;
    display: inline-block;
    text-decoration:none;
    *display: inline;
    *zoom: 1
}

.upload .uploadbtnBox .a-upload  input {
    position: absolute;
    width:70px;
    height:30px;
    overflow:hidden;
    margin-left:-10px;
    opacity: 0;
    filter: alpha(opacity=0);
    cursor: pointer
}

.upload .progress .bar
{
    height:4px;
    line-height:4px;
    background:#4090c0;
    *zoom:1; 
}

.clearfix:after {
    content: ".";
    display: block;
    height: 0;
    visibility: hidden;
    clear: both;
}
.clearfix {
    _zoom: 1;
}
.clearfix {
	*zoom:1;
}
</style>
<script src=<%= request.getContextPath() + "/js/editVehicleInfo.js" %> type="text/javascript"></script>
<script src=<%= request.getContextPath() + "/plugin/fileUpload/js/vendor/jquery.ui.widget.js" %>></script>
<script src=<%= request.getContextPath() + "/plugin/fileUpload/js/jquery.iframe-transport.js" %>></script>
<script src=<%= request.getContextPath() + "/plugin/fileUpload/js/jquery.fileupload.js" %>></script>
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
								<div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload uploadBtn">
						                <input type="file" data-url="saveFiles" name="files" value="" id="file1"
						                    onchange="CheckFile(this)" multiple/>上传照片 </a>
						        </div>
						        <div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload" onclick="cancleFile(this)"> 取消上传</a>
						        </div>
						        <div class="progresspercent"></div>
						        <div class="progress" style="height: 4px;">
						            <div class="bar" style="width: 0%;"></div>
						        </div>
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
								<div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload uploadBtn">
						                <input type="file" data-url="saveFiles" name="files" value="" id="file7"
						                    onchange="CheckFile(this)" multiple/>上传照片 </a>
						        </div>
						        <div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload" onclick="cancleFile(this)"> 取消上传</a>
						        </div>
						        <div class="progresspercent"></div>
						        <div class="progress" style="height: 4px;">
						            <div class="bar" style="width: 0%;"></div>
						        </div>
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
								<div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload uploadBtn">
						                <input type="file" data-url="saveFiles" name="files" value="" id="file7"
						                    onchange="CheckFile(this)" multiple/>上传照片 </a>
						        </div>
						        <div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload" onclick="cancleFile(this)"> 取消上传</a>
						        </div>
						        <div class="progresspercent"></div>
						        <div class="progress" style="height: 4px;">
						            <div class="bar" style="width: 0%;"></div>
						        </div>
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
								<div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload uploadBtn">
						                <input type="file" data-url="saveFiles" name="files" value="" id="file7"
						                    onchange="CheckFile(this)" multiple/>上传照片 </a>
						        </div>
						        <div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload" onclick="cancleFile(this)"> 取消上传</a>
						        </div>
						        <div class="progresspercent"></div>
						        <div class="progress" style="height: 4px;">
						            <div class="bar" style="width: 0%;"></div>
						        </div>
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
								<div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload uploadBtn">
						                <input type="file" data-url="saveFiles" name="files" value="" id="file7"
						                    onchange="CheckFile(this)" multiple/>上传照片 </a>
						        </div>
						        <div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload" onclick="cancleFile(this)"> 取消上传</a>
						        </div>
						        <div class="progresspercent"></div>
						        <div class="progress" style="height: 4px;">
						            <div class="bar" style="width: 0%;"></div>
						        </div>
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
								<div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload uploadBtn">
						                <input type="file" data-url="saveFiles" name="files" value="" id="file7"
						                    onchange="CheckFile(this)" multiple/>上传照片 </a>
						        </div>
						        <div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload" onclick="cancleFile(this)"> 取消上传</a>
						        </div>
						        <div class="progresspercent"></div>
						        <div class="progress" style="height: 4px;">
						            <div class="bar" style="width: 0%;"></div>
						        </div>
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
								<div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload uploadBtn">
						                <input type="file" data-url="saveFiles" name="files" value="" id="file7"
						                    onchange="CheckFile(this)" multiple/>上传照片 </a>
						        </div>
						        <div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload" onclick="cancleFile(this)"> 取消上传</a>
						        </div>
						        <div class="progresspercent"></div>
						        <div class="progress" style="height: 4px;">
						            <div class="bar" style="width: 0%;"></div>
						        </div>
						        <input class="savedFiles" value="${vehicle.electronicControlSystemPho}" id="electronicControlSystemPho" name="electronicControlSystemPho" type="hidden" />
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div align="center">
		<input id="saveBtn" class="orangeBtn" value="更新" type="button"/>
		<input id="closeBtn" class="orangeBtn" value="关闭" type="button"/>
	</div>
</body>
</html>