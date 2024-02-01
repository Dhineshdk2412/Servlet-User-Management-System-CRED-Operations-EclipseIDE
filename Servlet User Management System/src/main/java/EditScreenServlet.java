import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/editurl")
public class EditScreenServlet extends HttpServlet {
	private final static String query = "select name,email,mobile,dob,city,gender from user where id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	        PrintWriter pw = res.getWriter();
	        res.setContentType("text/html");
	        
	        int id = Integer.parseInt(req.getParameter("id"));
	        pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
	        
	        try {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        try(Connection con = DriverManager.getConnection("jdbc:mysql:///usermgmt","root","dhinesh24");
	        		PreparedStatement ps = con.prepareStatement(query);){
	        	ps.setInt(1, id);
	        	ResultSet rs = ps.executeQuery();
	        	rs.next();
	        	pw.println("<div style='margin:auto;width:500px;margin-top:100px'>");
	        	pw.println("<form action='edit?id="+id+"' method='post'>");
	        	pw.println("<table class='table table-hover table-striped'>");
	        	pw.println("<tr>");
	        	pw.println("<td>Name</td>");
	        	pw.println("<td><input type='text' name='name' value='"+rs.getString(1)+"'></td>");
	        	pw.println("</tr>");
	        	pw.println("<tr>");
	        	pw.println("<td>Email</td>");
	        	pw.println("<td><input type='email' name='email' value='"+rs.getString(2)+"'></td>");
	        	pw.println("</tr>");
	        	pw.println("<tr>");
	        	pw.println("<td>Mobile</td>");
	        	pw.println("<td><input type='number' name='mobile' value='"+rs.getString(3)+"'></td>");
	        	pw.println("</tr>");
	        	pw.println("<tr>");
	        	pw.println("<td>DOB</td>");
	        	pw.println("<td><input type='date' name='dob' value='"+rs.getString(4)+"'></td>");
	        	pw.println("</tr>");
	        	pw.println("<tr>");
	        	pw.println("<td>City</td>");
	        	pw.println("<td><input type='text' name='city' value='"+rs.getString(5)+"'></td>");
	        	pw.println("</tr>");
	        	pw.println("<tr>");
	        	pw.println("<td>Gender</td>");
	        	pw.println("<td><input type='text' name='gender' value='"+rs.getString(6)+"'></td>");
	        	pw.println("</tr>");
	        	pw.println("<tr>");
	        	pw.println("<td><button class='btn btn-outline-success' type='submit'>Edit</button></td>");
	        	pw.println("<td><button class='btn btn-outline-danger' type='reset'>Cancel</button></td>");
	        	pw.println("</tr>");
	        	pw.println("</table>");
	        	pw.println("</form>");
	 
	        }catch(SQLException se) {
	        	pw.println("<h2 class='text-light text-center'>" +se.getMessage()+"</h2>");
	        	se.printStackTrace();
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        pw.println("<a href='home.html'><button class='btn btn-outline-info'>Home</button></a>");
	        pw.println("</div>");
	        pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}




}