package com.preimerinc.agatha.repository.impl;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Subscription;
import ca.uhn.fhir.rest.client.IGenericClient;
import com.preimerinc.agatha.repository.SubscriptionRepository;
import com.preimerinc.agatha.util.FhirServersEnum;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

	public Bundle listAll() {
		FhirContext ctx = FhirContext.forDstu2();

		IGenericClient client = ctx.newRestfulGenericClient(FhirServersEnum.UHN_DSTU2.getUrl());
		//IGenericClient client = ctx.newRestfulGenericClient(FhirServersEnum.FURORE_DSTU2.getUrl());

		// Perform a search
		Bundle results = client
				.search()
				.forResource(Subscription.class)
				.returnBundle(ca.uhn.fhir.model.dstu2.resource.Bundle.class)
				.execute();

		System.out.println("Found " + results.getEntry().size() + " subscriptions");
		return results;
	}

}
