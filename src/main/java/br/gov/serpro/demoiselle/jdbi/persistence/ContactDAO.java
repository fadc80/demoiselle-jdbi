package br.gov.serpro.demoiselle.jdbi.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.serpro.demoiselle.jdbi.enity.Contact;

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

}
