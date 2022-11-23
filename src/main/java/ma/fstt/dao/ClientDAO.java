package ma.fstt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.fstt.entities.Client;
import ma.fstt.service.ClientRepository;

public class ClientDAO implements ClientRepository {
	 private String jdbcURL;
	    private String jdbcUsername;
	    private String jdbcPassword;
	    private Connection jdbcConnection;
		public ClientDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
			super();
			this.jdbcURL = jdbcURL;
			this.jdbcUsername = jdbcUsername;
			this.jdbcPassword = jdbcPassword;
		}
	     
		protected Connection connect() throws SQLException {
	        if (jdbcConnection == null || jdbcConnection.isClosed()) {
	            try {
	                Class.forName("com.mysql.jdbc.Driver");
	            } catch (ClassNotFoundException e) {
	                throw new SQLException(e);
	            }
	            jdbcConnection = DriverManager.getConnection(
	                                        jdbcURL, jdbcUsername, jdbcPassword);
	        }
	        return jdbcConnection;
	    }
		 protected void disconnect() throws SQLException {
		        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
		            jdbcConnection.close();
		        }
		    }
	
	
	@Override
	 public boolean insertClient(Client client) throws SQLException {

	        String sql = "INSERT INTO client (nom, prenom, adress,tele) VALUES (?, ?, ?,?)";
	        connect();
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	        statement.setString(1, client.getNom());
	        statement.setString(2, client.getPrenom());
	        statement.setString(3, client.getAdress());
	        statement.setString(4, client.getTele());

	        boolean rowInserted = statement.executeUpdate() > 0;
	        statement.close();
	       disconnect();
	        return rowInserted;
	    }
	
	@Override
	 public List<Client> listAllClients() throws SQLException {
		
		 List<Client> listBook = new ArrayList<>();
		 connect();
	        String sql = "SELECT * FROM client";
	         
	      
	         
	        Statement statement = jdbcConnection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	         
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String nom = resultSet.getString("nom");
	            String prenom = resultSet.getString("prenom");
	            String adress = resultSet.getString("adress");
	            String tel = resultSet.getString("tele");

	            Client client = new Client(id, nom, prenom, adress,tel);
	            listBook.add(client);
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        disconnect();
	         
	        return listBook;
	    }
	@Override
	public boolean deleteClient(Client client) throws SQLException {
		
		String sql = "DELETE FROM client where id = ?";
		  connect();
       
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, client.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
	@Override
    public boolean updateClient(Client client) throws SQLException {
    	
        String sql = "UPDATE client SET nom = ?, prenom = ?, adress = ?,tele= ?";
        sql += " WHERE id = ?";
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, client.getNom());
        statement.setString(2, client.getPrenom());
        statement.setString(3,client.getAdress());
        statement.setString(4, client.getTele());
        statement.setInt(5, client.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
    
	@Override
    public Client getClient(int id) throws SQLException {
        Client client = null;
        String sql = "SELECT * FROM client WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            String adress = resultSet.getString("adress");
            String tele = resultSet.getString("tele");

            client = new Client(id, nom, prenom, adress,tele);
        }
         
        resultSet.close();
        statement.close();
         
        return client;
    }



	
	
}
