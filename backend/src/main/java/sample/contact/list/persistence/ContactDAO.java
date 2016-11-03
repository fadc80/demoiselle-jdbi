package sample.contact.list.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import sample.contact.list.enity.Contact;

@PersistenceController
public class ContactDAO extends BaseDAO {
	
	public Contact insert(Contact contact) {
		
		contact.setId(getDbi().open().createStatement(
				"insert into contact(first_name, last_name) values(:first_name, :last_name)")
				.bind("first_name", contact.getFirstName())
				.bind("last_name", contact.getLastName())
				.executeAndReturnGeneratedKeys(new ContactIdResultSetMapper())
				.first());		
		return contact;
		
	}
	
	private static class ContactIdResultSetMapper implements ResultSetMapper<Long> {
		public Long map(final int i, final ResultSet resultSet, 
				final StatementContext statementContext) throws SQLException {
			return resultSet.getLong(1);
		}
	}
	
	public List<Contact> findAll() {
		
		List<Contact> allContacts = new ArrayList<Contact>();
		
		getDbi().open().select("select * from contact").forEach((item) -> allContacts.add(new Contact(
				(Long) item.get("id"), 
				(String) item.get("first_name"), 
				(String) item.get("last_name"))));
	
		return allContacts;
		
	}

}
