package br.gov.serpro.demoiselle.jdbi.business;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.demoiselle.jdbi.enity.Contact;
import br.gov.serpro.demoiselle.jdbi.enity.PhoneNumber;
import br.gov.serpro.demoiselle.jdbi.persistence.ContactDAO;
import br.gov.serpro.demoiselle.jdbi.persistence.PhoneNumberDAO;

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
}
