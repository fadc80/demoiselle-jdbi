package coding.addicts.simple.list.business.test;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import coding.addicts.simple.contact.list.business.ContactListBC;
import coding.addicts.simple.list.enity.Contact;
import coding.addicts.simple.list.enity.PhoneNumber;
import coding.addicts.simple.list.persistence.ContactDAO;
import coding.addicts.simple.list.persistence.PhoneNumberDAO;

public class ContactListBCTest {

	@Test
	public void addContactWithoutPhoneNumbers() {

		Contact contact = new Contact("Jorge", "Bush");

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
	public void addContactWithPhoneNumbers() {

		Contact contact = new Contact("Bill", "Clinton", Arrays.asList(
				new PhoneNumber("86568413"),
				new PhoneNumber("97054832"),
				new PhoneNumber("30341384")));

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
	
	@Test
	public void findAllReturnZeroContacts() {

		ContactDAO contactDAO = mock(ContactDAO.class);

		when(contactDAO.findAll()).thenReturn(new ArrayList<Contact>());
		
		ContactListBC contactListBC = new ContactListBC(contactDAO, null);
		
		assertTrue(contactListBC.findAll().isEmpty());
		
	}

	@Test
	public void findAllReturnThreeContacts() {

		ContactDAO contactDAO = mock(ContactDAO.class);
		PhoneNumberDAO phoneNumberDAO = mock(PhoneNumberDAO.class);
				
		when(contactDAO.findAll()).thenReturn(Arrays.asList(
				new Contact(1L, "Barack", "Obama"),
				new Contact(2L, "Donald", "Thrump"),
				new Contact(3L, "Hillary", "Clinton")));
		
		when(phoneNumberDAO.findById(1L)).thenReturn(Arrays.asList(
				new PhoneNumber(1L, "46284658"), 
				new PhoneNumber(1L, "15374064"),
				new PhoneNumber(1L, "84625394")));

		when(phoneNumberDAO.findById(2L)).thenReturn(Arrays.asList(
				new PhoneNumber(2L, "31693947"), 
				new PhoneNumber(2L, "97455248"),
				new PhoneNumber(2L, "67453283")));
		

		when(phoneNumberDAO.findById(3L)).thenReturn(Arrays.asList(
				new PhoneNumber(3L, "03827493"), 
				new PhoneNumber(3L, "38485025"),
				new PhoneNumber(3L, "28487632")));	
		
		ContactListBC contactListBC = new ContactListBC(contactDAO, phoneNumberDAO); 
		
		List<Contact> allContacts = contactListBC.findAll();
		
		assertEquals(allContacts.size(), 3);
		
		assertEquals(allContacts.get(0).getFirstName(), "Barack");
	    assertEquals(allContacts.get(0).getLastName(), "Obama");
		assertEquals(allContacts.get(0).getPhoneNumberList().size(), 3);
	    assertEquals(allContacts.get(0).getPhoneNumberList().get(0).getIt(), "46284658");
	    assertEquals(allContacts.get(0).getPhoneNumberList().get(1).getIt(), "15374064");
	    assertEquals(allContacts.get(0).getPhoneNumberList().get(2).getIt(), "84625394");
	    
		assertEquals(allContacts.get(1).getFirstName(), "Donald");
	    assertEquals(allContacts.get(1).getLastName(), "Thrump");
		assertEquals(allContacts.get(1).getPhoneNumberList().size(), 3);
	    assertEquals(allContacts.get(1).getPhoneNumberList().get(0).getIt(), "31693947");
	    assertEquals(allContacts.get(1).getPhoneNumberList().get(1).getIt(), "97455248");
	    assertEquals(allContacts.get(1).getPhoneNumberList().get(2).getIt(), "67453283");
		
		assertEquals(allContacts.get(2).getFirstName(), "Hillary");
	    assertEquals(allContacts.get(2).getLastName(), "Clinton");
		assertEquals(allContacts.get(2).getPhoneNumberList().size(), 3);
	    assertEquals(allContacts.get(2).getPhoneNumberList().get(0).getIt(), "03827493");
	    assertEquals(allContacts.get(2).getPhoneNumberList().get(1).getIt(), "38485025");
	    assertEquals(allContacts.get(2).getPhoneNumberList().get(2).getIt(), "28487632");
		
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
