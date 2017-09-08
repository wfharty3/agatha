package com.preimerinc.agatha.model;

import java.sql.Clob;
import java.sql.Date;

public class AgathaFlag {

	private Integer flagId;
	private String patientIdentifier;
//	private Date flagDate;
	private Clob flagContent;

	public Integer getFlagId() {
		return flagId;
	}

	public void setFlagId(Integer flagId) {
		this.flagId = flagId;
	}

	public String getPatientIdentifier() {
		return patientIdentifier;
	}

	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}

	/*
	public Date getFlagDate() {
		return flagDate;
	}

	public void setFlagDate(Date flagDate) {
		this.flagDate = flagDate;
	}
*/

	public Clob getFlagContent() {
		return flagContent;
	}

	public void setFlagContent(Clob flagContent) {
		this.flagContent = flagContent;
	}
}
