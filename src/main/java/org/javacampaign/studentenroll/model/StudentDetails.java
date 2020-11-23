package org.javacampaign.studentenroll.model;

import com.google.gson.annotations.SerializedName;

public class StudentDetails {
	
	private String name;
	
	private int age;
	
	@SerializedName(value = "proof_of_birth")
	private String proofOfBirth;
	
	private String gender;
	
	@SerializedName(value = "emergency_contact")
	private long emergencyContact;
	
	@SerializedName(value = "image_data")
	private String imageData;
	
	public StudentDetails() {
	}

	public StudentDetails(String name, int age, String proofOfBirth, String gender, int emergencyContact, String imageData) {
		super();
		this.name = name;
		this.age = age;
		this.proofOfBirth = proofOfBirth;
		this.gender = gender;
		this.emergencyContact = emergencyContact;
		this.imageData = imageData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getProofOfBirth() {
		return proofOfBirth;
	}

	public void setProofOfBirth(String proofOfBirth) {
		this.proofOfBirth = proofOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(long emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}
	
}