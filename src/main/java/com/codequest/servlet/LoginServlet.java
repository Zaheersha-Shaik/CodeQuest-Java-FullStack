package com.codequest.servlet;
import com.codequest.util.DBConnection;
import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*;
import java.io.IOException; import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  String login=req.getParameter("login"), password=req.getParameter("password");
  try(Connection con=DBConnection.getConnection()){
   PreparedStatement ps=con.prepareStatement("SELECT id,username FROM users WHERE (username=? OR email=?) AND password=?");
   ps.setString(1,login); ps.setString(2,login); ps.setString(3,password);
   ResultSet rs=ps.executeQuery();
   if(rs.next()){
    HttpSession s=req.getSession(); s.setAttribute("userId",rs.getInt("id")); s.setAttribute("username",rs.getString("username"));
    resp.sendRedirect("dashboard");
   }else{ req.setAttribute("message","Invalid username/email or password."); req.getRequestDispatcher("login.jsp").forward(req,resp); }
  }catch(Exception e){ req.setAttribute("message","Database error: "+e.getMessage()); req.getRequestDispatcher("login.jsp").forward(req,resp); }
 }
}
