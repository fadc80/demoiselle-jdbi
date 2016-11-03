package sample.contact.list.cdi;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.skife.jdbi.v2.DBI;

public class DbiProducer {
	
	@Inject
	private CustomDbiConnectionFactory connectionFactory;
	
	@Produces
	public DBI create() {
		return new DBI(connectionFactory);
	}

}
