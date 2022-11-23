package ma.fstt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.fstt.entities.LigneCmd;
import ma.fstt.entities.LigneCmd;
import ma.fstt.service.LignCmdRepository;

public class LigneCmdDAO implements LignCmdRepository{

	 private String jdbcURL;
	    private String jdbcUsername;
	    private String jdbcPassword;
	    private Connection jdbcConnection;
	    
		public LigneCmdDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	 public boolean insertLigneCmd(LigneCmd ligneCmd) throws SQLException {

	        String sql = "INSERT INTO ligneCmd ( qtcmd, idcmd,idproduit) VALUES (?, ?, ?)";
	        connect();
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	        statement.setInt(1, ligneCmd.getQtecmd());
	        statement.setInt(2, ligneCmd.getIdcmd());
	        statement.setInt(3, ligneCmd.getIdproduit());
	      

	        boolean rowInserted = statement.executeUpdate() > 0;
	        statement.close();
	       disconnect();
	        return rowInserted;
	    }
	
	@Override
	 public List<LigneCmd> listAllLigneCmds(int idcmd) throws SQLException {
		
		 List<LigneCmd> listBook = new ArrayList<>();
		 connect();
	        String sql = "SELECT * FROM ligneCmd WHERE idcmd=?";
	         
	      
	         
	        PreparedStatement st = jdbcConnection.prepareStatement(sql);
	        
            st.setInt(1, idcmd);
  
            ResultSet resultSet = st.executeQuery();

	        while (resultSet.next()) {
	            int qtcmd = resultSet.getInt("qtcmd");
	            int id = resultSet.getInt("id");
	            int idproduit = resultSet.getInt("idproduit");

	            LigneCmd ligneCmd = new LigneCmd(id, qtcmd, idcmd, idproduit);
	            listBook.add(ligneCmd);
	        }
	         
	        resultSet.close();
	        st.close();
	         
	        disconnect();
	         
	        return listBook;
	    }
	@Override
	public boolean deleteLigneCmd(LigneCmd ligneCmd) throws SQLException {
		
		String sql = "DELETE FROM ligneCmd where id = ?";
		  connect();
    
     PreparedStatement statement = jdbcConnection.prepareStatement(sql);
     statement.setInt(1, ligneCmd.getId());
      
     boolean rowDeleted = statement.executeUpdate() > 0;
     statement.close();
     disconnect();
     return rowDeleted;     
 }
	@Override
 public boolean updateLigneCmd(LigneCmd ligneCmd) throws SQLException {
 	
     String sql = "UPDATE ligneCmd SET qtcmd = ?, idcmd = ?, idproduit = ?";
     sql += " WHERE id = ?";
     connect();
     
     PreparedStatement statement = jdbcConnection.prepareStatement(sql);
     statement.setInt(1, ligneCmd.getQtecmd());
     statement.setInt(2, ligneCmd.getIdcmd());
     statement.setInt(3, ligneCmd.getIdproduit());
     statement.setInt(4, ligneCmd.getId());

     boolean rowUpdated = statement.executeUpdate() > 0;
     statement.close();
     disconnect();
     return rowUpdated;     
 }
 
	@Override
 public LigneCmd getLigneCmd(int id) throws SQLException {
     LigneCmd ligneCmd = null;
     String sql = "SELECT * FROM ligneCmd WHERE id = ?";
      
     connect();
      
     PreparedStatement statement = jdbcConnection.prepareStatement(sql);
     statement.setInt(1, id);
      
     ResultSet resultSet = statement.executeQuery();
      
     if (resultSet.next()) {
           int qtcmd = resultSet.getInt("qtcmd");
           int idcmd = resultSet.getInt("idcmd");
           int idproduit = resultSet.getInt("idproduit");

         ligneCmd = new LigneCmd(id, qtcmd, idcmd, idproduit);
     }
      
     resultSet.close();
     statement.close();
      
     return ligneCmd;
 }


}
