package com.qxl.pojo;
/**   
 * Copyright 漏 2017 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.qxl.pojo 
 * @author: hp   
 * @date: 
 *            
 */
public class Client {
	String id;			//主键
	String cname;		//客户名（唯一性）
	String cpassword;	//登录密码
	String telephone;	//电话
	String verify;		//验证码
	
	
	public String getVerify() {
		return verify;
	}
	public void setVerify(String verify) {
		this.verify = verify;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}
