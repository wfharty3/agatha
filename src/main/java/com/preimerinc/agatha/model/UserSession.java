package com.preimerinc.agatha.model;

import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.BaseResource;
import java.time.Instant;
import org.hl7.fhir.instance.model.api.IBackboneElement;

public class UserSession  {

	private IdentifierDt identifier;
	private ResourceReferenceDt user;
	private IBackboneElement status;
	private CodeableConceptDt workstation;
	private ResourceReferenceDt patient;
	private ResourceReferenceDt encounter;
	private ResourceReferenceDt imagingStudy;
	private Instant recorded;
	private IBackboneElement context;

	public IdentifierDt getIdentifier() {
		return identifier;
	}

	public void setIdentifier(IdentifierDt identifier) {
		this.identifier = identifier;
	}

	public ResourceReferenceDt getUser() {
		return user;
	}

	public void setUser(ResourceReferenceDt user) {
		this.user = user;
	}

	public IBackboneElement getStatus() {
		return status;
	}

	public void setStatus(IBackboneElement status) {
		this.status = status;
	}

	public CodeableConceptDt getWorkstation() {
		return workstation;
	}

	public void setWorkstation(CodeableConceptDt workstation) {
		this.workstation = workstation;
	}

	public ResourceReferenceDt getPatient() {
		return patient;
	}

	public void setPatient(ResourceReferenceDt patient) {
		this.patient = patient;
	}

	public ResourceReferenceDt getEncounter() {
		return encounter;
	}

	public void setEncounter(ResourceReferenceDt encounter) {
		this.encounter = encounter;
	}

	public ResourceReferenceDt getImagingStudy() {
		return imagingStudy;
	}

	public void setImagingStudy(ResourceReferenceDt imagingStudy) {
		this.imagingStudy = imagingStudy;
	}

	public Instant getRecorded() {
		return recorded;
	}

	public void setRecorded(Instant recorded) {
		this.recorded = recorded;
	}

	public IBackboneElement getContext() {
		return context;
	}

	public void setContext(IBackboneElement context) {
		this.context = context;
	}
}
