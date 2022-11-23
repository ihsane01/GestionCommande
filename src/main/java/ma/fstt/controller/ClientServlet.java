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

import ma.fstt.dao.ClientDAO;
import ma.fstt.entities.Client;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/client")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private ClientDAO clientDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        clientDAO= new ClientDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
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
				listClient(request, response);
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
            case "new":
                showNewForm(request, response);
                break;
            case "insert":
                insertClient(request, response);
                break;
            case "delete":
                deleteClient(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateClient(request, response);
                break;

            default:
                listClient(request, response);
                break;
          
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }	
        }}
	private void listClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Client> listClient = clientDAO.listAllClients();
        request.setAttribute("listClient", listClient);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ClientList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ClientForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	
    	String[] newReq = request.getParameter("action").split("=");
    	
        int id = Integer.parseInt(newReq[1]);
        Client existingBook = clientDAO.getClient(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ClientForm.jsp");
        request.setAttribute("client", existingBook);
        dispatcher.forward(request, response);
 
    }
 
    private void insertClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adress = request.getParameter("adress");
        String tele =request.getParameter("adress");

        Client newClient = new Client(nom, prenom,adress, tele);
        clientDAO.insertClient(newClient);
        response.sendRedirect("client?action=list");
    }
 
    private void updateClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adress =request.getParameter("adress");
        String tele =request.getParameter("tele");

        Client client = new Client(id, nom, prenom, adress,tele);
        clientDAO.updateClient(client);
        response.sendRedirect("client?action=list");
    }
 
    private void deleteClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String[] newReq = request.getParameter("action").split("=");

        int id = Integer.parseInt(newReq[1]);
 
        Client client = new Client(id);
        clientDAO.deleteClient(client);
        response.sendRedirect("client?action=list");
 
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
