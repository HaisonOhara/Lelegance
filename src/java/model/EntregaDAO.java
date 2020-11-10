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
import util.ConectaBanco;

/**
 *
 * @author Usuario
 */
public class EntregaDAO {

    private static final String CADASTRAR_CODIGO = "INSERT INTO public.entrega(\n"
            + "             data_geracao, codigo_rastreio, usuario, box)\n"
            + "    VALUES ( ?, ?, ?, ?);";
    private static final String CARREGAR_PORID_USUARIO = "SELECT * FROM PUBLIC.ENTREGA WHERE usuario=?";

    public void Cadastrar(Entrega entrega) throws SQLException {
//       CRIA CLASSE ENTREGA
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(CADASTRAR_CODIGO);
        comando.setDate(1, (Date) entrega.getDataGeracao()); //FICAR ATENTO COM DATE
        comando.setString(2, entrega.getCodigoRastreio());
        comando.setInt(3, entrega.getUsuario().getId());
        comando.setInt(4, entrega.getBox().getId());
        comando.execute();
        con.close();

    }
    
       public Entrega CarregarPorIdUsuario(Usuario usuario) throws SQLException, ClassNotFoundException
   {
       Connection con = ConectaBanco.getConexao();
       PreparedStatement comando = con.prepareStatement(CARREGAR_PORID_USUARIO);
       comando.setInt(1,usuario.getId());
       ResultSet resultado = comando.executeQuery();
       Entrega entrega = new Entrega();
       EstiloDAO dao = new EstiloDAO();
       while(resultado.next())
       {
           Estilo box = new Estilo();
           box.setId(resultado.getInt("box"));
           dao.carregarPorId(box);
           entrega.setBox(box);  
           entrega.setCodigoRastreio(resultado.getString("codigo_rastreio"));
           entrega.setDataGeracao(resultado.getDate("data_geracao"));
           entrega.setId(resultado.getInt("id"));
           entrega.setUsuario(usuario);
       }
     return entrega;
   }
}
