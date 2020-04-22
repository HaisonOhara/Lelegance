package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import model.PerfilDeAcesso;
import model.Usuario;
import model.UsuarioDAO;
import util.Formatar;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "ControleUsuario", urlPatterns = {"/ControleUsuario","/cadastrarUsuario","/logarUsuario","/preAlterar","/alterarUsuario","/sairUsuario"})
public class ControleUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
         try{
                String uri= request.getRequestURI();
                if(uri.equals(request.getContextPath() + "/preAlterar"))
                {
                   preAlterar(request,response);
                }
                else if(uri.equals(request.getContextPath() + "/sairUsuario")){
                    request.getSession().invalidate();
                    response.sendRedirect("index.jsp");
                }else
                {
                    response.sendRedirect("error.jsp");
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
                if(uri.equals(request.getContextPath() + "/cadastrarUsuario"))
                {
                    Cadastrar(request,response);
                }
                else if(uri.equals(request.getContextPath() + "/logarUsuario"))
                {
                    
                    String email = request.getParameter("email");
                    String[] dominio =email.split("@");
                    if(dominio[1].equals("admin.com"))
                    {
                        RequestDispatcher rd = request.getRequestDispatcher("/logarFuncionario");
                        rd.forward(request, response);
                    }else
                    {
                        logar(request,response);
                    }
                    
                    
                }else if(uri.equals(request.getContextPath() + "/alterarUsuario"))
                    {
                    if(request.getParameter("excluir")!=null)
                    {
                        excluirUsuario(request,response);
                    }
                    else{
                        alterarUsuario(request,response);
                    }
                }
                else if(uri.equals(request.getContextPath() + "/excluirUsuario"))
                {
                    excluirUsuario(request,response);
                }
                else
                {
                    response.sendRedirect("error.jsp");
                }

              }catch(Exception e){
                e.printStackTrace();
                response.sendRedirect("erro.jsp");

        }

    }
    
    
    public void Cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException
    {
        Usuario u = new Usuario();
        
        u.setNome(request.getParameter("nome"));
        u.setEmail(request.getParameter("email"));
        
        String senha = request.getParameter("senha");
        String confSenha = request.getParameter("confsenha");
        
        if(!confSenha.equals(senha))
        {
            RequestDispatcher rd = request.getRequestDispatcher("/cadastro.jsp");
            request.setAttribute("msg","Senhas não estão identicas");
            rd.forward(request, response);
        }
        else{
            try{
                   u.setSenha(Formatar.criptografar(senha));
               } catch (NoSuchAlgorithmException ex) {
                   Logger.getLogger(ControleUsuario.class.getName()).log(Level.SEVERE, null, ex);
               }

            u.setPerfil(PerfilDeAcesso.CLIENTE);
            
                UsuarioDAO dao = new UsuarioDAO();
                dao.cadastraNovoUsuario(u);
                RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                request.setAttribute("msg","Cadastrado com sucesso");
                rd.forward(request, response);
         }
        
    } 
    
    public void logar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException, SQLException
    {
        Usuario u = new Usuario();
        u.setEmail(request.getParameter("email"));
        
        try {
            u.setSenha(Formatar.criptografar(request.getParameter("senha")));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControleUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuarioAutenticado = dao.autenticaUsuario(u);
        
        if(usuarioAutenticado!= null)
        {
            HttpSession sessaoUsuario = request.getSession();
            sessaoUsuario.setAttribute("usuarioAutenticado",usuarioAutenticado);
            response.sendRedirect("index.jsp");
        }
        else
        {
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            request.setAttribute("msg","Email e/ou senha estao incorretas");
            rd.forward(request, response);
        }
    }
    
   public void preAlterar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException{
       HttpSession session = request.getSession();
       Usuario u = (Usuario) session.getAttribute("usuarioAutenticado");
       UsuarioDAO dao = new UsuarioDAO();
       Usuario usuario = dao.carregarPorId(u);
       
       u.setIdpessoa(usuario.getIdpessoa());
       session.setAttribute("usuarioAutenticado",u);
       
       RequestDispatcher rd = request.getRequestDispatcher("alterar.jsp");
       request.setAttribute("usu",usuario);
       rd.forward(request, response);
   }
   public void alterarUsuario(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException
   {
       Usuario u = new Usuario();
       u.setNome(request.getParameter("nome"));
       u.setEmail(request.getParameter("email"));
       
        HttpSession session = request.getSession();
        Usuario usu = (Usuario) session.getAttribute("usuarioAutenticado");
        
        u.setId(usu.getId());
        u.setIdpessoa(usu.getIdpessoa());
        
         
        UsuarioDAO dao = new UsuarioDAO();
        dao.alterarUsuario(u);
        
         RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
         request.setAttribute("msg","Alterado com sucesso!!!");
         rd.forward(request, response);
       
   }
   
   public void excluirUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException{
       HttpSession session = request.getSession();
       Usuario uSession = (Usuario) session.getAttribute("usuarioAutenticado");
       
       UsuarioDAO dao = new UsuarioDAO();
       Usuario usuario = dao.carregarPorId(uSession);
       usuario.setId(uSession.getId());
       
       dao.excluirUsuario(usuario);
       
       request.getSession().invalidate();
       
       RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
       request.setAttribute("msg","Bye Bye!!!");
       rd.forward(request, response);
       
   }
   
}
