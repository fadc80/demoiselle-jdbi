package br.gov.serpro.demoiselle.jdbi.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.serpro.demoiselle.jdbi.enity.PhoneNumber;

@PersistenceController
public class PhoneNumberDAO extends BaseDAO{

	public void insert(PhoneNumber phoneNumber) {
		getDbi().open().createStatement(
				"insert into phone_number(contact_id, it) values(:contact_id, :it)")
				.bind("contact_id", phoneNumber.getContactId())
				.bind("it", phoneNumber.getIt())
				.execute();
	}
	
}
