$(document).ready(function(){
	var height = $("#center").height() - 120;
	
	$('#vehicleList').datagrid({
		height:height,
	   	width:'auto',
	    url:'../vehicle/queryList',  
	    rownumbers:true,	//显示行数
		fitColumns:true,	//自适应列的大小，防止出现水平滚动条
		loadMsg:"正在加载数据，请稍等...",	//载入等待时信息
		nowrap:true,	//数据长度超过列宽时自动截取，默认为true，否则换行
		pagination:true,	//显示分页工具栏
		pageNumber:1,	//设置分页属性时，初始化页码，默认为1
		pageSize:20,	//设置分页属性时，初始化每页记录数，默认为10
		pageList:[10,20,30,40,50],		//设置分页属性时，初始化每页记录数列表，默认为[10,20,30,40,50]
		striped:true,	//设置斑马线
		singleSelect : true,	//只允许选择一行
		columns : [[{
			field:'vehicleNum',
			align : 'left',
			title:'整机编号',
			width: 100
		},{
			field : 'vehicleType', 
			align : 'left',
			title : '整机型号',
			width: 100
		},{
			field : 'customerName', 
			align : 'left',
			title : '客户姓名',
			width: 100
		},{
			field : 'produceDate', 
			align : 'left',
			title : '生产日期',
			width: 200
		},{
			field : 'leaveData', 
			align : 'left',
			title : '出厂日期',
			width: 200
		},{
			field : 'remarks', 
			align : 'left',
			title : '备注',
			width: 300
		}]]
	});
	
	$("#add_button").bind("click",function(){
		createWithoutCancle("录入车辆信息","/EVIS/vehicle/add",700,400);
	});
	$("#edit_button").bind("click",function(){
		createWithoutCancle("编辑车辆信息","/EVIS/vehicle/edit",700,400);
	});
})
function doSearch(){
	var t = $.serializeObject($('#searchForm'));
	$('#vehicleList').datagrid('load', t); 
}