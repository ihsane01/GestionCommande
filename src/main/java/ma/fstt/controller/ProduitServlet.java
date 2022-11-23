package ma.fstt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.fstt.dao.ProduitDAO;
import ma.fstt.dao.ProduitDAO;
import ma.fstt.entities.Produit;
import ma.fstt.entities.Produit;

/**
 * Servlet implementation class ProduitServlet
 */
@WebServlet("/produit")
public class ProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private ProduitDAO produitDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
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
				listProduit(request, response);
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
            case "newprd":
                showNewForm(request, response);
                break;
            case "insertprd":
                insertProduit(request, response);
                break;
            case "deleteprd":
                deleteProduit(request, response);
                break;
            case "editprd":
                showEditForm(request, response);
                break;
            case "updateprd":
                updateProduit(request, response);
                break;

            default:
                listProduit(request, response);
                break;
          
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }	
        }}
	private void listProduit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Produit> listProduit = produitDAO.listAllProduits();
        request.setAttribute("listProduit", listProduit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Produitlist.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ProduitForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	
    	String[] newReq = request.getParameter("action").split("=");
    	System.out.println(newReq[1]);
        int id = Integer.parseInt(newReq[1]);
        Produit existingBook = produitDAO.getProduit(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ProduitForm.jsp");
        request.setAttribute("produit", existingBook);
        dispatcher.forward(request, response);
 
    }
 
    private void insertProduit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nom = request.getParameter("nom");
        int qtstock = Integer.parseInt(request.getParameter("qtstock"));
        int prix = Integer.parseInt(request.getParameter("prix"));
  

        Produit newProduit = new Produit(nom, qtstock,prix);
        produitDAO.insertProduit(newProduit);
        response.sendRedirect("produit?action=list");
    }
 
    private void updateProduit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        int qtstock = Integer.parseInt(request.getParameter("qtstock"));
        int prix = Integer.parseInt(request.getParameter("prix"));
  
        Produit produit = new Produit(id, nom, qtstock, prix);
        produitDAO.updateProduit(produit);
        response.sendRedirect("produit?action=list");
    }
 
    private void deleteProduit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String[] newReq = request.getParameter("action").split("=");

        int id = Integer.parseInt(newReq[1]);
 
        Produit produit = new Produit(id);
        produitDAO.deleteProduit(produit);
        response.sendRedirect("produit?action=list");
 
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
