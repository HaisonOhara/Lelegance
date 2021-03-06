/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConectaBanco;
import model.EstiloDAO;

/**
 *
 * @author joaov
 */
public class AssinaturaDAO {

    private static final String CADASTRAR_ASSINATURA = "INSERT INTO assinatura(numero_meses,total,data_assinatura,estilo,usuario,status,valor_frete)"
            + "values(?,?,?,?,?,?,?);";

    private static final String CARREGAR_ASSINATURAS_ATIVAS = "SELECT * FROM public.assinatura WHERE status ='Ativa' ";

    private static final String CARREGAR_USUARIOS_ATIVOS = "SELECT DISTINCT  usuario FROM public.assinatura where status ='Ativa' ";

    private static final String DESATIVAR_ASSINATURA = "UPDATE public.assinatura\n"
            + "   SET  status='Inativa', data_cancelamento=?\n"
            + " WHERE usuario=?;";

    public void Assinar(Assinatura a) throws SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(CADASTRAR_ASSINATURA);
        comando.setInt(1, a.getNumeroMeses());
        comando.setDouble(2, a.getTotal());
        comando.setDate(3, new java.sql.Date(a.getData_assinatura().getTime()));
        comando.setInt(4, a.getEstilo().getId());
        comando.setInt(5, a.getUsuario().getId());
        comando.setString(6, a.getStatus());
        comando.setDouble(7, a.getvalorFrete());
        comando.execute();

    }

    public void desativarAssinaturaPorUsuario(Usuario usuario) throws SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(DESATIVAR_ASSINATURA);
        long millis = System.currentTimeMillis();
        java.sql.Date dataCancelamento = new java.sql.Date(millis);
        
        comando.setDate(1, dataCancelamento);
        comando.setInt(2, usuario.getId());
        comando.execute();
    }

    public List<Assinatura> CarregarAssinaturasAtivas() throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(CARREGAR_ASSINATURAS_ATIVAS);
        ResultSet resultado = comando.executeQuery();

        List<Assinatura> estilos = new ArrayList();

        while (resultado.next()) {
            //Recuperando objeto do Estilo Especifico
            int idEstilo = resultado.getInt("id");
            EstiloDAO estilodao = new EstiloDAO();
            Estilo estilo = new Estilo();
            estilo.setId(idEstilo);
            estilo = estilodao.carregarPorId(estilo);

            //Recupertando o Objeto do Usuario Especifico
            int IdUsuario = resultado.getInt("usuario");
            UsuarioDAO usuariodao = new UsuarioDAO();
            Usuario usuario = new Usuario();
            usuario.setId(IdUsuario);
            usuario = usuariodao.carregarPorId(usuario);
            usuario.setId(IdUsuario);
            //-------------------------------------------

            Assinatura ast = new Assinatura();

            ast.setTotal(resultado.getDouble("total"));
            ast.setData_assinatura(resultado.getDate("data_assinatura"));
            ast.setEstilo(estilo);
            ast.setUsuario(usuario);
            ast.setNumeroMeses(resultado.getInt("numero_meses"));
            ast.setvalorFrete(resultado.getDouble("valor_frete"));
            estilos.add(ast);
        }
        con.close();
        return estilos;
    }
    
    public String RelatorioGraficoPosivito() throws SQLException{
        String positivo = "[";
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(
                "select  mes,count(ass.*) as qtd \n" +
                "from  generate_series(1,12) as mes  left join assinatura as ass \n" +
                "on  mes = extract('month' from ass.data_assinatura) and extract('year' from ass.data_assinatura) = extract('year' from now())\n" +
                "group by mes,data_assinatura\n" +
                "order by 1"
        );
        ResultSet resultado = comando.executeQuery();
        
        while(resultado.next()){
            if(resultado.isLast()){
                positivo = positivo + resultado.getString("qtd")+"]";
            }
            else{
                    positivo = positivo + resultado.getString("qtd")+",";
            }
        }
        con.close();
        return positivo; 
    }
    
    public String RelatorioGraficoNegativo() throws SQLException{
        String negativo ="[";
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(
                "select  mes,count(ass.*) as qtd \n" +
                "from  generate_series(1,12) as mes  left join assinatura as ass \n" +
                "on  mes = extract('month' from ass.data_cancelamento) and extract('year' from ass.data_cancelamento) = extract('year' from now())\n" +
                "group by mes,data_assinatura\n" +
                "order by 1"
        );
       ResultSet resultado = comando.executeQuery();
        
        while(resultado.next()){
            if(resultado.isLast()){
                negativo = negativo + resultado.getString("qtd")+"]";
            }
            else{
                 negativo = negativo + resultado.getString("qtd")+",";
            }
        }
        con.close();
        return negativo;
    }

    public List<Integer> CarregarUsuariosAtivos() throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(CARREGAR_USUARIOS_ATIVOS);
        ResultSet resultado = comando.executeQuery();

        List<Integer> ids = new ArrayList();

        while (resultado.next()) {
            //Recuperando objeto do Estilo Especifico
            ids.add(resultado.getInt("usuario"));
        }
        con.close();
        return ids;
    }

}
