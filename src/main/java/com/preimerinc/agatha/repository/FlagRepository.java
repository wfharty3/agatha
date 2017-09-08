package com.preimerinc.agatha.repository;

import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Flag;
import ca.uhn.fhir.rest.api.MethodOutcome;
import com.preimerinc.agatha.model.AgathaFlag;
import java.util.List;

public interface FlagRepository {
	public MethodOutcome createFlagRemote(Flag f);
	public void createFlagLocal(Flag f);
	public Bundle listFlagsLocal();
}
