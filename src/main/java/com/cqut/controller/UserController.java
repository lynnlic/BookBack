package com.cqut.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cqut.service.IUserService;

@Controller
public class UserController {
	@Resource
	private IUserService service;
	
	/**
	 * 
	 * 方法简述：获取员工信息
	 * @param row
	 * @param page
	 * @param user_account
	 * @return 
	 * @author huhongjie
	 * @date 2017-7-1 下午6:53:16
	 */
	@RequestMapping(value="/user/getuser",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getUser(@RequestParam("row") int row,
			@RequestParam("page")int page,
			@RequestParam("user_name") String user_name) throws UnsupportedEncodingException {
		    user_name=new String(user_name.getBytes("iso8859-1"),"UTF-8");//将参数转为UTF-8编码格式
		    Map<String, Object> result = service.getUser(row, page, user_name);
		    if(result == null){
				return "-1";
			}
		    return JSON.toJSON(result).toString();
	}
	
	/**
	 * 
	 * 方法简述：新增一个员工
	 * @param user
	 * @return 
	 * @author huhongjie
	 * @date 2017-7-1 下午6:53:40
	 */
	@RequestMapping(value="/user/adduser",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String addUser(@RequestBody Map<String, Object> user){
		Map<String, Object> result = service.addUser(user);
		return JSONObject.toJSONString(result);
	}
	
	/**
	 * 
	 * 方法简述：修改一个员工信息
	 * @param id
	 * @param user
	 * @return 
	 * @author huhongjie
	 * @date 2017-7-1 下午6:56:10
	 */
	@RequestMapping(value="/user/updateuser/{user_id}",method=RequestMethod.PUT,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String updateUser(@PathVariable(value = "user_id") String user_id,
			@RequestBody Map<String, Object> user){
		Map<String, Object> result = service.updateUser(user_id, user);
		return JSONObject.toJSONString(result);
	}
	
	/**
	 * 
	 * 方法简述：删除一个或者多个员工
	 * @param user_id
	 * @return 
	 * @author huhongjie
	 * @date 2017-7-1 下午8:09:29
	 */
	@RequestMapping(value="/user/deleteuser/{user_id}",method=RequestMethod.DELETE,produces="application/json;charset=UTF-8")
	@ResponseBody
	public int deleteUser(@PathVariable(value = "user_id") String user_id){
		int result = service.deleteUser(user_id);
		return result;
	}
	
	/**
	 * 
	 * 方法简述：获取部门名称列表
	 * @return 
	 * @author huhongjie
	 * @date 2017-7-28 下午2:53:52
	 */
	@RequestMapping(value="/user/getnames",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getDepartmentName(){
		    List<Map<String, Object>> result = service.getDepartmentName();
		    if(result == null){
				return "-1";
			}
		    return JSON.toJSON(result).toString();
	}
}
