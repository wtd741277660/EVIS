package com.ly.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.entity.Params;
import com.ly.service.ParamService;
import com.ly.util.Pagination;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
@RequestMapping("/params")
public class ParamsController {
	
	@Autowired
	private ParamService paramService;

	@RequestMapping("/paramList")
	public String paramList(HttpServletRequest request,Model model){
		String paramType = request.getParameter("paramType");
		model.addAttribute("paramType", paramType);
		return "sysconfig/paramList";
	}
	
	@RequestMapping("/queryParamList")
	@ResponseBody
	public JSONObject queryParamList(HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONObject result = new JSONObject();
		String pageIndexStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		int pageIndex = 1;
		int pageSize = 20;
		if (pageIndexStr != null && !pageIndexStr.isEmpty()) {
			try {
				pageIndex = Integer.parseInt(pageIndexStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (pageSizeStr != null && !pageSizeStr.isEmpty()) {
			try {
				pageSize = Integer.parseInt(pageSizeStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Pagination<Map<String, Object>> pagination = paramService.queryList(request, pageIndex, pageSize);
		JSONArray array = new JSONArray();
		if (pagination.getItems() != null) {
			for(Map<String, Object> map:pagination.getItems()){
				JSONObject obj = new JSONObject();
				obj.put("id", map.get("ID"));
				obj.put("name", map.get("NAME"));
				if (map.get("CREATE_TIME") != null) {
					obj.put("createTime", sdf.format(map.get("CREATE_TIME")));
				}
				if (map.get("UPDATE_TIME") != null) {
					obj.put("updateTime", sdf.format(map.get("UPDATE_TIME")));
				}
				array.add(obj);
			}
		}
		result.put("rows", array);
		result.put("total", pagination.getRowsCount());
		return result;
	}
	
	@RequestMapping("/addParam")
	public ModelAndView add(HttpServletRequest request,Model model){
		String paramType = request.getParameter("paramType");
		model.addAttribute("paramType", paramType);
		Params param = new Params();
		model.addAttribute("param", param);
		return new ModelAndView("sysconfig/addParam");
	}
	
	@RequestMapping("/saveParam")
	@ResponseBody
	public JSONObject saveParam(@ModelAttribute("param") Params param,HttpServletRequest request){
		JSONObject result = new JSONObject();
		result.put("success", "true");
		try {
			paramService.saveParam(param);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "false");
			result.put("errorMsg", "创建参数失败，原因时：" + e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/editParam")
	public String edit(HttpServletRequest request,Model model){
		String paramType = request.getParameter("paramType");
		model.addAttribute("paramType", paramType);
		String id = request.getParameter("id");
		Params param = paramService.getByIdAndType(id, paramType);
		if (param != null) {
			model.addAttribute("name",param.getName());
			model.addAttribute("id", param.getId());
		}
		return "sysconfig/editParam";
	}
	
	@RequestMapping("/updateParam")
	@ResponseBody
	public JSONObject updateParam(Params param,HttpServletRequest request){
		JSONObject result = new JSONObject();
		result.put("success", "true");
		try {
			paramService.updateParam(param);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "false");
			result.put("errorMsg", "更新参数失败，原因时：" + e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/deleteParam")
	@ResponseBody
	public JSONObject deleteParam(HttpServletRequest request){
		JSONObject result = new JSONObject();
		result.put("success", "true");
		try {
			String id = request.getParameter("id");
			String paramType = request.getParameter("paramType");
			paramService.deleteParam(id, paramType);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "false");
			result.put("errorMsg", "删除参数失败，原因时：" + e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/getParamCombo")
	@ResponseBody
	public JSONArray getParamCombo(HttpServletRequest request){
		JSONArray data = new JSONArray();
		String paramType = request.getParameter("paramType");
		if (paramType == null || paramType.isEmpty()) {
			return data;
		}
		List<Params> params = paramService.getByType(paramType);
		for(Params param:params){
			JSONObject obj = new JSONObject();
			obj.put("id", param.getId());
			obj.put("name", param.getName());
			data.add(obj);
		}
		return data;
	}
}
