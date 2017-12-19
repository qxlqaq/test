package com.qxl.client.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qxl.common.mapper.commonMapper;
import com.qxl.pojo.Client;

/**   
 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.qxl.client.service 
 * @author: hp   
 * @date: 2017年12月3日 下午2:26:04 
 */
@Service("clientService")
public class ClientServiceImp extends commonMapper implements ClientService{

	@Override
	public Client findClient(Client client)throws Exception {
		return clientMapper.findClient(client);
	}
	
	@Override
	public int saveClient(Client client) throws Exception{
		return clientMapper.saveClient(client);
	}

	@Override
	public List<Client> findName(Client client)throws Exception {
		return clientMapper.findName(client);
	}

	@Override
	public List<Client> findTelephone(Client client)throws Exception {
		return clientMapper.findTelephone(client);
	}

	@Override
	public int updatePassword(Client client)throws Exception {
		return clientMapper.updatePassword(client);
	}

	@Override
	public List<Client> findNameTelephone(Client client)throws Exception {
		return clientMapper.findNameTelephone(client);
	}
	
}
