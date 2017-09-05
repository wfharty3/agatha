package com.preimerinc.agatha.util;

import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;

public class HapiUtils {

	public static boolean codedAs(CodeableConceptDt codedConcept, String system, String code) {
		boolean result = false;

		for (CodingDt coding: codedConcept.getCoding()) {
			if (coding.getSystem().equalsIgnoreCase(system) && coding.getCode().equalsIgnoreCase(code)) {
				result = true;
			}
		}
		return result;
	}
}
