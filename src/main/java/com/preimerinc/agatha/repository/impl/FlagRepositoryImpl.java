package com.preimerinc.agatha.repository.impl;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Flag;
import ca.uhn.fhir.model.dstu2.resource.Subscription;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import com.preimerinc.agatha.repository.FlagRepository;
import com.preimerinc.agatha.util.FhirServersEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlagRepositoryImpl implements FlagRepository {

	FhirContext ctx;

	@Autowired
	public FlagRepositoryImpl(FhirContext ctx) {
		this.ctx = ctx;
	}

	public MethodOutcome createFlag(Flag f) {
		IGenericClient client = ctx.newRestfulGenericClient(System.getProperty("serverBase"));

		return client.create()
				.resource(f)
				.prettyPrint()
				.encodedJson()
				.execute();

	}

}
