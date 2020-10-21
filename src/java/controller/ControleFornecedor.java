/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Estilo;
import model.EstiloDAO;
import model.Fornecedor;
import model.FornecedorDAO;
import model.Funcionario;

/**
 *
 * @author Haison Ohara
 */
@WebServlet(name = "ControleFornecedor", urlPatterns = {"/carregarFornecedores", "/AtivarFornecedor", "/abrirCadastroFornecedor", "/adicionarFornecedor", "/preAlterarFornecedor", "/alterarFornecedor"})
public class ControleFornecedor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();
            if (uri.equals(request.getContextPath() + "/carregarFornecedores")) {
                CarregarFornecedores(request, response);
            } else if (uri.equals(request.getContextPath() + "/AtivarFornecedor")) {
                AtivarFornecedor(request, response);
            } else if (uri.equals(request.getContextPath() + "/abrirCadastroFornecedor")) {
                request.getRequestDispatcher("admin/adicionar_fornecedor.jsp").forward(request, response);
            } else if (uri.equals(request.getContextPath() + "/preAlterarFornecedor")) {
                boolean isComum = (Boolean) request.getSession().getAttribute("isComum");;

                int idFornecedor = Integer.parseInt(request.getParameter("id"));
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(idFornecedor);
                FornecedorDAO dao = new FornecedorDAO();

                Fornecedor fornecedorCarregado = dao.carregarFornecedor(fornecedor);
                request.setAttribute("fornecedor", fornecedorCarregado);
                request.setAttribute("idFornecedor", idFornecedor);
                request.setAttribute("isComum", isComum);
                request.getRequestDispatcher("admin/editar_fornecedor.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String uri = request.getRequestURI();
            if (uri.equals(request.getContextPath() + "/adicionarFornecedor")) {
                AdicionarFornecedor(request, response);
            } else if (uri.equals(request.getContextPath() + "/alterarFornecedor")) {
                AlterarFornecedor(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");

        }

    }

    private void CarregarFornecedores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession();
        boolean isComum = (Boolean) request.getSession().getAttribute("isComum");;

        FornecedorDAO dao = new FornecedorDAO();
        List<Fornecedor> fornecedores;
        fornecedores = (ArrayList<Fornecedor>) dao.CarregarFornecedores();

        request.setAttribute("fornecedores", fornecedores);
        request.setAttribute("isComum", isComum);
        request.getRequestDispatcher("admin/lista_fornecedores.jsp").forward(request, response);
    }

    public void AtivarFornecedor(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(Integer.parseInt(request.getParameter("id")));

        FornecedorDAO dao = new FornecedorDAO();
        dao.AtivarFornecedor(fornecedor);
        response.sendRedirect("/carregarFornecedores");

    }

    private void AdicionarFornecedor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        HttpSession session = request.getSession();
        Funcionario funcLogado = (Funcionario) session.getAttribute("usuarioAutenticado");

        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setEmail(request.getParameter("emailFornecedor"));
        fornecedor.setNome(request.getParameter("nomeFornecedor"));
        fornecedor.setStatus("Inativo");

        FornecedorDAO dao = new FornecedorDAO();
        dao.CadastrarFornecedor(fornecedor);
        response.sendRedirect("/carregarFornecedores");

    }

    private void AlterarFornecedor(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession();
        int Fornecedorid = Integer.parseInt(request.getParameter("idFornecedor"));
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(Fornecedorid);
        fornecedor.setNome(request.getParameter("nomeFornecedor"));
        fornecedor.setEmail(request.getParameter("emailFornecedor"));
        System.out.println("OBJETO"+fornecedor.getEmail()+fornecedor.getNome()+fornecedor.getId());
        FornecedorDAO dao = new FornecedorDAO();
        dao.AlterarFornecedor(fornecedor);
        response.sendRedirect("/carregarFornecedores");
    }

}
