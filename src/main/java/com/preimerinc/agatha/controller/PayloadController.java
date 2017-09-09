package com.preimerinc.agatha.controller;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Flag;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.client.IGenericClient;
import com.preimerinc.agatha.repository.PatientRepository;
import com.preimerinc.agatha.service.RulesEngineService;
import java.util.List;
import java.util.Set;
import org.easyrules.api.Rule;
import org.easyrules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agatha/payload")
public class PayloadController {

	@Autowired
	RulesEngineService reService;

	@RequestMapping(value="/patient", method = RequestMethod.POST)
	public String payloadPatientResource(@RequestBody String payload) {

		FhirContext ctx = FhirContext.forDstu2();
		Patient p = ctx.newJsonParser().parseResource(Patient.class,payload);

		return "Patient received via POST: " + p.getName().toString();

	}

	@RequestMapping(value="/bundle", method = RequestMethod.POST)
	public String payloadBundle(@RequestBody String payload) {

		FhirContext ctx = FhirContext.forDstu2();
		Bundle b = ctx.newJsonParser().parseBundle(payload);

		return "Found " + b.getEntries().size() + " in bundle";

	}


	@RequestMapping(value="/observation", method = RequestMethod.POST)
	public String runRules(@RequestBody String payload) {

		FhirContext ctx = FhirContext.forDstu2();
		Observation obx = ctx.newJsonParser().parseResource(Observation.class, payload);

		reService.setData(obx);
		List<Flag> flags = reService.runRules();

		Bundle b = new Bundle();
		for (Flag f : flags) {
			b.addEntry().setResource(f);
		}

		// return bundle to web service
		return ctx.newJsonParser().encodeBundleToString(b);



	}

}