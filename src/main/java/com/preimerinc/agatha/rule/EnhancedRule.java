package com.preimerinc.agatha.rule;

import ca.uhn.fhir.model.dstu2.resource.Flag;
import java.util.List;
import org.easyrules.api.Rule;
import org.easyrules.core.BasicRule;

public interface EnhancedRule {

	public List<Flag> getFlags();
	public void clearFlags();

}
