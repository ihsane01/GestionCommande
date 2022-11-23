package ma.fstt.service;

import java.sql.SQLException;
import java.util.List;

import ma.fstt.entities.Produit;
import ma.fstt.entities.Produit;

public interface ProduitRepository {
	boolean insertProduit(Produit produit) throws SQLException;
	 List<Produit> listAllProduits() throws SQLException;
	 Produit getProduit(int id) throws SQLException;
	 boolean updateProduit(Produit produit) throws SQLException;
	 boolean deleteProduit(Produit produit) throws SQLException;
	 
}
