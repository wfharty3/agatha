package com.preimerinc.agatha.util;

public enum FhirServersEnum {
	UHN_STU3("http://fhirtest.uhn.ca/baseDstu3"),
	UHN_DSTU2("http://fhirtest.uhn.ca/baseDstu2"),
	FURORE_DSTU2("http://spark.furore.com/fhir/");

	private String url;

	private FhirServersEnum(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
