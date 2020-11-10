/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AssinaturaDAO;
import model.Endereco;
import model.EnderecoDAO;
import model.Entrega;
import model.EntregaDAO;
import model.Estilo;
import model.EstiloDAO;
import model.Usuario;
import model.UsuarioDAO;
import util.EmailSender;

/**
 *
 * @author joaov
 */
@WebServlet(name = "ControleEntrega", urlPatterns = {"/ControleEntrega", "/CadastrarEndereco", "/PreAlterarEndereco", "/ExcluirEndereco", "/carregarEnvios", "/cadastrarCodigo", "/enviarEmail", "/enviarEmailParaTodos"})
public class ControleEntrega extends HttpServlet {

    boolean alertMessage = false;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();
            if (uri.equals(request.getContextPath() + "/PreAlterarEndereco")) {
                preAlterar(request, response);
            } else if (uri.equals(request.getContextPath() + "/ExcluirEndereco")) {
                excluirEndereco(request, response);
            } else if (uri.equals(request.getContextPath() + "/carregarEnvios")) {
                carregarEnvios(request, response);
            } else if (uri.equals(request.getContextPath() + "/cadastrarCodigo")) {
                cadastrarCodigo(request, response);
            } else if (uri.equals(request.getContextPath() + "/enviarEmailParaTodos")) {
                enviarEmailParaTodos(request, response);
            } else if (uri.equals(request.getContextPath() + "/enviarEmail")) {
                enviarEmail(request, response);
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
            if (uri.equals(request.getContextPath() + "/CadastrarEndereco")) {
                CadastrarEndereco(request, response);
            } else if (uri.equals(request.getContextPath() + "/AlterarEndereco")) {
                AlterarEndereco(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");

        }
    }

    public void CadastrarEndereco(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
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

    public void AlterarEndereco(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
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

    public void preAlterar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Endereco e = new Endereco();
        e.setId(Integer.parseInt(request.getParameter("idEndereco")));
        EnderecoDAO dao = new EnderecoDAO();

        e = dao.BuscarEndereco(e);
        RequestDispatcher rd = request.getRequestDispatcher("");
        request.setAttribute("end", e);
        rd.forward(request, response);
    }

    public void excluirEndereco(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Endereco e = new Endereco();
        e.setId(Integer.parseInt(request.getParameter("idEndereco")));

        EnderecoDAO dao = new EnderecoDAO();
        dao.ExcluirEndereco(e);
        request.getRequestDispatcher("").forward(request, response);
    }

    public void mostrarEndereco(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Endereco e = new Endereco();
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuarioAutenticado");
        e.setUsuario(u);

        EnderecoDAO dao = new EnderecoDAO();
        e = dao.BuscarEndereco(e);

    }

    private void carregarEnvios(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, SQLException, IOException {

        boolean isComum = (Boolean) request.getSession().getAttribute("isComum");
        AssinaturaDAO ass_dao = new AssinaturaDAO();
        List<Integer> IdsUsuariosAtivos = ass_dao.CarregarUsuariosAtivos();

        UsuarioDAO dao = new UsuarioDAO();
        EnderecoDAO end_dao = new EnderecoDAO();

        List<Usuario> usuarios = new ArrayList();
        for (int id : IdsUsuariosAtivos) {
            Usuario usuario = new Usuario();
            Endereco endereco = new Endereco();
            Entrega entrega = new Entrega();
            EntregaDAO dao_entrega = new EntregaDAO();
            usuario.setId(id);
            endereco = end_dao.BuscarEnderecoPorIdUsuario(usuario);
            usuario = dao.carregarPorId(usuario);
            usuario.setId(id);
            entrega = dao_entrega.CarregarPorIdUsuario(usuario);
            System.out.print("");

            if (entrega.getDataGeracao() != null) {
                String date = "" + entrega.getDataGeracao();
                java.sql.Date dat = java.sql.Date.valueOf(date);

                LocalDate localDate = dat.toLocalDate();
//                System.out.println("MONTH" + localDate.getMonthValue());
                Calendar now = Calendar.getInstance();
                int mesAtual = now.get(Calendar.MONTH) + 1;
//                System.out.println("MES ATUAL : " + mesAtual);

                //VERIFICACAO DA ATUALIDADE DO CODIGO DE RASTREIO
                if (localDate.getMonthValue() == mesAtual) {
                    usuario.setEntrega(entrega);
                }
            }
            usuario.setEndereco(endereco);
            usuarios.add(usuario);
        }

        request.setAttribute("alertMessage", alertMessage);
        alertMessage = false;
        request.setAttribute("isComum", isComum);
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("lista_envios.jsp").forward(request, response);

    }

    private void cadastrarCodigo(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        System.out.println("ID: " + request.getParameter("idUsuario"));
        System.out.println("CODIGO:" + request.getParameter("CodCorreio"));

        EntregaDAO dao = new EntregaDAO();
        Entrega entrega = new Entrega();

        EstiloDAO est_dao = new EstiloDAO();
        Estilo box = new Estilo();

        box = est_dao.carregarEstilodoMes();

        long millis = System.currentTimeMillis();
        java.sql.Date dataGeracao = new java.sql.Date(millis);

        Usuario usuario = new Usuario();
        UsuarioDAO usu_dao = new UsuarioDAO();

        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        usuario.setId(idUsuario);
        usuario = usu_dao.carregarPorId(usuario);
        usuario.setId(idUsuario);

        entrega.setBox(box);
        entrega.setCodigoRastreio(request.getParameter("CodCorreio"));
        entrega.setDataGeracao(dataGeracao);
        entrega.setUsuario(usuario);
        dao.Cadastrar(entrega);

        response.sendRedirect("/carregarEnvios");

    }

    private void enviarEmail(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, SQLException, IOException {
        if (request.getParameter("cod") == "") {
            alertMessage = true;
        } else {
            EmailSender emailSender = new EmailSender();
            String destinatario = request.getParameter("email");//mockado inicialmente
            String nome = request.getParameter("nome");
            String nome_formatado = nome.substring(0, 1).toUpperCase() + nome.substring(1);
            String mensagem = "Olá," + nome_formatado + "\nPode comemorar! Seu pedido ja foi enviado :) \n\n"
                    + "Código Correios:" + request.getParameter("cod") + "\n\n\n\n"
                    + "Atenciosamente equipe Lellegance,";
            String assunto = "Envio de Pedido";

            emailSender.EnviarEmail(destinatario, mensagem, assunto);
            System.out.println("EMAIL DE PEDIDO ENVIADO !");
            alertMessage = false;
        }

        response.sendRedirect("/carregarEnvios");

    }

//    VALIDAR NECESSIDADE COM O GRUPO!
    private void enviarEmailParaTodos(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
        boolean blockChainEmail = false;
        AssinaturaDAO ass_dao = new AssinaturaDAO();
        List<Integer> IdsUsuariosAtivos = ass_dao.CarregarUsuariosAtivos();

        UsuarioDAO dao = new UsuarioDAO();
        EnderecoDAO end_dao = new EnderecoDAO();

        List<Usuario> usuarios = new ArrayList();
        for (int id : IdsUsuariosAtivos) {
            Usuario usuario = new Usuario();
            Endereco endereco = new Endereco();
            Entrega entrega = new Entrega();
            EntregaDAO dao_entrega = new EntregaDAO();
            usuario.setId(id);
            endereco = end_dao.BuscarEnderecoPorIdUsuario(usuario);
            usuario = dao.carregarPorId(usuario);
            usuario.setId(id);
            entrega = dao_entrega.CarregarPorIdUsuario(usuario);
            System.out.print("");

            if (entrega.getDataGeracao() != null) {
                String date = "" + entrega.getDataGeracao();
                java.sql.Date dat = java.sql.Date.valueOf(date);

                LocalDate localDate = dat.toLocalDate();
//                System.out.println("MONTH" + localDate.getMonthValue());
                Calendar now = Calendar.getInstance();
                int mesAtual = now.get(Calendar.MONTH) + 1;
//                System.out.println("MES ATUAL : " + mesAtual);

                //VERIFICACAO DA ATUALIDADE DO CODIGO DE RASTREIO
                if (localDate.getMonthValue() == mesAtual) {
                    usuario.setEntrega(entrega);
                }
            }
            usuario.setEndereco(endereco);
            usuarios.add(usuario);
        }
        for (Usuario usuario : usuarios) {
            if (usuario.getEntrega() == null) {
                blockChainEmail = true;
                alertMessage = true;
            }
        }
        System.out.println("VALOR DO ALERT" + alertMessage);
        System.out.println("VALOR DO BLOCK" + blockChainEmail);

        if (blockChainEmail == false) {
            for (Usuario usuario : usuarios) {
                if (usuario.getEntrega() != null) {
                    System.out.print("CODIGO" + usuario.getEntrega().getCodigoRastreio());
                    System.out.print("nome" + usuario.getEmail());
                    System.out.print("EMAIL" + usuario.getNome());

                    EmailSender emailSender = new EmailSender();
                    String destinatario = usuario.getEmail();
                    String nome = usuario.getNome();
                    String nome_formatado = nome.substring(0, 1).toUpperCase() + nome.substring(1);
                    String mensagem = "Olá," + nome_formatado + "\nPode comemorar! Seu pedido ja foi enviado :) \n\n"
                            + "Código Correios:" + request.getParameter("cod") + "\n\n\n\n"
                            + "Atenciosamente equipe Lellegance,";
                    String assunto = "Envio de Pedido";

                    emailSender.EnviarEmail(destinatario, mensagem, assunto);
                    System.out.println("EMAIL DE PEDIDO ENVIADO ! A PARTIR DA CADEIDA DE EMAILS");
                    alertMessage = false;

                }
            }
        }
        response.sendRedirect("/carregarEnvios");
    }
}
