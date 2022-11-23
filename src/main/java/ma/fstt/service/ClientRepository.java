package ma.fstt.service;

import java.sql.SQLException;
import java.util.List;

import ma.fstt.entities.Client;


public interface ClientRepository {
	boolean insertClient(Client client) throws SQLException;
	 List<Client> listAllClients() throws SQLException;
	 Client getClient(int id) throws SQLException;
	 boolean updateClient(Client client) throws SQLException;
	 boolean deleteClient(Client client) throws SQLException;
	 
	 
}
