package com.codequest.servlet;
import com.codequest.util.DBConnection; import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*;
import java.io.IOException; import java.sql.*;
@WebServlet("/add-question")
public class AddQuestionServlet extends HttpServlet{
 public void init(ServletConfig config)throws ServletException{ super.init(config); getServletContext().setAttribute("appName","CodeQuest"); }
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  if(req.getSession(false)==null||req.getSession(false).getAttribute("adminId")==null){resp.sendRedirect("admin-login.jsp");return;}
  try(Connection con=DBConnection.getConnection()){
   String sql="INSERT INTO missions(title,description,category,difficulty,option1,option2,option3,option4,correct_answer,xp_reward) VALUES(?,?,?,?,?,?,?,?,?,?)";
   PreparedStatement ps=con.prepareStatement(sql);
   String[] names={"title","description","category","difficulty","option1","option2","option3","option4","correctAnswer"};
   for(int i=0;i<names.length;i++){String v=req.getParameter(names[i]);if(v==null||v.trim().isEmpty()){req.setAttribute("message","All question fields are required.");req.getRequestDispatcher("add-question.jsp").forward(req,resp);return;}ps.setString(i+1,v.trim());}
   ps.setInt(10,Integer.parseInt(req.getParameter("xpReward"))); ps.executeUpdate();
   resp.sendRedirect("add-question.jsp?success=Mission added successfully!");
  }catch(Exception e){req.setAttribute("message","Error: "+e.getMessage());req.getRequestDispatcher("add-question.jsp").forward(req,resp);}
 }
}
