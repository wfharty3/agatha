package com.preimerinc.agatha.repository.impl;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.client.IGenericClient;
import com.preimerinc.agatha.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientRepositoryImpl implements PatientRepository {

	//@Autowired
	FhirContext ctx;

	@Autowired
	public PatientRepositoryImpl(FhirContext ctx) {
		this.ctx = ctx;
	}

	public Patient readBean(String id) {

		String serverBase = "http://fhirtest.uhn.ca/baseDstu2";

		IGenericClient client = ctx.newRestfulGenericClient(serverBase);

				Patient patient = client.read()
				.resource(Patient.class)
				.withId(id)
				.execute();

		System.out.println("Found patient: " + patient.getName().toString());
		return patient;
	}

	public Patient readPatient(String id) {
		FhirContext ctx = FhirContext.forDstu2();
		String serverBase = "http://fhirtest.uhn.ca/baseDstu2";

		IGenericClient client = ctx.newRestfulGenericClient(serverBase);

		Patient patient = client.read()
				.resource(Patient.class)
				.withId(id)
				.execute();

		System.out.println("Found patient: " + patient.getName().toString());
		return patient;

	}


	public Bundle searchPatients() {
		Bundle b = new Bundle();
		return b;

	}
}
