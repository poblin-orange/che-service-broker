package com.orange.chebroker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orange.chebroker.repository.CfClient;
import com.orange.chebroker.repository.CheApiClient;
import com.orange.chebroker.repository.DockerClient;

public class CheProvisionerImpl implements CheProvisioner {
	
	private static Logger logger=LoggerFactory.getLogger(CheProvisionerImpl.class.getName());

	@Autowired
	DockerClient dockerClient;
	
	@Autowired
	CfClient cfClient;
	
	@Autowired
	CheApiClient cheApiClient;

	@Override
	public String createChe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteChe(String serviceInstanceId) {
		// TODO Auto-generated method stub
		
	}
	
	
}
