package ma.fstt.entities;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Produit {


	private String nom;
	private int prix;
	private int qtstock;

	private int id;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getQtstock() {
		return qtstock;
	}

	public void setQtstock(int qtstock) {
		this.qtstock = qtstock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Produit [nom=" + nom + ", prix=" + prix + ", qtstock=" + qtstock + ", id=" + id + "]";
	}

	public Produit(int id,String nom, int prix, int qtstock) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.qtstock = qtstock;
		this.id = id;
	}

	public Produit() {
		super();
	}

	public Produit(String nom, int qtstock,int prix) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.qtstock = qtstock;
	}

	public Produit(int id) {
		super();
		this.id = id;
	}


	

	

}
