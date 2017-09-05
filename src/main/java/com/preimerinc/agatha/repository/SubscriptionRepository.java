package com.preimerinc.agatha.repository;

import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Subscription;
import java.util.List;

public interface SubscriptionRepository {
	public Bundle listAll();
}
