package com.cogent.bankserver;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/display")
public class DisplayServer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final String DB_URL = "jdbc:mysql://localhost/BankApp";
	static final String USER = "root";
	static final String PASS = "ASDF1qazxsw2@@";
	final String DRIVER = "com.mysql.cj.jdbc.Driver";
	Connection conn = null;

	//1 init
	public void init() throws ServletException {

		// Database connection through Driver Manager
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
	
	//2 display-get/fetch
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// Set the response content type and
			// get the PrintWriter object.
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			// Set up HTML table formatting for the output
			out.println("<html><body>");
			out.println("<h3>Bank Details</h3>");
			out.println("<table border=2><tr>" + "<td><b>id</b></td>" + "<td><b>names</b></td>"  + "<td><b>account</b></td>" + "<td><b>addresses</b></td> ");

			// Create JDBC statement object, construct
			// the SQL query and execute the query.
			Statement stmt = conn.createStatement();
			String sql = "select * from customers";
			ResultSet rs = stmt.executeQuery(sql);

			// Loop through the result set to
			// retrieve the individual data items.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				float account = rs.getFloat("account");
				String address = rs.getString("address");

				out.println("<tr>" + "<td>" + id + "</td>" + "<td>" + name + "</td>" +  "<td>" + account + "</td>" + "<td>" + address + "</td>"  );

			}
			out.println("</table></body></html>");

			// Close Result set, Statement
			// and PrintWriter objects.
			rs.close();
			stmt.close();
			out.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	// 3.Close connection object.
	public void destroy() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
