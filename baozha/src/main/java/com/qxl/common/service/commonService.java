package com.qxl.common.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.qxl.client.service.ClientService;

/**   
 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.qxl.com 
 * @author: hp   
 * @date: 2017年11月20日 下午5:17:16 
 */
@Component
public class commonService {
	
	protected ClientService clientService;
	//protected String uuid=UUID.randomUUID().toString();			
	
	@Resource(name="clientService")
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
	/**
	 * 生成随机id
	 * @return
	 */
	/*public String UU(){
		String uuid="";   
	    //获取当前时间戳         
	    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");    
	    String temp = sf.format(new Date());    
	    //获取6位随机数  
	    int random=(int) ((Math.random()+1)*100000);    
	    uuid=temp+random; 
		return uuid;
	}
	String a=UU();*/

	
	
}
