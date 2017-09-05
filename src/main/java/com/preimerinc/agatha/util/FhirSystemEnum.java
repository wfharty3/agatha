package com.preimerinc.agatha.util;

public enum FhirSystemEnum {
	LOINC("http://loinc.org"),
	SNOMED(" http://snomed.info/sct"),
	LOCALHOST("http://localhost");

	private String url;

	private FhirSystemEnum(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
