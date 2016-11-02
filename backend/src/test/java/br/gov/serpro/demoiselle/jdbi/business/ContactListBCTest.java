package br.gov.serpro.demoiselle.jdbi.business;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import br.gov.serpro.demoiselle.jdbi.enity.Contact;
import br.gov.serpro.demoiselle.jdbi.enity.PhoneNumber;
import br.gov.serpro.demoiselle.jdbi.persistence.ContactDAO;
import br.gov.serpro.demoiselle.jdbi.persistence.PhoneNumberDAO;

public class ContactListBCTest {

	@Test
	public void addContactWithoutPhoneNumbers() {

		Contact contact = new Contact();
		contact.setFirstName("Jorge");
		contact.setLastName("Bush");

		ContactDAO contactDAO = mock(ContactDAO.class);

		when(contactDAO.insert(any(Contact.class))).thenAnswer(createContactDAOInsertAnwser());

		ContactListBC contactListBC = new ContactListBC(contactDAO, null);
		contactListBC.add(contact);

		assertEquals(contact.getId(), new Long(1L));
		assertEquals(contact.getFirstName(), "Jorge");
		assertEquals(contact.getLastName(), "Bush");
		assertNull(contact.getPhoneNumberList());

	}

	@Test
	public void addContactPhoneNumbers() {

		Contact contact = new Contact();
		contact.setFirstName("Bill");
		contact.setLastName("Clinton");

		ArrayList<PhoneNumber> phoneNumberList = new ArrayList<PhoneNumber>();

		PhoneNumber phoneNumber1, phoneNumber2, phoneNumber3;

		phoneNumber1 = new PhoneNumber();
		phoneNumber1.setIt("86568413");

		phoneNumber2 = new PhoneNumber();
		phoneNumber2.setIt("97054832");

		phoneNumber3 = new PhoneNumber();
		phoneNumber3.setIt("30341384");

		phoneNumberList.add(phoneNumber1);
		phoneNumberList.add(phoneNumber2);
		phoneNumberList.add(phoneNumber3);

		contact.setPhoneNumberList(phoneNumberList);

		ContactDAO contactDAO = mock(ContactDAO.class);
		PhoneNumberDAO phoneNumberDAO = mock(PhoneNumberDAO.class);

		when(contactDAO.insert(any(Contact.class)))
				.thenAnswer(createContactDAOInsertAnwser());

		when(phoneNumberDAO.insert(any(Long.class), any(PhoneNumber.class)))
				.thenAnswer(createPhoneNumberDAOInsertAnswer());

		ContactListBC contactListBC = new ContactListBC(contactDAO, phoneNumberDAO);
		contactListBC.add(contact);

		assertEquals(contact.getId(), new Long(1L));
		assertEquals(contact.getFirstName(), "Bill");
		assertEquals(contact.getLastName(), "Clinton");

		assertEquals(contact.getPhoneNumberList().get(0).getContactId(), new Long(1));
		assertEquals(contact.getPhoneNumberList().get(1).getContactId(), new Long(1));
		assertEquals(contact.getPhoneNumberList().get(2).getContactId(), new Long(1));

	}

	private Answer<PhoneNumber> createPhoneNumberDAOInsertAnswer() {
		return new Answer<PhoneNumber>() {
			public PhoneNumber answer(InvocationOnMock invocation) throws Throwable {
				Long contactId = invocation.getArgumentAt(0, Long.class);
				PhoneNumber phoneNumber = invocation.getArgumentAt(1, PhoneNumber.class);
				phoneNumber.setContactId(contactId);
				return phoneNumber;
			}
		};
	}

	private Answer<Contact> createContactDAOInsertAnwser() {
		return new Answer<Contact>() {
			public Contact answer(InvocationOnMock invocation) throws Throwable {
				Contact contact = invocation.getArgumentAt(0, Contact.class);
				contact.setId(1L);
				return contact;
			}
		};
	}

}
