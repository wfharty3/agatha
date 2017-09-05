package com.preimerinc.agatha.repository.impl;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.client.IGenericClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseRepositoryImpl {


	@Autowired
	FhirContext ctx;
	String serverBase = "http://fhirtest.uhn.ca/baseDstu2";
	IGenericClient client;

	public BaseRepositoryImpl() {
	}
}
