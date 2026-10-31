package com.codequest.servlet;
import com.codequest.model.Mission; import com.codequest.util.DBConnection; import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*;
import java.io.IOException; import java.sql.*; import java.util.*;
@WebServlet("/view-questions")
public class ViewQuestionsServlet extends HttpServlet{
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  if(req.getSession(false)==null||req.getSession(false).getAttribute("adminId")==null){resp.sendRedirect("admin-login.jsp");return;}
  List<Mission> list=new ArrayList<>();
  try(Connection c=DBConnection.getConnection();PreparedStatement ps=c.prepareStatement("SELECT * FROM missions ORDER BY id DESC");ResultSet rs=ps.executeQuery()){
   while(rs.next()){Mission m=new Mission();m.setId(rs.getInt("id"));m.setTitle(rs.getString("title"));m.setCategory(rs.getString("category"));m.setDifficulty(rs.getString("difficulty"));m.setXpReward(rs.getInt("xp_reward"));list.add(m);}
  }catch(Exception e){req.setAttribute("message",e.getMessage());}
  req.setAttribute("missions",list);req.getRequestDispatcher("view-questions.jsp").forward(req,resp);
 }
}
