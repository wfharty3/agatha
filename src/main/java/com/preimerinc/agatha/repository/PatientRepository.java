package com.preimerinc.agatha.repository;


import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import org.springframework.stereotype.Component;

@Component
public interface PatientRepository {

	public Patient readBean(String id);
	public Patient readPatient(String id);
	public Bundle searchPatients();

}
