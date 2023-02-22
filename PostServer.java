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

@WebServlet("/addpost")
public class PostServer extends HttpServlet {

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
	    	response.setContentType("text/html");
	    	//((ServletResponse) request).setContentType("text/html");
	    	
	        int id = Integer.parseInt(request.getParameter("id"));
	        String name = request.getParameter("name");
	        double account = Double.parseDouble(request.getParameter("account"));
	        String address = request.getParameter("address");
	         
	        System.out.println("id: " + id);
	        System.out.println("name: " + name);
	        System.out.println("username: " + account);
	        System.out.println("address: " + address);
	 
	        // do some processing here...
	         
	        // get response writer
	        PrintWriter writer = response.getWriter();
	         
	        // build HTML code
	        String htmlRespone = "<html>";
	        htmlRespone += "<h2>customer id is: " + id + "<br/>";      
	        htmlRespone += "customer name is: " + name +"<br/>" ;
	        htmlRespone += "customer account is: " + account +"<br/>" ;
	        htmlRespone += "customer address is: " + address + "</h2>" ;
	    
	        htmlRespone += "</html>";
	        
	        //////////////////logic to add to database //////
	        try {
	        	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        	conn.createStatement();
	   
			PreparedStatement stmt=conn.prepareStatement("insert into customers(id, name, account, address)values(?,?,?,?)");  
			stmt.setInt(1,id);//1 specifies the first parameter in the query  
			stmt.setString(2,name);  
			stmt.setDouble(3,account); 
			stmt.setString(4,address);  
			  
			  
			
			  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
	        }catch(Exception e) {
	        	System.out.println("something Wrong");
	        }
	        /////////////////////////////////////////////////
	        
	        // return response
	        writer.println(htmlRespone);
	         
	    }

	


		public void destroy() {

			// Close connection object.
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
