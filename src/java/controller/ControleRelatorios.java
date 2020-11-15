/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import util.ConectaBanco;

/**
 *
 * @author joaov
 */
@WebServlet(name = "Relatorios", urlPatterns = {"/Relatorios","/gerarRelatorio"})
public class ControleRelatorios extends HttpServlet { 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String uri = request.getRequestURI();
            if (uri.equals(request.getContextPath() + "/gerarRelatorio")) {
                GerarRelatorio(request, response);
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
    }

    private void GerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String erro ="";
        String jasper = "reports/fornecedores.jasper";
        
        HashMap<String,Object> params = new HashMap<String, Object>();
        
        byte[] bytes = null;
        
        ServletContext contexto = getServletContext();
        
        try {
            JasperReport rel = (JasperReport) JRLoader.loadObjectFromFile(contexto.getRealPath(jasper));
            
            bytes = JasperRunManager.runReportToPdf(rel, params,ConectaBanco.getConexao());
        } catch (JRException ex) {
            erro = ex.getMessage();
        } finally{
           if(bytes != null){
               response.setContentType("Application/pdf");
               response.setContentLength(bytes.length);
               ServletOutputStream sos = response.getOutputStream();
               sos.write(bytes);
               sos.flush();
               sos.close();
           }else{
               RequestDispatcher rd  = request.getRequestDispatcher("Index.jsp");
               rd.forward(request, response);
           }
        }
        
    }


}
