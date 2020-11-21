package controller;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Estilo;
import model.EstiloDAO;
import model.Funcionario;
import model.FuncionarioDAO;
import static model.PerfilDeAcesso.ADMINISTRADOR;
import static model.PerfilDeAcesso.COMUM;
import model.Usuario;
import util.Formatar;

@WebServlet(name = "ControleEstilo", urlPatterns = {"/abrirCadastroEstilo", "/buscaEstilo", "/todosOsEstilos", "/adicionarEstilo", "/carregarEstilos", "/excluirEstiloPorId", "/preAlterarEstilo", "/alterarEstilo", "/AtivarBox"})
@MultipartConfig
public class ControleEstilo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String uri = request.getRequestURI();
            if (uri.equals(request.getContextPath() + "/preAlterarEstilo")) {
                boolean isComum = (Boolean) request.getSession().getAttribute("isComum");;

                int idEstilo = Integer.parseInt(request.getParameter("id"));
                Estilo estilo = new Estilo();
                estilo.setId(idEstilo);
                EstiloDAO dao = new EstiloDAO();

                Estilo estiloCarregado = dao.carregarPorId(estilo);
                request.setAttribute("estilo", estiloCarregado);
                request.setAttribute("idEstilo", idEstilo);
                request.setAttribute("isComum", isComum);
                request.getRequestDispatcher("admin/editar_estilo.jsp").forward(request, response);

            } else if (uri.equals(request.getContextPath() + "/preAlterarFuncionarioPorId")) {
                preAlterarPorId(request, response);
            } else if (uri.equals(request.getContextPath() + "/carregarEstilos")) {
                CarregarEstilos(request, response);
            } else if (uri.equals(request.getContextPath() + "/abrirCadastroEstilo")) {

                request.getRequestDispatcher("admin/adicionar_estilo.jsp").forward(request, response);
            } else if (uri.equals(request.getContextPath() + "/buscaEstilo")) {
                buscaEstilo(request, response);
            } else if (uri.equals(request.getContextPath() + "/todosOsEstilos")) {
                todosEstilos(request, response);
            } else if (uri.equals(request.getContextPath() + "/excluirEstiloPorId")) {
                excluirEstiloPorId(request, response);
            } else if (uri.equals(request.getContextPath() + "/AtivarBox")) {
                AtivarBox(request, response);
            } else if (uri.equals(request.getContextPath() + "/sairFuncionario")) {
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
            if (uri.equals(request.getContextPath() + "/adicionarEstilo")) {
                adicionarEstilo(request, response);
            } else if (uri.equals(request.getContextPath() + "/alterarEstilo")) {
                AlterarEstilo(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");

        }
    }

    public void adicionarEstilo(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, SQLException, ServletException, IOException, ClassNotFoundException {
        HttpSession session = request.getSession();
        Funcionario funcLogado = (Funcionario) session.getAttribute("usuarioAutenticado");

        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

//        final PrintWriter writer = response.getWriter();
        final String path = "C:\\Lellegance\\Lelegance\\web\\imagens_estilos\\masculino";

        saveImage(filePart, fileName, path);

        System.out.println("file Path: " + path + "\\" + fileName);
        Estilo estilo = new Estilo();
        estilo.setDescricao(request.getParameter("descricao"));
        estilo.setConteudo(request.getParameter("conteudo"));
        estilo.setImagem("imagens_estilos\\masculino\\" + fileName);
        estilo.setNome(request.getParameter("nomeEstilo"));
        estilo.setValor(Double.parseDouble(request.getParameter("valor")));
        System.out.println("CONTEUDO DO OBJETO:" + estilo.getConteudo() + estilo.getDescricao() + estilo.getNome());
        EstiloDAO dao = new EstiloDAO();

        //desativa o atual
        Estilo atual = dao.carregarEstilodoMes();
        dao.excluirEstilo(atual);

        dao.cadastraNovoEstilo(estilo, funcLogado.getId());

        response.sendRedirect("/carregarEstilos");
    }

    public void preAlterar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
    }

    public void preAlterarPorId(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
    }

    ;
  
   public void AlterarEstilo(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        Funcionario funcLogado = (Funcionario) session.getAttribute("usuarioAutenticado");

        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        final String path = "C:\\Lellegance\\Lelegance\\web\\imagens_estilos\\masculino";

        saveImage(filePart, fileName, path);
        
        int Estiloid = Integer.parseInt(request.getParameter("idEstilo"));

        Estilo estilo = new Estilo();
        estilo.setDescricao(request.getParameter("descricao"));
        estilo.setId(Estiloid);
        estilo.setNome(request.getParameter("nomeEstilo"));
        estilo.setImagem("imagens_estilos\\masculino\\" + fileName);
        estilo.setValor(Double.parseDouble(request.getParameter("valor")));
        estilo.setConteudo(request.getParameter("conteudo"));

        EstiloDAO dao = new EstiloDAO();
        dao.AlterarNovoEstilo(estilo, funcLogado.getId());
        response.sendRedirect("/carregarEstilos");
//        System.out.println("LOGOU NO ALTERAR !!!!!!!!!+ID:"+id);

    }

    public void CarregarEstilos(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
//        Funcionario fun = (Funcionario) session.getAttribute("usuarioAutenticado");
        boolean isComum = (Boolean) request.getSession().getAttribute("isComum");;

        EstiloDAO dao = new EstiloDAO();

        List<Estilo> estilos;
        estilos = (ArrayList<Estilo>) dao.CarregarEstilos();

        request.setAttribute("estilos", estilos);
        request.setAttribute("isComum", isComum);
        request.getRequestDispatcher("admin/lista_estilos.jsp").forward(request, response);

    }

    public void excluirEstiloPorId(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {

        Estilo est = new Estilo();
        est.setId(Integer.parseInt(request.getParameter("id")));

        EstiloDAO dao = new EstiloDAO();
        Estilo est_a_excluir = dao.carregarPorId(est);

        est_a_excluir.setId(est.getId());
        dao.excluirEstilo(est_a_excluir);
        response.sendRedirect("/carregarEstilos");
        /*Resolve o bug da url ficar com excluirEstiloPorId?id 
        isso impede que o usuario visualize um item ja excluido 
        caso clique pára voltar no Browser :)*/
    }

    public void AtivarBox(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {

        Estilo est = new Estilo();
        est.setId(Integer.parseInt(request.getParameter("id")));

        EstiloDAO dao = new EstiloDAO();
        Estilo est_a_alterar = dao.carregarPorId(est);

        //desativa o atual
        Estilo atual = dao.carregarEstilodoMes();
        dao.excluirEstilo(atual);

        est_a_alterar.setId(est.getId());
        dao.ativarBox(est_a_alterar);

        response.sendRedirect("/carregarEstilos");
        /*Resolve o bug da url ficar com excluirEstiloPorId?id 
        isso impede que o usuario visualize um item ja excluido 
        caso clique pára voltar no Browser :)*/
    }

    public void todosEstilos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        EstiloDAO dao = new EstiloDAO();
        List<Estilo> estilos = dao.CarregarEstilos();
        request.setAttribute("Estilos", estilos);

        request.getRequestDispatcher("boxes_visao_cliente.jsp").forward(request, response);
    }

    public void buscaEstilo(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuarioAutenticado");
        System.out.print("Usuario" + u);
        Estilo e = new Estilo();
        e.setId(Integer.parseInt(request.getParameter("id")));

        EstiloDAO dao = new EstiloDAO();
        e = dao.carregarPorId(e);

        request.setAttribute("estilo", e);
        request.setAttribute("usuario", u);
        request.getRequestDispatcher("box_visao_cliente.jsp").forward(request, response);

    }

    public void saveImage(Part filePart, String fileName, String path) throws IOException {

        OutputStream out = null;
        InputStream filecontent = null;
        out = new FileOutputStream(new File(path + File.separator
                + fileName));
        filecontent = filePart.getInputStream();

        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = filecontent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.close();
        filecontent.close();
    }

}
