package com.wangxuegang.enitiy;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @类描述：UserEo
 * @项目名称：ssm
 * @包名： com.wangxuegang.enitiy
 * @类名称：User
 * @创建人：wangxuegang
 * @创建时间：2018年12月24日上午10:58:06
 * @mail 15510235102@163.com
 * @version v1.0
 
 */
public class UserEo extends BaseEntity {
	
	
	/**
	 * @字段：serialVersionUID
	 * @功能描述：序列号
	 * @创建人：wangxuegang
	 * @创建时间：2018年12月24日上午11:09:13
	 */
	
	private static final long serialVersionUID = 4693669829536187438L;
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
