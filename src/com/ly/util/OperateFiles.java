package com.ly.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;

import com.alibaba.fastjson.JSONObject;

public class OperateFiles {

	private static String savePath = "";
	
	static{
		ResourceBundle resource = ResourceBundle.getBundle("configSystem");
		savePath = resource.getString("savePath");
		System.out.println("文件路径：" + savePath);
	}
	
	/**
	 * 保存文件
	 * @param item 文件对象，只包含文件名和文件内容
	 * @return 返回文件全路径，如果保存失败，则返回"FALSE"
	 */
	public static String  saveFiles(FileItem item,String vehicleNum,String type){
		String filePath = savePath + File.separator;
		if (type != null && !type.isEmpty() && type.equals("type")) {
			filePath += vehicleNum + "_edit";
		}
		String result = "FALSE";
		File tempFile = new File(item.getName());
		try {
			File folder = new File(filePath);
			if (!folder.exists()) {
				folder.mkdir();
			}
			//上传文件的保存路径
			File file = new File(filePath, tempFile.getName());
			item.write(file);
			result = file.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据文件路径删除文件，失败的返回文件名包括路径，成功的返回文件名
	 * @param filePath
	 * @return
	 */
	public static JSONObject deleteFile(String[] filePaths){
		JSONObject json = new JSONObject();
		List<String> remainFiles = new ArrayList<String>();//保存未删除的文件的全名
		List<String> remainNames = new ArrayList<String>();//保存未删除的文件名
		List<String> deletedFiles = new ArrayList<String>();
		for(String filePath:filePaths){
			File file = new File(filePath);
			if (file.exists()) {
				try {
					file.delete();
					deletedFiles.add(file.getName());
				} catch (Exception e) {
					remainFiles.add(filePath);
					remainNames.add(file.getName());
					e.printStackTrace();
					LogUtil.log("文件" + filePath + "删除失败", Level.ERROR, e);
				}
			}else {
				deletedFiles.add(file.getName());
			}
		}
		if (remainFiles != null && remainFiles.size() > 0) {
			json.put("remainFiles", StringUtils.join(remainFiles,","));
			json.put("remainNames", StringUtils.join(remainNames,","));
		}
		if (deletedFiles != null && deletedFiles.size() > 0) {
			json.put("deletedFiles", StringUtils.join(deletedFiles,","));
		}
		return json;
	}
	
	/**
	 * 删除vehicleNum对应的所有上传照片
	 * @param vehicleNum
	 * @return
	 */
	public static boolean deleteFileByVehicleNum(String vehicleNum,String type){
		String filePath = savePath + File.separator;
		if (type != null && !type.isEmpty() && type.equals("type")) {
			filePath += vehicleNum + "_edit";
		}
		File file = new File(filePath);
		if (file.exists()) {
			try {
				deleteFile(file);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	//删除文件夹或者文件
	private static  void deleteFile(File file) {  
        if (file.exists()) {//判断文件是否存在   
             if (file.isFile()) {//判断是否是文件   
                 file.delete();//删除文件    
             }else if(file.isDirectory()){//否则如果它是一个目录   
                 File[] files = file.listFiles();//声明目录下所有的文件 files[];  
                 for(int i = 0;i < files.length;i ++){//遍历目录下所有的文件   
                     deleteFile(files[i]);//把每个文件用这个方法进行迭代   
                 }  
                 file.delete();//删除文件夹   
             }else{
            	System.out.println("所删除的文件不存在");  
            }
        }  
     }
}
