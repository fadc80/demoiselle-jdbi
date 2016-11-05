package coding.addicts.simple.list.persistence;

import java.util.ArrayList;
import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import coding.addicts.simple.list.enity.PhoneNumber;

@PersistenceController
public class PhoneNumberDAO extends BaseDAO{

	public PhoneNumber insert(Long contactId, PhoneNumber phoneNumber) {
		
		phoneNumber.setContactId(contactId);
		
		getDbi().open().createStatement(
				"insert into phone_number(contact_id, it) values(:contact_id, :it)")
				.bind("contact_id", phoneNumber.getContactId())
				.bind("it", phoneNumber.getIt())
				.execute();
		
		return phoneNumber;
		
	}
	
	public List<PhoneNumber> findById(Long contactId) {
		
		List<PhoneNumber> allPhoneNumbers = new ArrayList<PhoneNumber>();
		
		getDbi().open().createQuery("select * from phone_number where contact_id = :id")
			.bind("id", contactId).list().forEach((item) -> allPhoneNumbers.add(new PhoneNumber(
					(Long) item.get("contact_id"),
					(String) item.get("it"))));
	
		return allPhoneNumbers;
	}
	
}
