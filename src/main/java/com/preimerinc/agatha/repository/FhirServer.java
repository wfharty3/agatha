package com.preimerinc.agatha.repository;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.IGenericClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class FhirServer {


	String serverBase = "http://fhirtest.uhn.ca/baseDstu2";
	IGenericClient client;
	FhirContext ctx;


//	@Autowired
	public FhirServer(FhirContext ctx) {
		this.ctx = ctx;
		this.client = ctx.newRestfulGenericClient(serverBase);
	}

	public IGenericClient getClient() {
		return this.client;
	}

	public String getServerBase() {

		return this.serverBase;
	}

	public FhirContext getContext() {
		return ctx;
	}

}
