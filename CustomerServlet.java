package Sunbase_Data.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sunbase_Data.DAo.CustomerDAO;
import Sunbase_Data.model.Customer;

@WebServlet("/")
public class CustomerServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDAO;

	public void init() {
		customerDAO = new CustomerDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertCustomer(request, response);
				break;
			case "/delete":
				deleteCustomer(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			default:
				listCustomer(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	
	private void listCustomer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Customer> listCustomer = customerDAO.selectAllCustomers();
		request.setAttribute("listCustomer", listCustomer);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Customer existingcustomer = customerDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("Customer", existingcustomer);
		dispatcher.forward(request, response);

	}

	private void insertCustomer(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String Firtname = request.getParameter("Firtname");
		String Lastname = request.getParameter("Lastname");
		String Address = request.getParameter("Address");
		String City = request.getParameter("City");
		String email = request.getParameter("email");
		String Phone = request.getParameter("Phone");
		
		Customer newCustomer = new Customer(Firtname,Lastname,Address,City,email,Phone);
		customerDAO.insertCustomer(newCustomer);
		response.sendRedirect("list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String Firtname = request.getParameter("Firtname");
		String Lastname = request.getParameter("Lastname");
		String Address = request.getParameter("Address");
		String City = request.getParameter("City");
		String email = request.getParameter("email");
		String Phone = request.getParameter("Phone");
		Customer book = new Customer(Firtname,Lastname,Address,City,email,Phone);
		customerDAO.updatecustomer(book);
		response.sendRedirect("list");
	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		customerDAO.deleteCustomer(id);
		response.sendRedirect("list");

	}

}
