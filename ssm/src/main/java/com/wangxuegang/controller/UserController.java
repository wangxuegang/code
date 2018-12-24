package com.wangxuegang.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangxuegang.model.UserVo;
import com.wangxuegang.service.UserService;

/**
 * 
 * @类描述：UserController
 * @项目名称：ssm
 * @包名： com.wangxuegang.controller
 * @类名称：UserController
 * @创建人：wangxuegang
 * @创建时间：2018年12月24日下午4:47:38
 * @mail 15510235102@163.com
 * @version v1.0
 
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * @描述: 添加用户
	 * @方法名: addUser
	 * @param userVo
	 * @return
	 * @返回类型 Map<String,Object>
	 * @创建人 wangxuegang
	 * @创建时间 2018年12月24日下午5:02:14
	 * @since
	 * @throws
	 */
	@RequestMapping("/addUser")
	@ResponseBody
	public Map<String,Object> addUser(@RequestBody UserVo userVo){
		Map<String,Object> map = this.getMap();
		userService.saveUser(userVo);
		return map;
	}
}
