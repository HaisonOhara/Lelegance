/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cartao;
import model.CartaoDAO;
import model.Usuario;

/**
 *
 * @author joaov
 */
@WebServlet(name = "ControleCartao", urlPatterns = {"/ControleCartao","/CadastrarCartao"})
public class ControleCartao extends HttpServlet {

  

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try{
                String uri= request.getRequestURI();
                if(uri.equals(request.getContextPath() + "/CadastrarCartao"))
                {
                   cadastrarCartao(request,response);
                }
         }catch(Exception e){
                e.printStackTrace();
                response.sendRedirect("erro.jsp");
    }
    }

   public void cadastrarCartao(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
   {
       Cartao c = new Cartao();
       c.setCnn(Integer.parseInt(request.getParameter("cnn")));
       c.setNomeCartao(request.getParameter("nomeCartao"));
       c.setNumero(request.getParameter("numeroCartao"));
       c.setVencimento(request.getParameter("vencimento"));
       
       HttpSession session = request.getSession();
       Usuario u = (Usuario) session.getAttribute("usuarioAutenticado");
       c.setUsuario(u);
       
       CartaoDAO dao = new CartaoDAO();
       dao.Cadastrar(c);
       request.getRequestDispatcher("confereTudo").forward(request, response);
   }
  public void mostrarCartao(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
  {
      Cartao c = new Cartao();
      HttpSession session = request.getSession();
      Usuario u = (Usuario) session.getAttribute("usuarioAutenticado");
      c.setUsuario(u);
      
      CartaoDAO dao = new CartaoDAO();
      c = dao.BuscarCartao(c);
      
      request.setAttribute("cartao",c);
      request.getRequestDispatcher("").forward(request, response);
      
  }
  
  public void preAlterar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException{
      Cartao c = new Cartao();
      c.setId(Integer.parseInt(request.getParameter("idCartao")));
      CartaoDAO dao = new CartaoDAO();
      
      c = dao.BuscarPorId(c);
      request.setAttribute("cartao",c);
      request.getRequestDispatcher("").forward(request, response);
  
  
  
  }
 
 public void alterarCartao(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
 {
      Cartao c = new Cartao();
      c.setCnn(Integer.parseInt(request.getParameter("cnn")));
      c.setNomeCartao(request.getParameter("nomeCartao"));
      c.setNumero(request.getParameter("numeroCartao"));
      c.setVencimento(request.getParameter("vencimento"));
      c.setId(Integer.parseInt(request.getParameter("idcartao")));
      
      CartaoDAO dao = new CartaoDAO();
      dao.AlterarCartao(c);
      request.getRequestDispatcher("").forward(request, response);
 }
 public void excluirCartao(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
 {
     Cartao c = new Cartao();
     c.setId(Integer.parseInt(request.getParameter("idcartao")));
     
     CartaoDAO dao = new CartaoDAO();
     dao.ExcluirCartao(c);
     request.getRequestDispatcher("").forward(request, response);
 }
}
