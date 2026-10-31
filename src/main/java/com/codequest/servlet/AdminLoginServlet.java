package com.codequest.servlet;
import com.codequest.util.DBConnection; import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*;
import java.io.IOException; import java.sql.*;
@WebServlet("/admin-login")
public class AdminLoginServlet extends HttpServlet{
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  String u=req.getParameter("username"),p=req.getParameter("password");
  try(Connection con=DBConnection.getConnection()){
   PreparedStatement ps=con.prepareStatement("SELECT id,username FROM admins WHERE username=? AND password=?"); ps.setString(1,u);ps.setString(2,p);
   ResultSet rs=ps.executeQuery();
   if(rs.next()){ HttpSession s=req.getSession();s.setAttribute("adminId",rs.getInt("id"));s.setAttribute("adminUsername",rs.getString("username"));resp.sendRedirect("admin-dashboard.jsp");}
   else{req.setAttribute("message","Invalid admin credentials.");req.getRequestDispatcher("admin-login.jsp").forward(req,resp);}
  }catch(Exception e){req.setAttribute("message","Database error: "+e.getMessage());req.getRequestDispatcher("admin-login.jsp").forward(req,resp);}
 }
}
