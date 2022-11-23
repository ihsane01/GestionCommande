 package ma.fstt.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.fstt.entities.Commande;
import ma.fstt.entities.Commande;
import ma.fstt.service.CommandeRepository;

public class CommandeDAO  implements CommandeRepository{

	 private String jdbcURL;
	    private String jdbcUsername;
	    private String jdbcPassword;
	    private Connection jdbcConnection;
		public CommandeDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	 public boolean insertCommande(Commande commande) throws SQLException {

	        String sql = "INSERT INTO commande (date, idclient) VALUES (?, ?)";
	        connect();
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	        statement.setString(1, commande.getDate());
	        statement.setInt(2, commande.getIdclient());
	       

	        boolean rowInserted = statement.executeUpdate() > 0;
	        statement.close();
	       disconnect();
	        return rowInserted;
	    }
	
	@Override
	 public List<Commande> listAllCommandes() throws SQLException {
		
		 List<Commande> listBook = new ArrayList<>();
		 connect();
	        String sql = "SELECT * FROM commande";
	         
	      
	         
	        Statement statement = jdbcConnection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	         
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String date = resultSet.getString("date");
	            int idclient = resultSet.getInt("idclient");
	     

	            Commande commande = new Commande(id, date, idclient);
	            listBook.add(commande);
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        disconnect();
	         
	        return listBook;
	    }
	@Override
	public Commande getcmdfin()throws SQLException {
	     Commande commande = null;

		 connect();
	        String sql = "SELECT * FROM commande ORDER BY id DESC LIMIT 1 ";
	         
	      
	         
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	      
	         
	        ResultSet resultSet = statement.executeQuery();
	         
	        if (resultSet.next()) {
	            int id = resultSet.getInt("id");

	            String date = resultSet.getString("date");
	            int idclient = resultSet.getInt("idclient");
	     

	            commande = new Commande(id, date, idclient);}
	        resultSet.close();
	        statement.close();
	         
	        disconnect();
	         
	        return commande;	
	}
	public boolean deleteCommande(Commande commande) throws SQLException {
		
		String sql = "DELETE FROM commande where id = ?";
		  connect();
    
     PreparedStatement statement = jdbcConnection.prepareStatement(sql);
     statement.setInt(1, commande.getId());
      
     boolean rowDeleted = statement.executeUpdate() > 0;
     statement.close();
     disconnect();
     return rowDeleted;     
 }
  
 public boolean updateCommande(Commande commande) throws SQLException {
 	
     String sql = "UPDATE commande SET date = ?, idclient = ?";
     sql += " WHERE id = ?";
     connect();
     
     PreparedStatement statement = jdbcConnection.prepareStatement(sql);
     statement.setString(1, commande.getDate());
     statement.setInt(2, commande.getIdclient());
   
     statement.setInt(3, commande.getId());

     boolean rowUpdated = statement.executeUpdate() > 0;
     statement.close();
     disconnect();
     return rowUpdated;     
 }
 
	@Override
 public Commande getCommande(int id) throws SQLException {
     Commande commande = null;
     String sql = "SELECT * FROM commande WHERE id = ?";
      
     connect();
      
     PreparedStatement statement = jdbcConnection.prepareStatement(sql);
     statement.setInt(1, id);
      
     ResultSet resultSet = statement.executeQuery();
      
     if (resultSet.next()) {
         String date = resultSet.getString("date");
         int idclient = resultSet.getInt("idclient");
  

         commande = new Commande(id, date, idclient);
     }
      
     resultSet.close();
     statement.close();
      
     return commande;
 }

}
