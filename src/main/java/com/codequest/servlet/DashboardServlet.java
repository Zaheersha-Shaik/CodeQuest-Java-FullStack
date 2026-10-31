package com.codequest.servlet;
import com.codequest.util.DBConnection; import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*;
import java.io.IOException; import java.sql.*;
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet{
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  HttpSession s=req.getSession(false);if(s==null||s.getAttribute("userId")==null){resp.sendRedirect("login.jsp");return;}
  int id=(Integer)s.getAttribute("userId");
  try(Connection c=DBConnection.getConnection()){
   PreparedStatement u=c.prepareStatement("SELECT username,xp,level FROM users WHERE id=?");u.setInt(1,id);ResultSet ur=u.executeQuery();
   if(ur.next()){req.setAttribute("username",ur.getString("username"));req.setAttribute("xp",ur.getInt("xp"));req.setAttribute("level",ur.getInt("level"));}
   PreparedStatement q=c.prepareStatement("SELECT COUNT(*) FROM mission_submissions WHERE user_id=? AND is_correct=TRUE");q.setInt(1,id);ResultSet qr=q.executeQuery();if(qr.next())req.setAttribute("completed",qr.getInt(1));
  }catch(Exception e){req.setAttribute("message",e.getMessage());}
  req.getRequestDispatcher("dashboard.jsp").forward(req,resp);
 }
}
