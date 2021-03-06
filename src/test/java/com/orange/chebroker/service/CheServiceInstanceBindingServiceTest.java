/*
 *
 *  * Copyright (C) 2015 Orange
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *
 *
 */

package com.orange.chebroker.service;

import com.orange.chebroker.model.CredentialsRepository;
import com.orange.chebroker.model.ServicePlan;
import com.orange.chebroker.model.ServicePlanBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingResponse;


/**
 * Created by sbortolussi on 07/03/2016.
 */
public class CheServiceInstanceBindingServiceTest {

    public static final String API_DIRECTORY_SERVICE = "API_DIRECTORY";
    public static final String DEV_PLAN = "dev";
    public static final String PROD_PLAN = "prod";
    public static final String DUMMY_PLAN = "dummy";

    public static final ServicePlan SERVICE_PLAN_DEV= new ServicePlanBuilder().withServiceID(API_DIRECTORY_SERVICE).withPlanID(DEV_PLAN).build();
    public static final ServicePlan SERVICE_PLAN_PROD= new ServicePlanBuilder().withServiceID(API_DIRECTORY_SERVICE).withPlanID(PROD_PLAN).build();
    public static final ServicePlan SERVICE_PLAN_DUMMY= new ServicePlanBuilder().withServiceID(API_DIRECTORY_SERVICE).withPlanID(DUMMY_PLAN).build();


    @Test
    public void should_bind_with_credentials_that_have_been_set_for_associated_service_plan() throws Exception {

        CredentialsRepository credentialsRepository = new CredentialsRepository();
        //given credentials have been set for dev plan of service API_DIRECTORY
        credentialsRepository.save(SERVICE_PLAN_DEV, "CREDENTIALS_URI","http://mydev-api.org");
        credentialsRepository.save(SERVICE_PLAN_DEV,"CREDENTIALS_ACCESS_KEY","devAZERTY");
        //given credentials have been set for prod plan of service API_DIRECTORY
        credentialsRepository.save(SERVICE_PLAN_PROD,"CREDENTIALS_URI","http://myprod-api.org");
        credentialsRepository.save(SERVICE_PLAN_PROD,"CREDENTIALS_ACCESS_KEY","prodAZERTY");

        //when I bind my app to a service API_DIRECTORY instance whose plan is dev
        CheServiceInstanceBindingService serviceInstanceBindingService = new CheServiceInstanceBindingService(credentialsRepository);
        final CreateServiceInstanceBindingResponse response = serviceInstanceBindingService.createServiceInstanceBinding(getCreateServiceInstanceRequestWithServiceAndPlan(SERVICE_PLAN_DEV));

        //then I should only get credentials that have been set for dev plan of service API_DIRECTORY
        Assert.assertNotNull(response.getCredentials());
        Assert.assertEquals(2,response.getCredentials().size());
        Assert.assertEquals("http://mydev-api.org",response.getCredentials().get("CREDENTIALS_URI"));
        Assert.assertEquals("devAZERTY",response.getCredentials().get("CREDENTIALS_ACCESS_KEY"));

    }

    @Test
    public void should_bind_with_no_credentials_if_no_credentials_have_been_set_for_associated_service_plan() throws Exception {

        CredentialsRepository credentialsRepository = new CredentialsRepository();
        //given credentials have been set for dev plan of service API_DIRECTORY
        credentialsRepository.save(SERVICE_PLAN_DEV,"CREDENTIALS_URI","http://mydev-api.org");
        credentialsRepository.save(SERVICE_PLAN_DEV,"CREDENTIALS_ACCESS_KEY","devAZERTY");
        //given credentials have been set for prod plan of service API_DIRECTORY
        credentialsRepository.save(SERVICE_PLAN_PROD,"CREDENTIALS_URI","http://myprod-api.org");
        credentialsRepository.save(SERVICE_PLAN_PROD,"CREDENTIALS_ACCESS_KEY","prodAZERTY");

        //when I bind my app to a service API_DIRECTORY instance whose plan is dummy
        CheServiceInstanceBindingService serviceInstanceBindingService = new CheServiceInstanceBindingService(credentialsRepository);
        final CreateServiceInstanceBindingResponse response = serviceInstanceBindingService.createServiceInstanceBinding(getCreateServiceInstanceRequestWithServiceAndPlan(SERVICE_PLAN_DUMMY));

        //then I should get no credentials
        Assert.assertNull(response.getCredentials());

    }

    private CreateServiceInstanceBindingRequest getCreateServiceInstanceRequestWithServiceAndPlan(ServicePlan servicePlan) {
        return new CreateServiceInstanceBindingRequest("serviceDefinitionId", servicePlan.getPlanUid(),"appGuid",null);
    }

}