package sample.contact.list.persistence;

import javax.inject.Inject;

import org.skife.jdbi.v2.DBI;

public class BaseDAO {
	
	@Inject
	private DBI dbi;

	public DBI getDbi() {
		return dbi;
	}

}