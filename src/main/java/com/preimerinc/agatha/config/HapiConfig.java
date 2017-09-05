package com.preimerinc.agatha.config;

import ca.uhn.fhir.context.FhirContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HapiConfig {

	@Bean
	public FhirContext fhirContext() {
		FhirContext ctx = FhirContext.forDstu2();
		return ctx;
	}


}
