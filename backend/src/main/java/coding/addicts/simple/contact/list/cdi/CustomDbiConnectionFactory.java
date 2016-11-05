package coding.addicts.simple.contact.list.cdi;

import java.sql.Connection;
import java.sql.SQLException;

import org.skife.jdbi.v2.tweak.ConnectionFactory;

import br.gov.frameworkdemoiselle.util.Beans;

public class CustomDbiConnectionFactory implements ConnectionFactory{

	public Connection openConnection() throws SQLException {
		return Beans.getReference(Connection.class);
	}
	
}
