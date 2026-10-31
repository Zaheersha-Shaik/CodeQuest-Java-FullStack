package com.codequest.servlet;
import com.codequest.model.Mission; import com.codequest.util.DBConnection; import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*;
import java.io.IOException; import java.sql.*; import java.util.*;
@WebServlet("/missions")
public class MissionServlet extends HttpServlet{
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  List<Mission> list=new ArrayList<>();
  try(Connection c=DBConnection.getConnection();PreparedStatement ps=c.prepareStatement("SELECT id,title,description,category,difficulty,option1,option2,option3,option4,xp_reward FROM missions ORDER BY category,id");ResultSet rs=ps.executeQuery()){
   while(rs.next()){Mission m=new Mission();m.setId(rs.getInt("id"));m.setTitle(rs.getString("title"));m.setDescription(rs.getString("description"));m.setCategory(rs.getString("category"));m.setDifficulty(rs.getString("difficulty"));m.setOption1(rs.getString("option1"));m.setOption2(rs.getString("option2"));m.setOption3(rs.getString("option3"));m.setOption4(rs.getString("option4"));m.setXpReward(rs.getInt("xp_reward"));list.add(m);}
  }catch(Exception e){req.setAttribute("message",e.getMessage());}
  req.setAttribute("missions",list);req.getRequestDispatcher("missions.jsp").forward(req,resp);
 }
}
