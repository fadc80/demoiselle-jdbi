package br.gov.serpro.demoiselle.jdbi.enity;

import java.util.List;

public class Contact {
	
	private Long id;

	private String firstName;	
	
	private String lastName;

	private List<PhoneNumber> phoneNumberList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<PhoneNumber> getPhoneNumberList() {
		return phoneNumberList;
	}

	public void setPhoneNumberList(List<PhoneNumber> phoneNumberList) {
		this.phoneNumberList = phoneNumberList;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
