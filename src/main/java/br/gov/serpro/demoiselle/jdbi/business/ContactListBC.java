package br.gov.serpro.demoiselle.jdbi.business;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.demoiselle.jdbi.enity.Contact;
import br.gov.serpro.demoiselle.jdbi.enity.PhoneNumber;
import br.gov.serpro.demoiselle.jdbi.persistence.PersonDAO;
import br.gov.serpro.demoiselle.jdbi.persistence.PhoneNumberDAO;

@BusinessController
public class ContactListBC {
	
	@Inject
	private PersonDAO personDAO;
	
	@Inject
	private PhoneNumberDAO phoneNumberDAO;

	@Transactional
	public Long add(Contact contact) {
		personDAO.insert(contact);
		add(contact.getId(), contact.getPhoneNumberList());
		return contact.getId();
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
		phoneNumber.setContactId(contactId);
		phoneNumberDAO.insert(phoneNumber);
	}
}
