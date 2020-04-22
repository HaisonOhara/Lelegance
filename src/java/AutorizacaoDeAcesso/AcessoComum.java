/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutorizacaoDeAcesso;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Funcionario;
import model.PerfilDeAcesso;
import model.Usuario;

public class AcessoComum implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        //recuperar sessao
        HttpSession sessaoUsuario = ((HttpServletRequest) request).getSession();
        Funcionario funcionario = (Funcionario) sessaoUsuario.getAttribute("usuarioAutenticado");

        if (funcionario != null && funcionario.getPerfil().equals(PerfilDeAcesso.COMUM) || funcionario.getPerfil().equals(PerfilDeAcesso.ADMINISTRADOR) ) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("../acessoNegado.jsp");

        }
    }

    @Override
    public void destroy() {

    }
}
