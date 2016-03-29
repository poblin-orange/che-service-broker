package com.orange.chebroker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.GetLastServiceOperationRequest;
import org.springframework.cloud.servicebroker.model.GetLastServiceOperationResponse;
import org.springframework.cloud.servicebroker.model.OperationState;
import org.springframework.cloud.servicebroker.model.UpdateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.UpdateServiceInstanceResponse;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Service;

@Service
public class CheServiceInstanceService implements ServiceInstanceService {
	
	
	private static Logger logger=LoggerFactory.getLogger(CheServiceInstanceService.class.getName());
	
	@Override
	public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest arg0) {
		logger.info("a service instance created.");
		return new CreateServiceInstanceResponse();
	}

	@Override
	public DeleteServiceInstanceResponse deleteServiceInstance(DeleteServiceInstanceRequest arg0) {
		logger.info("a service instance deleted.");
		return new DeleteServiceInstanceResponse();
	}

	@Override
	public GetLastServiceOperationResponse getLastOperation(GetLastServiceOperationRequest arg0) {
		//TODO check id -> succeeded or failed
		return new GetLastServiceOperationResponse(OperationState.SUCCEEDED);
	}

	@Override
	public UpdateServiceInstanceResponse updateServiceInstance(UpdateServiceInstanceRequest arg0) {
		return new UpdateServiceInstanceResponse();
	}

}
