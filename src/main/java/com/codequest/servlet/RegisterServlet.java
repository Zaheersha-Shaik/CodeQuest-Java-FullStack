package com.codequest.servlet;
import com.codequest.util.DBConnection;
import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*;
import java.io.IOException; import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  req.setCharacterEncoding("UTF-8");
  String username=req.getParameter("username"), email=req.getParameter("email");
  String password=req.getParameter("password"), confirm=req.getParameter("confirmPassword");
  if(username==null||email==null||password==null||username.trim().isEmpty()||email.trim().isEmpty()||password.isEmpty()){
   req.setAttribute("message","All fields are required."); req.getRequestDispatcher("register.jsp").forward(req,resp); return;
  }
  if(!password.equals(confirm)){ req.setAttribute("message","Passwords do not match."); req.getRequestDispatcher("register.jsp").forward(req,resp); return; }
  try(Connection con=DBConnection.getConnection()){
   PreparedStatement check=con.prepareStatement("SELECT id FROM users WHERE username=? OR email=?");
   check.setString(1,username.trim()); check.setString(2,email.trim());
   ResultSet rs=check.executeQuery();
   if(rs.next()){ req.setAttribute("message","Username or email already exists."); req.getRequestDispatcher("register.jsp").forward(req,resp); return; }
   PreparedStatement ps=con.prepareStatement("INSERT INTO users(username,email,password,xp,level) VALUES(?,?,?,?,?)");
   ps.setString(1,username.trim()); ps.setString(2,email.trim()); ps.setString(3,password); ps.setInt(4,0); ps.setInt(5,1); ps.executeUpdate();
   resp.sendRedirect("login.jsp?success=Registration successful! Please login.");
  }catch(Exception e){ req.setAttribute("message","Database error: "+e.getMessage()); req.getRequestDispatcher("register.jsp").forward(req,resp); }
 }
}
