package com.orange.chebroker.service;

import com.orange.chebroker.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.stereotype.Service;

import com.orange.model.CredentialsRepository;

@Service
public class CheServiceInstanceBindingService implements ServiceInstanceBindingService {
    private CredentialsRepository credentialsRepository;

    @Autowired
    public CheServiceInstanceBindingService(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public CreateServiceInstanceBindingResponse createServiceInstanceBinding(CreateServiceInstanceBindingRequest request) {
        String planId = request.getPlanId();
        Credentials credentials = credentialsRepository.findByPlan(planId);
        return new CreateServiceInstanceBindingResponse(credentials != null ? credentials.toMap() : null);
    }

    @Override
    public void deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest arg0) {
    }
}