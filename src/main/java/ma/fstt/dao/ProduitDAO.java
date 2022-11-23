package ma.fstt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.fstt.controller.connexion;
import ma.fstt.entities.Produit;
import ma.fstt.entities.Produit;
import ma.fstt.service.ProduitRepository;

public class ProduitDAO implements ProduitRepository {

	 private String jdbcURL;
	    private String jdbcUsername;
	    private String jdbcPassword;
	    private Connection jdbcConnection;
		public ProduitDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	 public boolean insertProduit(Produit produit) throws SQLException {

	        String sql = "INSERT INTO produit (nom, qtstock, prix) VALUES (?, ?, ?)";
	        connect();
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	        statement.setString(1, produit.getNom());
	        statement.setInt(2, produit.getQtstock());
	        statement.setInt(3, produit.getPrix());

	        boolean rowInserted = statement.executeUpdate() > 0;
	        statement.close();
	       disconnect();
	        return rowInserted;
	    }
	
	@Override
	 public List<Produit> listAllProduits() throws SQLException {
		
		 List<Produit> listBook = new ArrayList<>();
		 connect();
	        String sql = "SELECT * FROM produit";
	         
	      
	         
	        Statement statement = jdbcConnection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	         
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String nom = resultSet.getString("nom");
	            int qtstock = resultSet.getInt("qtstock");
	            int prix = resultSet.getInt("prix");

	            Produit produit = new Produit(id, nom, qtstock, prix);
	            listBook.add(produit);
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        disconnect();
	         
	        return listBook;
	    }
	@Override
	public boolean deleteProduit(Produit produit) throws SQLException {
		
		String sql = "DELETE FROM produit where id = ?";
		  connect();
    
     PreparedStatement statement = jdbcConnection.prepareStatement(sql);
     statement.setInt(1, produit.getId());
      
     boolean rowDeleted = statement.executeUpdate() > 0;
     statement.close();
     disconnect();
     return rowDeleted;     
 }
	@Override
 public boolean updateProduit(Produit produit) throws SQLException {
 	
     String sql = "UPDATE produit SET nom = ?, prenom = ?, adress = ?,tele= ?";
     sql += " WHERE id = ?";
     connect();
     
     PreparedStatement statement = jdbcConnection.prepareStatement(sql);
     statement.setString(1, produit.getNom());
     statement.setInt(2, produit.getQtstock());
     statement.setInt(3, produit.getPrix());
     statement.setInt(5, produit.getId());

     boolean rowUpdated = statement.executeUpdate() > 0;
     statement.close();
     disconnect();
     return rowUpdated;     
 }
 
	@Override
 public Produit getProduit(int id) throws SQLException {
     Produit produit = null;
     String sql = "SELECT * FROM produit WHERE id = ?";
      
     connect();
      
     PreparedStatement statement = jdbcConnection.prepareStatement(sql);
     statement.setInt(1, id);
      
     ResultSet resultSet = statement.executeQuery();
      
     if (resultSet.next()) {
         String nom = resultSet.getString("nom");
         int qtstock = resultSet.getInt("qtstock");
         int prix = resultSet.getInt("prix");

         produit = new Produit(id, nom, qtstock, prix);
     }
      
     resultSet.close();
     statement.close();
      
     return produit;
 }
}
