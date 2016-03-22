package com.orange.chebroker.config;

import java.util.Map.Entry;

import com.orange.chebroker.model.Credentials;
import com.orange.chebroker.model.ServicePlan;
import com.orange.chebroker.model.ServicePlanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.orange.chebroker.util.Environment;
import com.orange.chebroker.util.ParserApplicationProperties;
import com.orange.chebroker.util.ParserProperties;
import com.orange.chebroker.model.CredentialsRepository;
import com.orange.chebroker.util.ParserSystemEnvironment;

@Configuration
public class CredentialsConfig {
	@Value("${enable:false}")
	private boolean useApplicationProperties;
	@Autowired
	private ParserApplicationProperties parserApplicationProperties;
	/**
	 * find the map between all plans of all services and its corresponding credentials.
	 * The credentials defined for the whole services may be overridden by plan specific credentials values, if conflict.
	 * @return a credentialsMap whose key is Arrays.asList(serviceName, planName), and
	 * 			value is the credentials of corresponding plan.
	 */
	@Bean
	public CredentialsRepository credentialsMap(){
		ParserProperties parserProperties = useApplicationProperties ? parserApplicationProperties : new ParserSystemEnvironment(new Environment());
		CredentialsRepository idCredentialsRepository = parserProperties.parseCredentialsProperties();
		parserProperties.checkAllServicesHaveCredentialDefinition(idCredentialsRepository);
		CredentialsRepository nameCredentialsRepository = new CredentialsRepository();
		// credentials for all plans of the service
		for (Entry<ServicePlan,Credentials> entry : idCredentialsRepository.findAll()) {
			ServicePlan service_plan_id = entry.getKey();
			if (service_plan_id.getPlanId() == null) {
				String service_id = service_plan_id.getServiceId();
				String service_name = parserProperties.getServiceName(service_id);
				for (String plan_name : parserProperties.parsePlansProperties(service_id).getNames()) {
					nameCredentialsRepository.save(new ServicePlanBuilder().withServiceID(service_name).withPlanID(plan_name).build(), entry.getValue());
				}
			}
		}
		// credentials for specific plans
		for (Entry<ServicePlan,Credentials> entry : idCredentialsRepository.findAll()) {
			ServicePlan service_plan_id = entry.getKey();
			if (service_plan_id.getPlanId() != null) {
				String service_id = service_plan_id.getServiceId();
				String service_name = parserProperties.getServiceName(service_id);
				String plan_id = service_plan_id.getPlanId();
				String plan_name = parserProperties.getPlanName(service_id, plan_id);
				nameCredentialsRepository.save(new ServicePlanBuilder().withServiceID(service_name).withPlanID(plan_name).build(), entry.getValue());
			}
		}
		return nameCredentialsRepository;
	}
}
