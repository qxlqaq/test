package ClientController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONObject;

/**   
 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: ClientController 
 * @author: hp   
 * @date: 2017年12月6日 下午3:43:12 
 */
@Controller
public class client {
	/**
	 * 页面跳转
	 * */
	//首页
	@RequestMapping("/index")
	public String index()throws Exception{
		return "forward:index.html";
	}
	//登录
	@RequestMapping("/log")
	public String log()throws Exception{
		return "forward:/WEB-INF/html/login.html";
	}
	//注册
	@RequestMapping("/save")
	public String save()throws Exception{
		return "forward:/WEB-INF/html/register.html";
	}
	//忘记密码
	@RequestMapping("/update")
	public String update()throws Exception{
		return "forward:/WEB-INF/html/retrievePWD.html";
	}
	//更改密码
	@RequestMapping("/updatePwd")
	public String updatePwd()throws Exception{
		return "forward:/WEB-INF/html/retrievePWD2.html";
	}
	/**
	 * 物流
	 * */
	//寄件
	@RequestMapping("/order")
	public String order()throws Exception{
		return "forward:/WEB-INF/html/add.html";
	}
	//标准准则
	@RequestMapping("/orderCom")
	public String orderCom()throws Exception{
		return "forward:/WEB-INF/html/principle.html";
	}
	//我要查单
	@RequestMapping("/orderSelect")
	public String orderAddress()throws Exception{
		return "forward:/WEB-INF/html/select.html";
	}
	
	//加入我们
	@RequestMapping("/join")
	public String join()throws Exception{
		return "forward:/WEB-INF/html/join.html";
	}
	//联系我们
	@RequestMapping("/contact")
	public String contact()throws Exception{
		return "forward:/WEB-INF/html/contact.html";
	}
	//关于我们
	@RequestMapping("/about")
	public String about()throws Exception{
		return "forward:/WEB-INF/html/aboutus.html";
	}
	//成功
	@RequestMapping("/succes")
	public String succes()throws Exception{
		return "forward:/WEB-INF/html/success.html";
	}
	
}
