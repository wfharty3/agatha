package com.preimerinc.agatha.repository;

import ca.uhn.fhir.model.dstu2.resource.Flag;
import ca.uhn.fhir.rest.api.MethodOutcome;

public interface FlagRepository {
	public MethodOutcome createFlag(Flag f);

}
