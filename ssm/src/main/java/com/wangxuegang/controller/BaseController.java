package com.wangxuegang.controller;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @类描述：BaseController
 * @项目名称：ssm
 * @包名： com.wangxuegang.controller
 * @类名称：BaseController
 * @创建人：wangxuegang
 * @创建时间：2018年12月24日下午4:54:15
 * @mail 15510235102@163.com
 * @version v1.0
 */
public class BaseController {
	
	protected String ERROR = "error";
	protected String SUCCESS = "success";
	
	/** 返回信息 */
	protected Map<String,Object> map;

	public Map<String, Object> getMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}
	
}
