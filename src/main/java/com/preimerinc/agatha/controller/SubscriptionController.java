package com.preimerinc.agatha.controller;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import com.preimerinc.agatha.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agatha/subscription")
public class SubscriptionController {

	@Autowired
	SubscriptionRepository scripRepo;

	@Autowired
	FhirContext ctx;

	@RequestMapping("")
	public String subscriptionHome() {
		return ctx.newJsonParser().encodeResourceToString(scripRepo.listAll());
	}

}
