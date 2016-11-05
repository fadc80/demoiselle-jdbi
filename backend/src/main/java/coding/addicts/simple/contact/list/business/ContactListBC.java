package coding.addicts.simple.contact.list.business;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import coding.addicts.simple.contact.list.enity.Contact;
import coding.addicts.simple.contact.list.enity.PhoneNumber;
import coding.addicts.simple.contact.list.persistence.ContactDAO;
import coding.addicts.simple.contact.list.persistence.PhoneNumberDAO;

@BusinessController
public class ContactListBC {
	
	@Inject
	private ContactDAO contactDAO;
	
	@Inject
	private PhoneNumberDAO phoneNumberDAO;

	public ContactListBC() {
	}
	
	public ContactListBC(ContactDAO contactDAO, PhoneNumberDAO phoneNumberDAO) {
		this.contactDAO = contactDAO;
		this.phoneNumberDAO = phoneNumberDAO;
	}	
	
	@Transactional
	public Contact add(Contact contact) {
		contactDAO.insert(contact);
		add(contact.getId(), contact.getPhoneNumberList());
		return contact;
	}

	@Transactional
	public void add(Long contactId, List<PhoneNumber> phoneNumberList) {
		if (phoneNumberList != null) { 
			for (PhoneNumber phoneNumber: phoneNumberList) {
				add(contactId, phoneNumber);			
			}
		}
	}

	@Transactional
	public void add(Long contactId, PhoneNumber phoneNumber) {
		phoneNumberDAO.insert(contactId, phoneNumber);
	}
	
	public List<Contact> findAll() {
		List<Contact> allContacts = contactDAO.findAll();
		for (Contact contact: allContacts) {
			contact.setPhoneNumberList(phoneNumberDAO.findById(contact.getId()));
		}
		return allContacts;
	}
}
