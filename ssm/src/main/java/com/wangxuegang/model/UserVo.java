package com.wangxuegang.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @类描述：UserVo
 * @项目名称：ssm
 * @包名： com.wangxuegang.model
 * @类名称：UserVo
 * @创建人：wangxuegang
 * @创建时间：2018年12月24日下午3:03:00
 * @mail 15510235102@163.com
 * @version v1.0
 */
public class UserVo {
	
	/** 用户id */
	@Getter @Setter private Integer userId;
	/** 登录名 */
	@Getter @Setter private String loginName;
	/** 用户名 */
	@Getter @Setter private String userName;
	/** 密码 */
	@Getter @Setter private String password;
	/** 手机号 */
	@Getter @Setter private String phone;
	/** 邮箱 */
	@Getter @Setter private String email;
	/** 创建时间 */
	@Getter @Setter private String createDate;
}
