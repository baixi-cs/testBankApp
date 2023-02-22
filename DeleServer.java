package com.cogent.bankserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
	
import java.sql.SQLException;

import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleServer extends HttpServlet {

		private static final long serialVersionUID = 1L;
		static final String DB_URL = "jdbc:mysql://localhost/BankApp";
		static final String USER = "root";
		static final String PASS = "ASDF1qazxsw2@@";
		final String DRIVER = "com.mysql.cj.jdbc.Driver";
		Connection conn = null;

		public void init() throws ServletException {

			// Database connection through Driver Manager
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		}
		protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	         
	        // read form fields
	    	response.setContentType("text/html");//((ServletResponse) request).setContentType("text/html");
	        int id = Integer.parseInt(request.getParameter("id")); //get val from the request using getParameter
	        System.out.println("id: " + id);
	        
	       
	        // get response writer
	        PrintWriter writer = response.getWriter();
	         
	        // print data
	        writer.print("<html> <body>");
	        writer.print("<h2>customer id is</h2><br>");
	        writer.print("customer id is: " + id + "<br/>");
	        writer.print("</body></html> ");
	        
	        //////////////////logic to add to database //////
	        try {
	   
			PreparedStatement stmt=conn.prepareStatement("delete from customers where id = ?)");  
			stmt.setInt(1,id);//1 specifies the first parameter in the query  
		
			  
			  
			
			  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
	        }catch(Exception e) {
	        	System.out.println("something Wrong");
	        }
	        /////////////////////////////////////////////////
	        
	      
	    }

	
//
//
//		public void destroy() {
//
//			// Close connection object.
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

	}
