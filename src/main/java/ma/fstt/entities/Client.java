package ma.fstt.entities;

import javax.enterprise.context.ApplicationScoped;


public class Client {

	private int id;
	private String Nom;
	private String Prenom;
	private String adress;
	private String tele;
    

	

	public Client(int id, String nom, String prenom, String adress, String tele) {
		super();
		this.id = id;
		Nom = nom;
		Prenom = prenom;
		this.adress = adress;
		this.tele = tele;
	}
	

	public Client(int id) {
		super();
		this.id = id;
	}


	public Client(String nom, String prenom, String adress, String tele) {
		super();
		Nom = nom;
		Prenom = prenom;
		this.adress = adress;
		this.tele = tele;
	}


	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Client [Nom=" + Nom + ", Prenom=" + Prenom + ", adress=" + adress + ", tele=" + tele + ", id=" + id
				+ "]";
	}

	public Client(String nom, String prenom, String adress, String tele, int id) {
		super();
		Nom = nom;
		Prenom = prenom;
		this.adress = adress;
		this.tele = tele;
		this.id = id;
	}
	
}
