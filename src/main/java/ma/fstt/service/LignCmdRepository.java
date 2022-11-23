package ma.fstt.service;

import java.sql.SQLException;
import java.util.List;

import ma.fstt.entities.LigneCmd;
import ma.fstt.entities.LigneCmd;

public interface LignCmdRepository {
	boolean insertLigneCmd(LigneCmd ligneCmd) throws SQLException;
	 List<LigneCmd> listAllLigneCmds(int id) throws SQLException;
	 LigneCmd getLigneCmd(int id) throws SQLException;
	 boolean updateLigneCmd(LigneCmd ligneCmd) throws SQLException;
	 boolean deleteLigneCmd(LigneCmd ligneCmd) throws SQLException;
	 
}
