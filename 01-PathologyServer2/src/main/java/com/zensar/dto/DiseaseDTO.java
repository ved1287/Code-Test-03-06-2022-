package com.zensar.dto;

public class DiseaseDTO {
	private String Id;
	private String description;
	private String treatment;
	
	public DiseaseDTO() {
		
	}

	public DiseaseDTO(String id, String description, String treatment) {
		super();
		Id = id;
		this.description = description;
		this.treatment = treatment;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	@Override
	public String toString() {
		return "Disease [Id=" + Id + ", description=" + description + ", treatment=" + treatment + "]";
	}
	

}
