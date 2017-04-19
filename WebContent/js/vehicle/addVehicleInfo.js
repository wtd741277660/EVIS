$(document).ready(function(){
	
	//初始化车辆编号
	var value = $("#vehicleNum").val();
	if(value==""){
		$("#vehicleNum").val("请先输入整机编号");
		$("#vehicleNum").css("color","gray");
	}
	
	//初始化参数下拉框
	$('#frontSuspensionSystem').combobox({    
	    url:'../params/getParamCombo?paramType=01',    
	    valueField:'id',    
	    textField:'name',
	    panelHeight:'auto'
	});
	$('#rearSuspensionSystem').combobox({    
	    url:'../params/getParamCombo?paramType=02',    
	    valueField:'id',    
	    textField:'name',
	    panelHeight:'auto'
	});  
	$('#electronicControlSystem').combobox({    
	    url:'../params/getParamCombo?paramType=03',    
	    valueField:'id',    
	    textField:'name',
	    panelHeight:'auto'
	});  
	$('#brakeSystem').combobox({    
	    url:'../params/getParamCombo?paramType=04',    
	    valueField:'id',    
	    textField:'name',
	    panelHeight:'auto'
	});  
	$('#windshieldSystem').combobox({    
	    url:'../params/getParamCombo?paramType=05',    
	    valueField:'id',    
	    textField:'name',
	    panelHeight:'auto'
	});  
	$('#ceilingSystem').combobox({    
	    url:'../params/getParamCombo?paramType=06',    
	    valueField:'id',    
	    textField:'name',
	    panelHeight:'auto'
	});  
	
	$("input[type=file]").fileupload({
//		dataType:"json",
		autoUpload: false,
		type:"post",
		add:function(e,data){
			debugger;
			var fileupload =  $(this);
			var vehicleNum = $("#vehicleNum").val();
			if(vehicleNum == null || vehicleNum == "" || vehicleNum == "请先输入整机编号"){
				$.messager.alert('消息提示','上传照片之前请先填写车辆编号！','warning');
			}else{
				var readonly = $("#vehicleNum").attr("readonly");
				if(readonly == false || readonly == undefined){
					$.messager.confirm('确认对话框', '上传照片之后车辆编号不能再修改！', function(r){
						if (r){
							var url = "/EVIS/vehicle/saveFiles?vehicleNum=" + vehicleNum;
							fileupload.fileupload('option', 'url', url);
							data.submit();
							$("#vehicleNum").attr("readonly","readyonly");
						}
					});
				}else{
					var url = "/EVIS/vehicle/saveFiles?vehicleNum=" + vehicleNum;
					fileupload.fileupload('option', 'url', url);
					data.submit();
				}
			}
		},
		done:function(e,data){
			var result = data.result;
			var uploadDiv = $(e.target).parent().parent().parent();
			uploadDiv.find(".savedFiles").val(result.successNames);
			//修改按钮文本
			changeToReLoad(uploadDiv);
		},
		fail:function(e,result){
			$.messager.alert('消息提示','上传失败！','warning');
		},
		progressall:function(e,data){
			var maxWidth = 160;
            var percent = (data.loaded / data.total * 100).toFixed(2);
            var progress = parseInt(data.loaded / data.total * maxWidth, 10);
            var uploadDiv = $(e.target).parent().parent().parent();
            uploadDiv.find(".progress").show();
            uploadDiv.find(".bar").css("width", progress);
            uploadDiv.find(".progresspercent").show().text(percent + "%");
		}
	});
	
	var isRepeat = false;
	//判断车辆编号的重复
	$("#vehicleNum").blur(function(){
		var value = $("#vehicleNum").val();
		if(value==""){
			$("#vehicleNum").val("请先输入整机编号");
			$("#vehicleNum").css("color","gray");
		}else if(value != "请先输入整机编号"){
	    	$.ajax({
	    	 	type:"post",
	    	 	url:"/EVIS/vehicle/checkRepeat",
	    	 	data:{"vehicleNum":value},
	    	 	dataType:"json",
	    	 	async:false,
	    	 	success:function(data){
	    	 		if(data.success != null && data.success == "true"){
	               		isRepeat = true;
	    	 		}else{
	    	 			isRepeat = false;
	    	 		}
	    	 		if(isRepeat){
	    	    		parent.$.messager.show({
	    	       			title:'提示',
	    	       			msg:'该编号已经被使用，请修改！',
	    	       			timeout:3000,
	    	       			showType:'slide'
	    	       		});
	    	    	}
	    	 	}
	    	});
		}
	});
	
	//保存车辆信息
	$("#saveBtn").bind("click",function(){
		if(jQuery("form").form('validate')){
			var vehicleNum = $("#vehicleNum").val();
			if(vehicleNum == null || vehicleNum == "" || vehicleNum == "请先输入整机编号"){
				parent.$.messager.show({
           			title:'提示',
           			msg:'车辆编号不能为空！',
           			timeout:3000,
           			showType:'slide'
           		});
           		return false;
			}
        	//判断账号是否重复
        	/*if(isRepeat){
        		parent.$.messager.show({
           			title:'提示',
           			msg:'该编号已经被使用，请修改！',
           			timeout:3000,
           			showType:'slide'
           		});
           		return false;
        	}
        	var vehicleType = $("#vehicleType").val();
        	var customerName = $("#customerName").val();
        	var produceDate = $("#produceDate").datebox('getValue');
        	var leaveDate = $("#leaveDate").datebox('getValue');
        	if(vehicleType == null || vehicleType == ""){
        		parent.$.messager.show({
           			title:'提示',
           			msg:'整机型号不能为空！',
           			timeout:3000,
           			showType:'slide'
           		});
           		return false;
        	}
        	if(customerName == null || customerName == ""){
        		parent.$.messager.show({
           			title:'提示',
           			msg:'客户姓名不能为空！',
           			timeout:3000,
           			showType:'slide'
           		});
           		return false;
        	}
        	if(produceDate == null || produceDate == ""){
        		parent.$.messager.show({
           			title:'提示',
           			msg:'生产日期不能为空！',
           			timeout:3000,
           			showType:'slide'
           		});
           		return false;
        	}
        	if(leaveDate == null || leaveDate == ""){
        		parent.$.messager.show({
           			title:'提示',
           			msg:'出厂日期不能为空！',
           			timeout:3000,
           			showType:'slide'
           		});
           		return false;
        	}*/
    		//如果验证成功，就通过ajax方式将数据传到后台并保存
    		var data = $.serializeObject($('#addForm'));
    		data.type = "add";
    		$.ajax({
    		     type: 'POST',
    		     url: "/EVIS/vehicle/saveVehicle" ,
    		     data: {"data":JSON.stringify(data)},
    		     dataType: "json",
    		     success: function(result){
    		    	 var success = result.success;
    		    	 if(success == "true"){
						parent.$.messager.show({
	               			title:'提示',
	               			msg:'车辆信息创建成功！',
	               			timeout:3000,
	               			showType:'slide'
	               		});
						getOpener.doSearch();
						closeDialog();
    		    	 }else{
    		    		 parent.$.messager.show({
                 			title:'提示',
                 			msg:result.errorMsg,
                 			timeout:3000,
                 			showType:'slide'
                 		});
    		    	 }
    		     }
    		});
		}else{
			parent.$.messager.show({
    			title:'提示',
    			msg:'创建失败，请将页面中的必填项输入完整！',
    			timeout:3000,
    			showType:'slide'
    		});
			return false;
		}
	});
	$("#closeBtn").bind("click",function(){
		$.ajax({
			type:"post",
			dataType:"json",
			url:"/EVIS/vehicle/cancleSave",
			success:function(result){
				var success = result.success;
				if(success == "false"){
					parent.$.messager.show({
						title:'消息',
						msg:result.errorMsg,
						timeout:5000,
						showType:'slide'
					});
				}else{
					/*parent.$.messager.show({
						title:'消息',
						msg:'已上传照片已经全部删除！',
						timeout:5000,
						showType:'slide'
					});*/
				}
				closeDialog();
			}
		})
	});
	
	//将编辑信息区域隐藏
	$(".editDiv").each(function(){
		$(this).hide();
	})
	
	//编辑信息按钮的点击事件
	$(".editBtn").bind("click",function(){
		//按钮所在的tr
		var tr = $(this).parent().parent();
		//编辑信息所在的div
		var div = $(tr).next().find("div");
		if($(div).is(":hidden")){
			$(div).show();
			$(this).html("收起");
		}else{
			$(div).hide();
			$(this).html("编辑");
		}
	})
});
function closeDialog(){
	var api = frameElement.api;
	api.close();
}
function getOpener(){
	var tab = parent.$('#maintabs').tabs('getSelected');
	var index = parent.$('#maintabs').tabs('getTabIndex',tab);
	var opener = parent.$("iframe[name!=lhgDialog]")[index].contentWindow;
	return opener;
}
function inputVehicleNum(){
	var value = $("#vehicleNum").val();
	if(value=="请先输入整机编号"){
		$("#vehicleNum").val("");
		$("#vehicleNum").css("color","");
	}
}
function CheckFile(obj) {
	var array = new Array('gif', 'jpeg', 'png', 'jpg');  //可以上传的文件类型  
    if (obj.value == '') {
        $.messager.alert('消息提示','上传照片不能为空！','warning');
        return false;
    }else {
        var fileContentType = obj.value.match(/^(.*)(\.)(.{1,8})$/)[3]; //这个文件类型正则很有用：）  
        var isExists = false;
        for (var i in array) {
            if (fileContentType.toLowerCase() == array[i].toLowerCase()) {
                isExists = true;
                return true;
            }
        }
        if (isExists == false) {
            obj.value = null;
            $.messager.alert('消息提示','上传的文件类型只能是jpg、png、gif格式！','warning');
            return false;
        }
        return false;
    }
}
function cancleFile(obj){
	uploadDiv = $(obj).parent().parent();
	var savedFiles = uploadDiv.find(".savedFiles").val();
	if(savedFiles == null || savedFiles == ""){
		parent.$.messager.show({
			title:'消息',
			msg:'尚未上传照片，无法取消上传！',
			timeout:5000,
			showType:'slide'
		});
		return;
	}else{
		$.ajax({
			type:"post",
			dataType:"json",
			url:"/EVIS/vehicle/deleteFiles",
			data:{"fileNames":savedFiles},
			success:function(data){
				debugger;
				var remainFiles = data.remainFiles;
				var remainNames = data.remainNames;
				if(remainFiles != null && remainFiles != ""){
					parent.$.messager.show({
						title:'消息',
						msg:remainNames + '取消上传失败！',
						timeout:5000,
						showType:'slide'
					});
				}else {
					parent.$.messager.show({
						title:'消息',
						msg:'取消上传成功！',
						timeout:5000,
						showType:'slide'
					});
					uploadDiv.find(".progress").hide();
		            uploadDiv.find(".bar").css("width", 0);
		            uploadDiv.find(".progresspercent").hide().text(0 + "%");
		            //修改上传按钮的文字
		            changeToLoad(uploadDiv);
				}
				//修改上传的文件
				uploadDiv.find(".savedFiles").val(remainFiles);
			}
		})
	}
}
//将上传照片修改为重新上传
function changeToLoad(uploadDiv){
	var uploadBtnHtml = uploadDiv.find(".uploadBtn").html();
	uploadBtnHtml = uploadBtnHtml.replace("重新上传","上传照片");
	uploadDiv.find(".uploadBtn").html(uploadBtnHtml);
}
//将重新上传改为上传照片
function changeToReLoad(uploadDiv){
	var uploadBtnHtml = uploadDiv.find(".uploadBtn").html();
	uploadBtnHtml = uploadBtnHtml.replace("上传照片","重新上传");
	uploadDiv.find(".uploadBtn").html(uploadBtnHtml);
}