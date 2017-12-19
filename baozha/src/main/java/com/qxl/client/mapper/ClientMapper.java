package com.qxl.client.mapper;

import java.util.List;

import com.qxl.pojo.Client;

/**   
 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.qxl.client.mapper 
 * @author: hp   
 * @date: 2017年12月3日 下午12:41:25 
 */
public interface ClientMapper {
	//登录
	Client findClient(Client client)throws Exception;
	//注册
	int saveClient(Client client)throws Exception;
	List<Client> findName(Client client)throws Exception;
	List<Client> findTelephone(Client client)throws Exception;
	//修改密码
	int updatePassword(Client client)throws Exception;
	List<Client> findNameTelephone(Client client)throws Exception;
}
