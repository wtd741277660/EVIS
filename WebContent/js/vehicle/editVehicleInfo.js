$(document).ready(function(){
	
	//初始化车辆编号
	initVehicleNum();
	
	//处理上传照片按钮的显示
	$(".savedFiles").each(function(){
		var value = $(this).val();
		if(value != null && value != "" && value != undefined){
			changeToReLoad($(this).parent());
		}
	});
	
	$("input[type=file]").fileupload({
//		dataType:"json",
		autoUpload: false,
		type:"post",
		add:function(e,data){
			var url = "/EVIS/vehicle/saveFiles?vehicleNum=" + vehicleNum + "&type=edit";
			$(this).fileupload('option', 'url', url);
			data.submit();
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
	
	//保存车辆信息
	$("#saveBtn").bind("click",function(){
		if(jQuery("form").form('validate')){
        	var vehicleType = $("#vehicleType").val();
        	var customerName = $("#customerName").val();
        	var produceDate = $("#produceDate").datebox('getValue');
        	var leaveDate = $("#leaveDate").datebox('getValue');
        	if(vehicleType == null || vehicleType == ""){
        		parent.$.messager.show({
           			title:'提示',
           			msg:'车辆型号不能为空！',
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
        	}
    		//如果验证成功，就通过ajax方式将数据传到后台并保存
    		var data = $.serializeObject($('#addForm'));
    		$.ajax({
    		     type: 'POST',
    		     url: "/EVIS/vehicle/updateVehicle" ,
    		     data: data ,
    		     dataType: "json",
    		     success: function(result){
    		    	 var success = result.success;
    		    	 if(success == "true"){
						parent.$.messager.show({
	               			title:'提示',
	               			msg:'车辆信息编辑成功！',
	               			timeout:3000,
	               			showType:'slide'
	               		});
						getOpener().doSearch();
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
    			msg:'编辑失败，请将页面中的必填项输入完整！',
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
			url:"/EVIS/vehicle/cancleUpdate",
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
});
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
			url:"/EVIS/vehicle/deleteFiles?type=edit",
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