package com.qxl.common.mapper;


import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.qxl.client.mapper.ClientMapper;

/**   
 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.qxl.com 
 * @author: hp   
 * @date: 2017年11月20日 下午4:29:39 
 */
@Component
public class commonMapper{
	protected ClientMapper clientMapper;
	@Resource(name="clientMapper")
	public void setClientMapper(ClientMapper clientMapper) {
		this.clientMapper = clientMapper;
	}
	
}
