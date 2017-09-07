package com.preimerinc.agatha.rule;

import ca.uhn.fhir.model.base.composite.BaseQuantityDt;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.NarrativeDt;
import ca.uhn.fhir.model.dstu2.resource.Flag;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.valueset.FlagStatusEnum;
import ca.uhn.fhir.model.dstu2.valueset.NarrativeStatusEnum;
import ca.uhn.fhir.model.primitive.XhtmlDt;
import com.preimerinc.agatha.util.FhirSystemEnum;
import com.preimerinc.agatha.util.HapiUtils;
import java.util.List;
import java.util.ArrayList;
import org.easyrules.core.BasicRule;

public class HypokalemiaRule extends BasicRule implements EnhancedRule {

	Observation data;

	List<Flag> flags = new ArrayList<Flag>();

	public HypokalemiaRule(Observation data) {
		this.data = data;
	}

	@Override
	public boolean evaluate() {
		boolean result = false;

		BaseQuantityDt v;

		if (HapiUtils.codedAs(data.getCode(), FhirSystemEnum.LOINC.getUrl(), "2823-3")) {
			if (data.getStatus().equalsIgnoreCase("final")) {
				v = (BaseQuantityDt)data.getValue();
				if (v.getValueElement().getValue().floatValue() < 2.8 && v.getUnitsElement().getValue().equalsIgnoreCase("mmol/L")) {
					result = true;
				}
			}
		}
		return result;
	}

	@Override
	public void execute() throws Exception {
		Flag f = new Flag();
		f.setCategory(new CodeableConceptDt("http://hl7.org/fhir/flag-category", "lab"));
		f.setStatus(FlagStatusEnum.ACTIVE);
		f.setText(new NarrativeDt(new XhtmlDt(
				"Super low K.  The patient doesnt eat enough bananas!"),
				NarrativeStatusEnum.ADDITIONAL));
		flags.add(f);
	}

	public String getName() {
		return "Hypo K";
	}

	public String getDescription() {
		return "Yes we have no bananas";
	}

	public List<Flag> getFlags() {
		return flags;
	}

	public void clearFlags() {
		flags.clear();
	}
}
