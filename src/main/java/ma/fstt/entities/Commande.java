package ma.fstt.entities;




import javax.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class Commande {

	private  String date;
	

	private int id;
	private int idclient;


	public Commande( int id,String date, int idclient) {
		super();
		this.date = date;
		this.id = id;
		this.idclient = idclient;
	}


	public Commande(String date, int idclient) {
		super();
		this.date = date;
		this.idclient = idclient;
	}


	public Commande(int id) {
		super();
		this.id = id;
	}


	public int getIdclient() {
		return idclient;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}


	public String getString() {
		return date;
	}


	public void setString(String date) {
		this.date = date;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Commande [date=" + date + ", id=" + id + "]";
	}



	
}
