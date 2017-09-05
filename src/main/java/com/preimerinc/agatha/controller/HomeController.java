package com.preimerinc.agatha.controller;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.client.IGenericClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping(value="/agatha")
public class HomeController {

	@RequestMapping("")
	public String agathaHome() {
		FhirContext ctx = FhirContext.forDstu2();
		String serverBase = "http://fhirtest.uhn.ca/baseDstu2";

		IGenericClient client = ctx.newRestfulGenericClient(serverBase);

		Patient patient = client.read()
				.resource(Patient.class)
				.withId("15355")
				.execute();

		System.out.println("Found patient: " + patient.getName().toString());
		return "Found patient on UHN server" + patient.getName();
	}

}