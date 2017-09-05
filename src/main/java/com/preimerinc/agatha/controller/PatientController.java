package com.preimerinc.agatha.controller;

import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.client.IGenericClient;
import com.preimerinc.agatha.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agatha/data/patient")
public class PatientController {


	@Autowired
	PatientRepository ptRepo;

	@RequestMapping("/{id}")
	public String payloadHome(@PathVariable("id") String id) {


		Patient patient = ptRepo.readPatient(id);

		System.out.println("Found patient: " + patient.getName().toString());
		return "Found patient on UHN server" + patient.getName();

	}

	@RequestMapping("/bean/{id}")
	public String payloadBean(@PathVariable("id") String id) {


		Patient patient = ptRepo.readBean(id);

		System.out.println("Found patient via Bean: " + patient.getName().toString());
		return "Found patient on UHN server" + patient.getName();

	}

}