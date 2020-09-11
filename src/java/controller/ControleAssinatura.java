/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import static com.oracle.wls.shaded.org.apache.xalan.lib.ExsltDatetime.date;
import util.CorreiosService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import model.Assinatura;
import model.AssinaturaDAO;
import model.Cartao;
import model.CartaoDAO;
import model.Endereco;
import model.EnderecoDAO;
import model.Estilo;
import model.EstiloDAO;
import model.Usuario;
import org.xml.sax.SAXException;

/**
 *
 * @author joaov
 */
@WebServlet(name = "ControleAssinatura", urlPatterns = {"/ControleAssinatura", "/preAssinar", "/confereTudo", "/assinar"})
public class ControleAssinatura extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();
            if (uri.equals(request.getContextPath() + "/preAssinar")) {
                preAssinar(request, response);
            } else if (uri.equals(request.getContextPath() + "/assinar")) {
                Assinar(request, response);
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
            if (uri.equals(request.getContextPath() + "/preAssinar")) {
                preAssinar(request, response);
            } else if (uri.equals(request.getContextPath() + "/confereTudo")) {
                confereTudo(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }

    }

    private void preAssinar(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException {
        Estilo e = new Estilo();

        int idEstilo = Integer.parseInt(request.getParameter("id"));
        Estilo estilo = new Estilo();
        estilo.setId(idEstilo);
        EstiloDAO dao = new EstiloDAO();
        Estilo estiloCarregado = dao.carregarPorId(estilo);

        Assinatura a = new Assinatura();
        
      
        a.setTamanhoCamiseta(request.getParameter("MedidaCamiseta"));
        a.setTamanhoCalca(Integer.parseInt(request.getParameter("MedidaCalca")));
        a.setTamanhoCalcado(Integer.parseInt(request.getParameter("MedidaCalcado")));
        
        a.setEstilo(estiloCarregado);
        a.setNumeroMeses(Integer.parseInt(request.getParameter("plano")));
//        a.setvalorFrete(30.00);//Valor fixo por enquanto--Precisa ser implementado consumo do wevService do correios

        HttpSession session = request.getSession();
        session.setAttribute("preAssinatura", a);
        response.sendRedirect("CadastrarEndereco.jsp");

    }

    private void confereTudo(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException, ParseException, SAXException, ParserConfigurationException {
        Cartao c = new Cartao();
        Endereco e = new Endereco();

        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuarioAutenticado");

        c.setUsuario(u);
        CartaoDAO dao = new CartaoDAO();
        c = dao.BuscarCartao(c);

        e.setUsuario(u);
        EnderecoDAO dao2 = new EnderecoDAO();
        e = dao2.BuscarEndereco(e);
        CorreiosService service= new CorreiosService(e.getCep());
        double valorFrete= service.getShippingValue();

        Assinatura a = (Assinatura) session.getAttribute("preAssinatura");

        Estilo es = new Estilo();
        es.setId(a.getEstilo().getId());
        EstiloDAO dao3 = new EstiloDAO();

        es = dao3.carregarPorId(es);

        a.setTotal(a.getNumeroMeses() * es.getValor()+a.getvalorFrete());
        a.setvalorFrete(valorFrete);
        session.setAttribute("preAssinatura", a);

        request.setAttribute("cartao", c);
        request.setAttribute("endereco", e);
        request.setAttribute("estilo", es);
        request.setAttribute("assi", a);

        request.getRequestDispatcher("confere.jsp").forward(request, response);

    }

    private void Assinar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        Assinatura a = (Assinatura) session.getAttribute("preAssinatura");
        Usuario u = (Usuario) session.getAttribute("usuarioAutenticado");
        a.setData_assinatura(new Date());
        a.setUsuario(u);
        a.setStatus("Ativa");
        AssinaturaDAO dao = new AssinaturaDAO();
        dao.Assinar(a);

        request.getRequestDispatcher("").forward(request, response);

    }

}
