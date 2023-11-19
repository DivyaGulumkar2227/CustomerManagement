package Sunbase_Data.DAo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Sunbase_Data.model.Customer;

public class CustomerDAO 
{
	private String jdbcURL = "jdbc:mysql://localhost:3306/Cutomer1?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "divya@2001";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (Firtname,Lastname,Address,City,email,Phone) VALUES "
			+ " (?, ?, ?,?,?,?);";

	private static final String SELECT_USER_BY_ID = "select id,Firtname,Lastname,Address,City,email,Phone from customer where id =?";
	private static final String SELECT_ALL_USERS = "select * from customer";
	private static final String DELETE_USERS_SQL = "delete from customer where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set Firtname = ?,Lastname = ?, Address=?,City =?,email= ?, Phone =? where id = ?;";

	public CustomerDAO() {
	}
     
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	
	public void insertCustomer(Customer Customer) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, Customer.getFirtname());
			preparedStatement.setString(1, Customer.getLastname());
			preparedStatement.setString(1, Customer.getAddress());
			preparedStatement.setString(1, Customer.getCity());
			preparedStatement.setString(2, Customer.getEmail());
			preparedStatement.setString(3, Customer.getPhone());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Customer selectUser(int id) {
		Customer customer = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String Firtname = rs.getString("Firtname");
				String Lastname = rs.getString("Lastname");
				String Address = rs.getString("Address");
				String City = rs.getString("City");
				
				String email = rs.getString("email");
				String Phone = rs.getString("Phone");
				customer = new Customer(id,Firtname,Lastname,Address,City,email,Phone);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return customer;
	}	
		
		public List<Customer> selectAllCustomers() {

			// using try-with-resources to avoid closing resources (boiler plate code)
			List<Customer> Customer = new ArrayList<>();
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

					// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id");
					String Firtname = rs.getString("Firtname");
					String Lastname = rs.getString("Lastname");
					String Address = rs.getString("Address");
					String City = rs.getString("City");
					String email = rs.getString("email");
					String Phone = rs.getString("Phone");
					Customer.add(new Customer(id,Firtname,Lastname,Address,City,email,Phone));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return Customer;
		}
		
		public boolean deleteCustomer(int id) throws SQLException {
			boolean rowDeleted;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
				statement.setInt(1, id);
				rowDeleted = statement.executeUpdate() > 0;
			}
			return rowDeleted;
		}
		
		public boolean updatecustomer(Customer customer) throws SQLException {
			boolean rowUpdated;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
				statement.setString(1, customer .getFirtname());
				statement.setString(2, customer.getLastname());
				statement.setString(3, customer.getAddress());
				statement.setString(4, customer.getCity());
				statement.setString(4, customer.getEmail());
				statement.setString(4, customer.getPhone());
				statement.setInt(4, customer.getId());

				rowUpdated = statement.executeUpdate() > 0;
			}
			return rowUpdated;
		}
	
	
		private void printSQLException(SQLException ex) {
			for (Throwable e : ex) {
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}

	
	
	
	
	
	
	
	
	
	


	

}
