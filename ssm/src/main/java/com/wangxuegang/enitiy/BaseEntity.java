package com.wangxuegang.enitiy;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @类描述：实体基类
 * @项目名称：ssm
 * @包名： com.wangxuegang.enitiy
 * @类名称：BaseEntity
 * @创建人：wangxuegang
 * @创建时间：2018年12月24日上午10:51:11
 * @mail 15510235102@163.com
 * @version v1.0
 
 */
public class BaseEntity implements Serializable{

	
	/**
	 * @字段：serialVersionUID
	 * @功能描述：序列号
	 * @创建人：wangxuegang
	 * @创建时间：2018年12月24日上午11:08:59
	 */
	
	private static final long serialVersionUID = 1586396000955555210L;

	@Override  
    public String toString() {  
        return ReflectionToStringBuilder.toString(this, ToStringStyle.NO_CLASS_NAME_STYLE);  
    }
}
