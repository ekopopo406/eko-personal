package com.ibm.model;

import java.util.List;

public class ProjectNode {
	private List<String> section;
	private String formName;
	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public List<String> getSection() {
		return section;
	}

	public void setSection(List<String> section) {
		this.section = section;
	}
 
}