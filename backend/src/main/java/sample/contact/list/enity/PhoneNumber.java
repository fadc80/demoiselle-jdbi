package sample.contact.list.enity;

public class PhoneNumber {
	
	private Long contactId;
	
	private String it;
	
	public PhoneNumber() {
	}

	public PhoneNumber(String it) {
		this(null, it);
	}	
	
	public PhoneNumber(Long contactId, String it) {
		this.contactId = contactId;
		this.it = it;
	}
	
	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public String getIt() {
		return it;
	}

	public void setIt(String it) {
		this.it = it;
	}

}
