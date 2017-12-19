package com.qxl.client.service;

import java.util.List;

import com.qxl.pojo.Client;

/**   
 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.qxl.client.service 
 * @author: hp   
 * @date: 2017年12月3日 下午2:23:53 
 */
public interface ClientService {
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
