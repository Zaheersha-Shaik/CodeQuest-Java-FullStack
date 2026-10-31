package com.codequest.servlet;
import com.codequest.util.DBConnection; import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*;
import java.io.IOException; import java.sql.*;
@WebServlet("/submit-answer")
public class SubmitAnswerServlet extends HttpServlet{
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  HttpSession s=req.getSession(false);if(s==null||s.getAttribute("userId")==null){resp.sendRedirect("login.jsp");return;}
  int userId=(Integer)s.getAttribute("userId"), missionId=Integer.parseInt(req.getParameter("missionId"));String answer=req.getParameter("answer");
  try(Connection c=DBConnection.getConnection()){
   c.setAutoCommit(false);
   PreparedStatement dup=c.prepareStatement("SELECT id FROM mission_submissions WHERE user_id=? AND mission_id=?");dup.setInt(1,userId);dup.setInt(2,missionId);
   if(dup.executeQuery().next()){c.rollback();req.setAttribute("result","Already Attempted");req.setAttribute("correct",false);req.setAttribute("message","You have already used your one attempt for this mission.");req.getRequestDispatcher("mission-result.jsp").forward(req,resp);return;}
   PreparedStatement m=c.prepareStatement("SELECT correct_answer,xp_reward FROM missions WHERE id=?");m.setInt(1,missionId);ResultSet rs;m.setInt(1,missionId);rs=m.executeQuery();
   if(!rs.next()){c.rollback();req.setAttribute("message","Mission not found.");req.getRequestDispatcher("mission-result.jsp").forward(req,resp);return;}
   boolean correct=rs.getString("correct_answer").equals(answer);int reward=correct?rs.getInt("xp_reward"):0;
   PreparedStatement ins=c.prepareStatement("INSERT INTO mission_submissions(user_id,mission_id,selected_answer,is_correct) VALUES(?,?,?,?)");ins.setInt(1,userId);ins.setInt(2,missionId);ins.setString(3,answer);ins.setBoolean(4,correct);ins.executeUpdate();
   if(correct){
    PreparedStatement up=c.prepareStatement("UPDATE users SET xp=xp+? WHERE id=?");up.setInt(1,reward);up.setInt(2,userId);up.executeUpdate();
    PreparedStatement level=c.prepareStatement("UPDATE users SET level=FLOOR(xp/100)+1 WHERE id=?");level.setInt(1,userId);level.executeUpdate();
   }
   c.commit();req.setAttribute("correct",correct);req.setAttribute("result",correct?"Mission Complete!":"Mission Failed");req.setAttribute("message",correct?"Correct answer! You earned "+reward+" XP.":"Incorrect answer. No XP awarded.");req.getRequestDispatcher("mission-result.jsp").forward(req,resp);
  }catch(Exception e){req.setAttribute("message","Error: "+e.getMessage());req.getRequestDispatcher("mission-result.jsp").forward(req,resp);}
 }
}
