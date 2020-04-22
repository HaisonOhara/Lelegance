/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Endereco;
import model.EnderecoDAO;
import model.Usuario;

/**
 *
 * @author joaov
 */
@WebServlet(name = "ControleEntrega", urlPatterns = {"/ControleEntrega","/CadastrarEndereco","/PreAlterarEndereco","/ExcluirEndereco"})
public class ControleEntrega extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try{
                String uri= request.getRequestURI();
                if(uri.equals(request.getContextPath() + "/PreAlterarEndereco"))
                {
                   preAlterar(request,response);
                }
                else if(uri.equals(request.getContextPath() + "/ExcluirEndereco"))
                {
                    excluirEndereco(request,response);
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
                String uri= request.getRequestURI();
                if(uri.equals(request.getContextPath() + "/CadastrarEndereco"))
                {
                   CadastrarEndereco(request,response);
                }
                else if(uri.equals(request.getContextPath() + "/AlterarEndereco"))
                {
                    AlterarEndereco(request,response);
                }
          }catch(Exception e){
                e.printStackTrace();
                response.sendRedirect("erro.jsp");
        
    }
    }
    
   public void CadastrarEndereco(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
   {
       Endereco e = new Endereco();
       e.setCep(request.getParameter("cep"));
       e.setCidade(request.getParameter("cidade"));
       e.setComplemento(request.getParameter("complemento"));
       e.setEndereco(request.getParameter("endereco"));
       e.setEstado(request.getParameter("estado"));
       e.setNumero(Integer.parseInt(request.getParameter("numero")));
       
       HttpSession session = request.getSession();
       Usuario u = (Usuario) session.getAttribute("usuarioAutenticado");
       e.setUsuario(u);
       
      EnderecoDAO dao = new EnderecoDAO();
      dao.Cadastrar(e);
      
     request.getRequestDispatcher("cadastrarCartao.jsp").forward(request, response);
      
   }
   public void AlterarEndereco(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
   {
       Endereco e = new Endereco();
       e.setCep(request.getParameter("cep"));
       e.setCidade(request.getParameter("cidade"));
       e.setComplemento(request.getParameter("complemento"));
       e.setEndereco(request.getParameter("endereco"));
       e.setEstado(request.getParameter("estado"));
       e.setNumero(Integer.parseInt("numero"));
       e.setId(Integer.parseInt(request.getParameter("idEndereco")));
       
       EnderecoDAO dao = new EnderecoDAO();
       dao.AlterarEndereco(e);
       
       request.getRequestDispatcher("").forward(request, response);
   }
   
    public void preAlterar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException{
      Endereco e = new Endereco();
      e.setId(Integer.parseInt(request.getParameter("idEndereco")));
      EnderecoDAO dao = new EnderecoDAO();
      
      e = dao.BuscarEndereco(e);
        RequestDispatcher rd = request.getRequestDispatcher("");
       request.setAttribute("end",e);
       rd.forward(request, response);
   }
   public void excluirEndereco(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
   {
       Endereco e = new Endereco();
       e.setId(Integer.parseInt(request.getParameter("idEndereco")));
       
       EnderecoDAO dao = new EnderecoDAO();
       dao.ExcluirEndereco(e);
       request.getRequestDispatcher("").forward(request, response);
   }
  public void mostrarEndereco(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
  {
      Endereco e = new Endereco();
      HttpSession session = request.getSession();
      Usuario u = (Usuario) session.getAttribute("usuarioAutenticado");
      e.setUsuario(u);
      
      EnderecoDAO dao = new EnderecoDAO();
      e= dao.BuscarEndereco(e);
      
      
   
  }
}
