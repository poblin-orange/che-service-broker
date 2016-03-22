package com.orange.chebroker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.chebroker.model.Credentials;
import com.orange.chebroker.model.CredentialsRepository;

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