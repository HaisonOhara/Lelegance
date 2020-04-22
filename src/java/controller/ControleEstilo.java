/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Estilo;
import model.EstiloDAO;

/**
 *
 * @author joaov
 */
@WebServlet(name = "ControleEstilo", urlPatterns = {"/ControleEstilo","/todosOsEstilos","/buscaEstilo"})
public class ControleEstilo extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try{
                String uri= request.getRequestURI();
                if(uri.equals(request.getContextPath() + "/buscaEstilo"))
                {
                   buscaEstilo(request,response);
                }else if(uri.equals(request.getContextPath() + "/todosOsEstilos"))
                {
                   todosEstilos(request,response);
                }
         }catch(Exception e){
                e.printStackTrace();
                response.sendRedirect("erro.jsp");
         }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        
                
        
    }catch(Exception e){
                e.printStackTrace();
                response.sendRedirect("erro.jsp");
  
    }  
}
    
    public void todosEstilos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
{
   EstiloDAO dao = new EstiloDAO();
   List<Estilo> estilos = dao.trazEstilos();
   request.setAttribute("Estilos",estilos);
   
   request.getRequestDispatcher("estilostey.jsp").forward(request, response);
}

   public void buscaEstilo(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
   {
       Estilo e = new Estilo();
       e.setId(Integer.parseInt(request.getParameter("id")));
       
       EstiloDAO dao = new EstiloDAO();
       e = dao.buscaEstilos(e);
       
     request.setAttribute("estilo",e);
     request.getRequestDispatcher("estilotey.jsp").forward(request, response);
     
   }
}