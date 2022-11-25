package ma.fstt.entities;

import javax.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class LigneCmd {

	private int Qtecmd;
	private int id;
	
	private int idcmd;
	private int idproduit;

	public int getQtecmd() {
		return Qtecmd;
	}
	public void setQtecmd(int qtecmd) {
		Qtecmd = qtecmd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LigneCmd(int id) {
		super();
		this.id = id;
	}
	public LigneCmd(int qtecmd, int idcmd, int idproduit) {
		super();
		Qtecmd = qtecmd;
		this.idcmd = idcmd;
		this.idproduit = idproduit;
	}
	public LigneCmd( int id,int qtecmd, int idcmd, int idproduit) {
		super();
		Qtecmd = qtecmd;
		this.id = id;
		this.idcmd = idcmd;
		this.idproduit = idproduit;
	}
	public int getIdcmd() {
		return idcmd;
	}
	public void setIdcmd(int idcmd) {
		this.idcmd = idcmd;
	}
	public int getIdproduit() {
		return idproduit;
	}
	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
	}
	@Override
	public String toString() {
		return "LigneCmd [Qtecmd=" + Qtecmd + ", id=" + id + "]";
	}

	
}
