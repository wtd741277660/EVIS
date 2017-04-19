<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/jsp/main/resource.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆管理系统</title>
<style type="text/css">
 iframe {
	width: 100%;
	height: 99%;
	border: none;
}
 </style>
 <script type="text/javascript" src="/EVIS/js/main/main.js"></script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div region="north" class="north" border="false" title="" region="north" border="false" title="" style="">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		    <td align="left" style="vertical-align: middle !important;vertical-align: text-top;">
		    	<img style="margin-left: 10px;" src="/EVIS/plugin/login/images/sys_logo.png">
		    </td>
		    <td align="right" nowrap>
		        <table border="0" cellpadding="0" cellspacing="0">
		            <tr style="" id="mainMenu">
			                <td colspan="2">
			                    <ul class="shortcut" id="firstMenu">
			                        <!-- 动态生成并赋值过来 -->
			                        <li onclick="openSecondMenu('vehicleInfo')">
			                        	<img class="imag1" src="/EVIS/images/login/vehicle.jpg" style="display: none;">  
			                        	<img class="imag2" src="/EVIS/images/login/vehicle_up.jpg" style=""> 
			                        </li>
			                        <li onclick="openSecondMenu('user')">
			                        	<img class="imag1" src="/EVIS/plugin/login/images/demand.jpg" style="display: none;">  
			                        	<img class="imag2" src="/EVIS/plugin/login/images/demand_up.jpg" style=""> 
			                        </li>
			                        <li onclick="openSecondMenu('system')">
			                        	<img class="imag1" src="/EVIS/plugin/login/images/demand.jpg" style="display: none;">  
			                        	<img class="imag2" src="/EVIS/plugin/login/images/demand_up.jpg" style=""> 
			                        </li>
			                    </ul>
			                </td>
			                <td>
			                <div style="float: left; margin-left: 1px;">
		                        <div style="right: 0px; bottom: 0px;" class="userInfo">
		                            <a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu"
		                            	 iconCls="icon-comturn" style="color: #FFFFFF;">
		<!--                                	 管理员 -->
		                            </a>
									<div style="color:#000;font-size:13px;position:absolute; top: 47px; 
										right:0px; float: right; margin-left: 8px;margin-right: 5px; margin-top: 5px;
										width:73px;text-align:center">
										${userName}
									</div>
				                    <div style="position:absolute; top: 0px; right:0px; float: right; margin-left: 8px;margin-right: 5px; margin-top: 5px;">
				                        <!-- <img src="/EVIS/plugin/easyui/themes/default/images/layout_button_up.gif"
				                             style="cursor:pointer" onclick="panelCollapase()" /> -->
				                    </div>
		                        </div>
		                        <div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
		                            <div >
				 						<span style="color: #386780">用户:</span>&nbsp;&nbsp;
				                        <span style="color: #3c3c3c">${user.name}</span>
			                        </div>
			                        <div>
				                        <span style="color: #386780">角色:</span>&nbsp;&nbsp;
				                        <span style="color: #3c3c3c">${user.role.roleName}</span>
			                        </div>
			                        <div>
				                        <span style="color: #386780">用户名:</span>&nbsp;&nbsp;
				                        <span style="color: #3c3c3c">${user.account}</span>
					                </div>
		                            <div class="menu-sep"></div>
		                            <%-- <div onclick="openwindow('<t:mutiLang langKey="common.profile"/>','userController.do?userinfo')">
		                                <t:mutiLang langKey="common.profile"/>
		                            </div> --%>
		                            <%-- <div class="menu-sep"></div>
		                            <div onclick="add('<t:mutiLang langKey="common.change.password"/>','userController.do?changepassword','',550,200)">
		                                	修改密码
		                            </div> --%>
		                            <%-- <div class="menu-sep"></div>
		                            <div onclick="openwindow('<t:mutiLang langKey="common.ssms.getSysInfos"/>','tSSmsController.do?getSysInfos')">
		                                <t:mutiLang langKey="common.ssms.getSysInfos"/>
		                            </div> --%>
		<!--                              <div class="menu-sep"></div> -->
		<%--                             <div onclick="add('<t:mutiLang langKey="common.change.style"/>','userController.do?changestyle','',550,200)"> --%>
		<%--                                 <t:mutiLang langKey="common.change.style"/> --%>
		<!--                             </div>  -->
		<!--                              <div onclick="clearLocalstorage()"> -->
		<%--                        		 	<t:mutiLang langKey="common.clear.localstorage"/> --%>
		<!--                    			 </div>  -->
		                             <div class="menu-sep"></div>
		                            <div onclick="exit('/EVIS/logout','确认退出',1);">
		                               	 退出
		                            </div>
		                        </div>
		                    </div>
			                </td>
			            </tr>
		        </table>
		    </td>
		</tr>
		</table>
		<div style="overflow-x: hidden; overflow-y: auto;height: 28px; margin-top : 0px"  id="secondMenu" class='topMemu' >
           	<div name='divmain' id='vehicleInfo' style="display:inline" >
				<a onclick="addTab('车辆信息管理','/EVIS/vehicle/vehicleList','default')" class='easyui-linkbutton' data-options="plain:true" iconCls="default">车辆信息管理</a>
			</div>
			<div name='divmain' id='user' style="display:inline" >
				<a onclick="addTab('用户信息管理','/EVIS/user/userList','default')" class='easyui-linkbutton' data-options="plain:true" iconCls="default">用户信息管理</a>
			</div>
			<div name='divmain' id='system' style="display:inline" >
				<a onclick="addTab('前悬系统','/EVIS/params/paramList?paramType=01','default')" class='easyui-linkbutton' data-options="plain:true" iconCls="default">前悬系统</a>
				<a onclick="addTab('后悬系统','/EVIS/params/paramList?paramType=02')" class='easyui-linkbutton' data-options="plain:true" iconCls="default">后悬系统</a>
				<a onclick="addTab('电控系统','/EVIS/params/paramList?paramType=03')" class='easyui-linkbutton' data-options="plain:true" iconCls="default">电控系统</a>
				<a onclick="addTab('刹车系统','/EVIS/params/paramList?paramType=04')" class='easyui-linkbutton' data-options="plain:true" iconCls="default">刹车系统</a>
				<a onclick="addTab('风挡系统','/EVIS/params/paramList?paramType=05')" class='easyui-linkbutton' data-options="plain:true" iconCls="default">风挡系统</a>
				<a onclick="addTab('顶棚系统','/EVIS/params/paramList?paramType=06')" class='easyui-linkbutton' data-options="plain:true" iconCls="default">顶棚系统</a>
				<a onclick="addTab('系统日志','/EVIS/log/list','default')" class='easyui-linkbutton' data-options="plain:true" iconCls="default">系统日志</a>
			</div>
        </div>
		<link rel="stylesheet" href="/EVIS/plugin/accordion/css/icons.css" type="text/css"></link>
		<link rel="stylesheet" href="/EVIS/plugin/accordion/css/accordion.css" type="text/css"></link>
		<script type="text/javascript" src="/EVIS/plugin/accordion/js/top_shortcut_menu.js"></script>
	</div>
	<!-- north -->
	<%-- <div style="height:83%">
		<img id="bgImg" style="width:100%;height:100%" src='<%= request.getContextPath() + "/images/123.jpg"%>'/>
	</div>
	<div style="height:20px;background:#58CDFD"></div> --%>
	
	
		
	<!-- 中间-->
	
	<div id="mainPanle" region="center" style="overflow: hidden;">
	    <div id="maintabs" class="easyui-tabs" fit="true" border="false">
	        <!-- <div title="参数配置" style="width:100%;height:100%">
		        <iframe id="parameterConfigIFrame" src="/EVIS/vehicle/vehicleList"></iframe>
		    </div> -->
	    </div>
	</div>
</body>
</html>