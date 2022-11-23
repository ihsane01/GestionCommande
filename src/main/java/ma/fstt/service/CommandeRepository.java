package ma.fstt.service;

import java.sql.SQLException;
import java.util.List;

import ma.fstt.entities.Commande;
import ma.fstt.entities.Commande;

public interface CommandeRepository {
	boolean insertCommande(Commande commande) throws SQLException;
	 List<Commande> listAllCommandes() throws SQLException;
	 Commande getCommande(int id) throws SQLException;
	 boolean updateCommande(Commande commande) throws SQLException;
	 boolean deleteCommande(Commande commande) throws SQLException;
	 Commande getcmdfin()throws SQLException;
}
