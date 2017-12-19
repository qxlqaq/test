package com.qxl.client.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qxl.common.service.commonService;
import com.qxl.pojo.Client;
import com.qxl.util.MD5;
import com.qxl.util.VerifyCodeUtil;

import net.sf.json.JSONObject;

/**   
 * Copyright 漏 2017 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.qxl.client.controller 
 * @author: hp   
 * @date:  
 */
@Controller
@RequestMapping("/client")
public class ClientController extends commonService{
	/**
	 * 获取sesion
	 * */
	@RequestMapping("/getUserName")
	@ResponseBody
	public String getUserName(HttpServletRequest request) throws Exception
	{
		JSONObject result=new JSONObject();
		//获取实体的session
		HttpSession session=request.getSession();
		Client clientEnty=(Client) session.getAttribute("clientEnty");
		//判断session是否有值
		if(clientEnty!=null){
			String username=clientEnty.getCname();
			String cid=clientEnty.getId();
			result.put("username", username);
			result.put("cid", cid);
		}
		return result.toString();
	}
	/**
	 * 注销session
	 * */
	@RequestMapping("/deleSession")
	@ResponseBody
	public String deleSession(HttpServletRequest request) throws Exception
	{
		JSONObject result=new JSONObject();
		HttpSession session=request.getSession();
		Client clientEnty=(Client) session.getAttribute("clientEnty");
		//判断session是否有值
		if(clientEnty!=null){
			//清除session
			session.invalidate();
			result.put("msg", true);
		   return result.toString();
		}
		result.put("msg", false);
		return result.toString();
	}
	/**
	 * 登录    
	 * */
	@RequestMapping(value="/login", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String login(@RequestBody Client client,HttpServletRequest request)throws Exception{
		HttpSession session=request.getSession();
		MD5 m=new MD5();
		//获取验证码，前台传入，后台session取出
		String verify1= session.getAttribute("verify").toString();
		String verify=client.getVerify();
		//对密码进行加密
		String password=client.getCpassword();
		String pwd=m.getMD5ofStr(password);
		client.setCpassword(pwd);
		//进行查询
		Client clien=clientService.findClient(client);
		JSONObject result=new JSONObject();
		String cname=client.getCname();
		//查询结果是否为空
		if(cname.equals("")||cname.equals(null)){
			result.put("str", "用户名不能为空");
			return result.toString();
		}
		if(password.equals("")||password.equals(null)){
			result.put("str", "密码不能为空");
			return result.toString();
		}
		if(verify.equals("")||verify.equals(null)){
			result.put("str", "验证码不能为空");
			return result.toString();
		}
		if(clien==null){
			result.put("str", "用户名或密码错误");
			return result.toString();
		}
		String pwd1=clien.getCpassword();
		if(!verify1.equals(verify))
		{
			result.put("str", "验证码错误");
			return result.toString();
		}
		if(!pwd.equals(pwd1)){
			result.put("str", "用户名或密码错误");
			return result.toString();
		}
		if(clien!=null&&verify1.equals(verify)&&pwd.equals(pwd1)){
			session.setAttribute("clientEnty", clien);
			result.put("str",true);
		}
		return result.toString();
	}
	
	/**
	 * 验证码
	 * */
	@RequestMapping("/verify")
	public void verify(HttpServletRequest request,HttpServletResponse response) throws IOException {
		// 获取HttpSession 通过request获取的
		HttpSession session = request.getSession();
		// 生成一个6位的随机字符串
		String VerifyCode = VerifyCodeUtil.getNumber(6);
		OutputStream output = response.getOutputStream();
		// VerifyCodeUtil.create(随机字符,宽,高,格式,输出流);
		String verifystr = VerifyCodeUtil.create(VerifyCode, 80, 45, "JPG", output);
		output.close();
		// 把验证码 放入session
		session.setAttribute("verify", verifystr);
		System.out.println("验证码:" + verifystr);
	}
	/**
	 * 注册
	 * */
	@RequestMapping(value="/save", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String save(@RequestBody Client client,HttpServletRequest request)throws Exception{
		HttpSession session=request.getSession();
		MD5 m=new MD5();
		//密码加密
		String pwd1=client.getCpassword();
		String pwd=m.getMD5ofStr(pwd1);
		//注入密码，随机主键id
		client.setCpassword(pwd);
		client.setId(UUID.randomUUID().toString());
		//获取验证码，前台传入，后台session取出
		String verify=client.getVerify();
		String phone=client.getTelephone();
		String cname=client.getCname();
		JSONObject result=new JSONObject();
		if(cname.equals("")||cname.equals(null)){
			result.put("msg", "用户名不能为空");
			return result.toString();
		}
		if(pwd1.equals("")||pwd1.equals(null)){
			result.put("msg", "密码不能为空");
			return result.toString();
		}
		if(phone.equals("")||phone.equals(null)){
			result.put("msg", "手机号不能为空");
			return result.toString();
		}
		if(phone.length()!=11){
			result.put("msg", "手机号有误");
			return result.toString();
		}
		if(verify.equals("")||verify.equals(null))
		{
			result.put("msg", "验证码不能为空");
			return result.toString();
		}
		if(verify.length()!=4){
			result.put("msg", "验证码有误");
			return result.toString();
		}
		String verify1= session.getAttribute("verify").toString();
		if(!verify.equals(verify1)){
			result.put("msg", "验证码输入错误");
			return result.toString();
		}
		Client clientEnty=(Client) session.getAttribute("clientEnty");
		if(clientEnty==null||clientEnty.equals("")){
			session.invalidate();
			result.put("msg", "用户已经被注册");
			return result.toString();
		}
		String saveName=clientEnty.getCname();
		if(!saveName.equals(cname)){
			session.invalidate();
			result.put("msg", "用户已经被注册");
			return result.toString();
		}
		int i=clientService.saveClient(client);
		if(i>0){
			session.invalidate();
			result.put("msg", true);
		}else {
			result.put("msg", "注册失败，请重试");
		}
		return result.toString();
	}
	/**
	 * 注册验证
	 * */
	@RequestMapping("/saveName")
	@ResponseBody
	public String saveName(@RequestBody Client client,HttpServletRequest request)throws Exception{
		List list=clientService.findName(client);
		HttpSession session=request.getSession();
		JSONObject result=new JSONObject();
		if(list.size()==0){
			session.setAttribute("clientEnty", client);
			result.put("msg", true);
		}else {
			result.put("msg", false);
		}
		return result.toString();
	}
	/**
	 * 修改
	 * */
	@RequestMapping("/update")
	@ResponseBody
	public String update(@RequestBody Client client,HttpServletRequest request)throws Exception{
		HttpSession session=request.getSession();
		String cname=session.getAttribute("updateName").toString();
		client.setCname(cname);
		MD5 m=new MD5();
		String pwd=m.getMD5ofStr(client.getCpassword());
		client.setCpassword(pwd);
		int i=clientService.updatePassword(client);
		JSONObject result=new JSONObject();
		if(i>0){
			result.put("msg", true);
		}else {
			result.put("msg", false);
		}
		return result.toString();
	}
	/**
	 * 修改验证
	 * */
	@RequestMapping(value="/updateName",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateName(@RequestBody Client client,HttpServletRequest request)throws Exception{
		List list=clientService.findNameTelephone(client);
		JSONObject result=new JSONObject();
		String phone=client.getTelephone();
		String cname=client.getCname();
		if(cname.equals("")||cname.equals(null)){
			result.put("msg", "账号不能为空");
			return result.toString();
		}
		if(phone.equals("")||phone.equals(null)){
			result.put("msg", "空手机号不能为空");
			return result.toString();
		}
		if(phone.length()!=11){
			result.put("msg", "手机号格式有误");
			return result.toString();
		}
		if(list.size()!=0){
			HttpSession session=request.getSession();
			session.setAttribute("updateName", client.getCname());
			result.put("msg", true);
		}else {
			result.put("msg", "账号或者或者手机号有误");
		}
		return result.toString();
	}
}
