package ma.fstt.controller;

import java.io.IOException;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.fstt.dao.ClientDAO;
import ma.fstt.dao.CommandeDAO;
import ma.fstt.dao.LigneCmdDAO;
import ma.fstt.dao.ProduitDAO;
import ma.fstt.entities.Client;
import ma.fstt.entities.Commande;
import ma.fstt.entities.LigneCmd;
import ma.fstt.entities.Produit;

/**
 * Servlet implementation class CommandeServlet
 */
@WebServlet("/commande")
public class CommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private CommandeDAO commandeDAO;
	  private ClientDAO clientDAO;
	  private LigneCmdDAO lignecmdDAO;
	  private ProduitDAO produitDAO;


	    /**
	     * @see HttpServlet#HttpServlet()
	     */

	    public void init() {
	        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
	        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
	        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
	 
	        commandeDAO= new CommandeDAO(jdbcURL, jdbcUsername, jdbcPassword);
	        clientDAO= new ClientDAO(jdbcURL, jdbcUsername, jdbcPassword);
	        lignecmdDAO= new LigneCmdDAO(jdbcURL, jdbcUsername, jdbcPassword);
	        produitDAO= new ProduitDAO(jdbcURL, jdbcUsername, jdbcPassword);

	 
	    }
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			String action = request.getParameter("action");
			
			System.out.println(action);
	        if(action == null) {
	        	
	        	 try {
	        		 listCommande(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        else {
	        	action =action.split("\\?",2)[0];
	        try {

	            switch (action) {
	            case "newcmd":
	                showNewForm(request, response);
	                break;
	            case "newLigneCmd":
	            	showNewFormlign(request, response);
	                break;
	            case "insertcmd":
	                insertCommande(request, response);
	                break;
	            case "insertligncmd":
	                insertCommande(request, response);
	                break;
	            case "deletecmd":
	                deleteCommande(request, response);
	                break;
	            case "editLigneCmd":
	                showEditForm(request, response);
	                break;
	            case "updatecmd":
	                updateCommande(request, response);
	                break;
	            case "detailscmd":
	                listligne(request, response);
	                break;
	            case "deleteLigneCmd":
	            	deletelignecmd(request, response);
	                break;

	            default:
	                listCommande(request, response);
	                break;
	            }
	        } catch (SQLException ex) {
	            throw new ServletException(ex);
	        }	}}
		private void listCommande(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        List<Commande> listCommande = commandeDAO.listAllCommandes();
	        request.setAttribute("listCommande", listCommande);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("CommandeListe.jsp");
	        dispatcher.forward(request, response);
	    }
		private void listligne(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
			String[] newReq = request.getParameter("action").split("=");

	        int id = Integer.parseInt(newReq[1]);
	 
	        List<LigneCmd> listLigneCmd = lignecmdDAO.listAllLigneCmds(id);
	        System.out.println(listLigneCmd);
	        request.setAttribute("listLigneCmd", listLigneCmd);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("LigneCmdList.jsp");
	        dispatcher.forward(request, response);
	    }
	    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException, SQLException {
	        List<Client> listClient = clientDAO.listAllClients();
	        request.setAttribute("listClient", listClient);
	        List<Produit> listProduit = produitDAO.listAllProduits();
	        request.setAttribute("listProduit", listProduit);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("CommandeForm.jsp");
	        dispatcher.forward(request, response);
	    }
	    private void showNewFormlign(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException, SQLException {
	       
	        List<Produit> listProduit = produitDAO.listAllProduits();
	        request.setAttribute("listProduit", listProduit);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("LigneCmdForm.jsp");
	        dispatcher.forward(request, response);
	    }
	    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	    	String[] newReq = request.getParameter("action").split("=");
	        int id = Integer.parseInt(newReq[1]);

	        LigneCmd  existingBook = lignecmdDAO.getLigneCmd(id);
	        System.out.println(id);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("LigneCmdForm.jsp");
	        request.setAttribute("lignecmd", existingBook);
	        List<Produit> listProduit = produitDAO.listAllProduits();
	        request.setAttribute("listProduit", listProduit);
	        dispatcher.forward(request, response);
	 
	    }
	 
	    private void insertCommande(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {

	        Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
            String strDate = dateFormat.format(date);  
	        int idclient =Integer.parseInt( request.getParameter("idclient"));
	     
	        Commande newCommande = new Commande(strDate, idclient);

	        commandeDAO.insertCommande(newCommande);
	        int idproduit=Integer.parseInt( request.getParameter("idproduit"));
	        int Qtecmd=Integer.parseInt( request.getParameter("Qtecmd"));

	        
           Commande cmdfin =commandeDAO.getcmdfin();
       	        int idcmd=cmdfin.getId();
            System.out.println(idcmd);
	        LigneCmd newligne = new LigneCmd(Qtecmd,idcmd,idproduit);
	        lignecmdDAO.insertLigneCmd(newligne);

	        
	        response.sendRedirect("commande?action=list");
	    }
	 
	    private void updateCommande(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
		    int qtcmd =Integer.parseInt( request.getParameter("Qtecmd"));

		    int idproduit =Integer.parseInt( request.getParameter("idproduit"));
		    int idcmd=lignecmdDAO.getLigneCmd(id).getIdcmd();
            System.out.println(idcmd);

		    LigneCmd LigneCmd = new LigneCmd(id,qtcmd,idcmd , idproduit);
		    lignecmdDAO.updateLigneCmd(LigneCmd);
	        response.sendRedirect("commande?action=list");
	    }
	    
	 
	    private void deleteCommande(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	    	String[] newReq = request.getParameter("action").split("=");
	    	
	        int id = Integer.parseInt(newReq[1]);	 
	        Commande commande = new Commande(id);
	        commandeDAO.deleteCommande(commande);
	        response.sendRedirect("commande?action=list");
	 
	    }
	    private void deletelignecmd(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	    	String[] newReq = request.getParameter("action").split("=");
	    	
	        int id = Integer.parseInt(newReq[1]);	 
	        LigneCmd LigneCmd = new LigneCmd(id);
	        lignecmdDAO.deleteLigneCmd(LigneCmd);
	        response.sendRedirect("commande?action=list");
	 
	    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
