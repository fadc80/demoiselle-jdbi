package sample.contact.list.enity;

import java.util.List;

public class Contact {
	
	private Long id;

	private String firstName;	
	
	private String lastName;

	private List<PhoneNumber> phoneNumberList;
	
	public Contact() {
	}

	public Contact(String firstName, String lastName) {
		this(null, firstName, lastName);
	}	
	
	public Contact(Long id, String firstName, String lastName) {
		this(id, firstName, lastName, null);
	}
	
	public Contact(String firstName, String lastName, List<PhoneNumber> phoneNumberList) {
		this(null, firstName, lastName, phoneNumberList);
	}	
	
	public Contact(Long id, String firstName, String lastName, List<PhoneNumber> phoneNumberList) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumberList = phoneNumberList;
	}	
	
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
