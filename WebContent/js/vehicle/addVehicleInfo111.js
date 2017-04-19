$(document).ready(function(){
	//初始化车辆编号
	initVehicleNum();
	
	$(".addPhoto").bind("click",function(){
		createWithoutCancle("上传照片","/EVIS/vehicle/addPhoto",300,200);
	});
	
	$("input[type=file]").fileupload({
//		dataType:"json",
		autoUpload: true,
		type:"post",
		add: function (e,result) {
		   var vehicleNum = $("#vehicleNum").val();
	　      if(vehicleNum == null || vehicleNum == "" || vehicleNum == "请先输入整机编号"){
	　      		   parent.$.messager.show({
	　      				title:'消息',
	       				msg:'尚未上传照片，无法取消上传！',
	       				timeout:5000,
	       				showType:'slide'
	    		});
	　      		   return;
	　      }
		},
        done: function (e, result) {
            //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
            //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
            //返回的数据在result.result中，假设我们服务器返回了一个json对象
　　　　　　　　　 //但是由于IE10以下存在bug，会将XHR的内容识别错误，所以第一需要返回Content-Type: text/plain
　　　　　　　　　 //其次，及时转成text/plain还存在取不到result.result的内容，取到的是其他的东西
　　　　　　　        //需要用这个方法来接值，var jmsg = result.result[0].body ? result.result[0].body.innerHTML : result.result;
  　　　　　　　　 //最后接到值后，发现还有<pre></pre>包着需要通过字符串处理去掉这个东西
            //json对象{"newName": "sss", "oldName": "sdf"}
        	
			/*var resultJson;
			var jmsg = result.result[0].body ? result.result[0].body.innerHTML : result.result;
			var startIndex = jmsg.indexOf("{");
			var lastIndex = jmsg.lastIndexOf("}");
			jmsg = jmsg.substring(startIndex, lastIndex+1);
			try { 
				resultJson = $.parseJSON(jmsg); 
			}catch (e) { 
				resultJson = jmsg; 
			}*/
        	var data = result.result;
			var uploadDiv = $(e.target).parent().parent().parent();
			uploadDiv.find(".savedFiles").val(data.successNames);
			//修改按钮文本
			changeToReLoad(uploadDiv);
        },
        fail:function(e,data){
        	alert("上传失败");
        },
        progressall: function (e, data) {
            var maxWidth = 160;
            var percent = (data.loaded / data.total * 100).toFixed(2);
            var progress = parseInt(data.loaded / data.total * maxWidth, 10);
            var uploadDiv = $(e.target).parent().parent().parent();
            uploadDiv.find(".progress").show();
            uploadDiv.find(".bar").css("width", progress);
            uploadDiv.find(".progresspercent").show().text(percent + "%");
        },
	　   /*change: function (e, data) {
	　　 	   $.each(data.files, function (index, file) {
	　　		    alert('Selected file: ' + file.name);
	　　	   });
	　　 }*/
    });
	
	$("#saveBtn").bind("click",function(){
		
	});
});

function initVehicleNum(){
	var value = $("#vehicleNum").val();
	if(value==""){
		$("#vehicleNum").val("请先输入整机编号");
		$("#vehicleNum").css("color","gray");
	}
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
        alert("让选择要上传的图片!");
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
            alert("上传图片类型不正确!");
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
				var remainFiles = data.remainFiles;
				var remainNames = data.remainNames;
				if(remainFiles != null && remainFiles != ""){
					parent.$.messager.show({
						title:'消息',
						msg:remainNames + '取消上传失败！',
						timeout:5000,
						showType:'slide'
					});
					//修改上传的文件
					uploadDiv.find(".savedFiles").val(remainFiles);
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