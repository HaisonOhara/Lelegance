/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Estilo;
import model.EstiloDAO;
import model.Funcionario;
import model.FuncionarioDAO;
import static model.PerfilDeAcesso.ADMINISTRADOR;
import static model.PerfilDeAcesso.COMUM;
import util.Formatar;

/**
 *
 * @author sergi
 */
@WebServlet(name = "ControleFuncionario", urlPatterns = {"/ControleFuncionario", "/logarFuncionario", "/preAlterarFuncionario", "/alterarFuncionario", "/sairFuncionario", "/preAlterarFuncionarioPorId", "/carregarFuncionarios", "/excluirFuncionarioPorId","/promover"})
public class ControleFuncionario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String uri = request.getRequestURI();
            if (uri.equals(request.getContextPath() + "/preAlterarFuncionario")) {
                preAlterar(request, response);
            } else if (uri.equals(request.getContextPath() + "/preAlterarFuncionarioPorId")) {
                preAlterarPorId(request, response);
            } else if (uri.equals(request.getContextPath() + "/carregarFuncionarios")) {
                CarregarFuncionarios(request, response);
            } else if (uri.equals(request.getContextPath() + "/excluirFuncionarioPorId")) {
                excluirFuncionarioPorId(request, response);
            } else if (uri.equals(request.getContextPath() + "/promover")) {
                promover(request, response);
            }else if (uri.equals(request.getContextPath() + "/sairFuncionario")) {
                request.getSession().invalidate();
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String uri = request.getRequestURI();
            if (uri.equals(request.getContextPath() + "/logarFuncionario")) {
                logar(request, response);
            } else if (uri.equals(request.getContextPath() + "/alterarFuncionario")) {
                alterarFuncionario(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");

        }
    }

    public void logar(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, SQLException, ServletException, IOException, ClassNotFoundException {
        Funcionario f = new Funcionario();
        f.setEmail(request.getParameter("email"));
        f.setSenha(Formatar.criptografar(request.getParameter("senha")));

        FuncionarioDAO dao = new FuncionarioDAO();
        Funcionario fun = dao.autenticaFuncionario(f);
        if (fun != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioAutenticado", fun);
            if (fun.getPerfil().equals(ADMINISTRADOR)) {
                //SETANDO ATRIBUTO DE SESSAO PARA DIFERENCIAR O FUNCIONARIO COMUM
                // DO GERENTE.               
                session.setAttribute("isComum", false );
                CarregarFuncionarios(request, response);

            } else if (fun.getPerfil().equals(COMUM)) {
                session.setAttribute("isComum", true);
                CarregarFuncionarioComum(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            request.setAttribute("msg", "Email e/ou senha estao incorretas");
            rd.forward(request, response);
        }

        request.setAttribute("msg", "Funcionario Logado C/ sucesso!!!");

    }

    public void preAlterar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        HttpSession session = request.getSession();
        Funcionario f = (Funcionario) session.getAttribute("usuarioAutenticado");
        FuncionarioDAO dao = new FuncionarioDAO();
        Funcionario funcionario = dao.carregarPorId(f);

        f.setIdpessoa(funcionario.getIdpessoa());
        session.setAttribute("usuarioAutenticado", f);
        RequestDispatcher rd = null;

        if (f.getPerfil().equals(COMUM)) {
            rd = request.getRequestDispatcher("funcionario/alterar_func.jsp");
        } else if (f.getPerfil().equals(ADMINISTRADOR)) {
            rd = request.getRequestDispatcher("admin/alterar_gerente.jsp");
        }
        request.setAttribute("fun", funcionario);
        rd.forward(request, response);
    }

    public void preAlterarPorId(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Funcionario f = new Funcionario();
        f.setId(Integer.parseInt(request.getParameter("id")));
        FuncionarioDAO dao = new FuncionarioDAO();
        Funcionario funcionario = dao.carregarPorId(f);

        f.setIdpessoa(funcionario.getIdpessoa());
        RequestDispatcher rd = request.getRequestDispatcher("funcionario/alterar_func.jsp");

        request.setAttribute("fun", funcionario);
        rd.forward(request, response);
    }

    public void alterarFuncionario(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException, NoSuchAlgorithmException {
        Funcionario f = new Funcionario();
        f.setNome(request.getParameter("nome"));
        f.setEmail(request.getParameter("email"));

        HttpSession session = request.getSession();
        Funcionario fun = (Funcionario) session.getAttribute("usuarioAutenticado");

        f.setId(fun.getId());
        f.setIdpessoa(fun.getIdpessoa());

        FuncionarioDAO dao = new FuncionarioDAO();
        dao.alterarFuncionario(f);

        if (fun.getPerfil().equals(ADMINISTRADOR)) {
            CarregarFuncionarios(request, response);
        } else if (fun.getPerfil().equals(COMUM)) {
            CarregarFuncionarioComum(request, response);
        }

    }

    public void CarregarFuncionarios(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        Funcionario fun = (Funcionario) session.getAttribute("usuarioAutenticado");

        FuncionarioDAO dao = new FuncionarioDAO();

        List<Funcionario> Funcionarios;
        Funcionarios = (ArrayList<Funcionario>) dao.CarregarFuncionarios(fun);

        request.setAttribute("funcionarios", Funcionarios);

        request.getRequestDispatcher("admin/gerente.jsp").forward(request, response);

    }

    public void CarregarFuncionarioComum(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {

        boolean isComum = (Boolean) request.getSession().getAttribute("isComum");;
        EstiloDAO estilo_dao = new EstiloDAO();

        List<Estilo> estilos;
        estilos = (ArrayList<Estilo>) estilo_dao.CarregarEstilos();

        request.setAttribute("estilos", estilos);
        request.setAttribute("isComum", isComum);
        request.getRequestDispatcher("admin/lista_estilos.jsp").forward(request, response);
    }

    public void excluirFuncionarioPorId(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Funcionario f = new Funcionario();
        f.setId(Integer.parseInt(request.getParameter("id")));

        FuncionarioDAO dao = new FuncionarioDAO();
        Funcionario fun = dao.carregarPorId(f);
        fun.setId(f.getId());
        dao.excluirFuncionario(fun);
        
        CarregarFuncionarios(request, response);

    }

    private void promover(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Funcionario f = new Funcionario();
        f.setId(Integer.parseInt(request.getParameter("id")));
        
        FuncionarioDAO dao = new FuncionarioDAO();
        Funcionario fun = dao.carregarPorId(f);
        fun.setId(f.getId());
        dao.Promover(fun);
        
        CarregarFuncionarios(request, response);
        
    }
}
