/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ConectaBanco;

/**
 *
 * @author joaov
 */
public class CartaoDAO {
     private static final String CADASTRAR_CARTAO= "INSERT INTO cartao(nome_cartao,vencimento,numero,cnn,usuario)"
            + "values(?,?,?,?,?);"; 
    
    private static final String EXCLUIR_CARTAO = "DELETE FROM cartao WHERE id=?";
    
    private static final String ALTERAR_CARTAO = "UPDATE FROM cartao "
            + "SET nome_cartao=?, vencimento=?,numero=?, numero=?,cnn=?"
            + "WHERE id=?;";
    private static final String BUSCAR_CARTAO = "SELECT * from cartao where usuario = ? order by id desc limit 1";
    
    private static final String BUSCAR_CARTAO_POR_ID = "SELECT * from cartao where id = ?";
    
    
    public void Cadastrar(Cartao cartao) throws SQLException
   {
       Connection con = ConectaBanco.getConexao();
       PreparedStatement comando = con.prepareStatement(CADASTRAR_CARTAO);
       comando.setString(1,cartao.getNomeCartao());
       comando.setString(2,cartao.getVencimento());
       comando.setString(3,cartao.getNumero());
       comando.setInt(4,cartao.getCnn());
       comando.setInt(5,cartao.getUsuario().getId());
       comando.execute();
       con.close();
    
}
    
 public Cartao BuscarCartao(Cartao c) throws SQLException
   {
      Connection con = ConectaBanco.getConexao();
       PreparedStatement comando = con.prepareStatement(BUSCAR_CARTAO);
       comando.setInt(1,c.getUsuario().getId());
       ResultSet resultado = comando.executeQuery();
       Cartao cartao = new Cartao();
       while(resultado.next())
       {
           cartao.setCnn(resultado.getInt("cnn"));
           cartao.setNomeCartao(resultado.getString("nome_cartao"));
           cartao.setNumero(resultado.getString("numero"));
           cartao.setVencimento(resultado.getString("vencimento"));
           cartao.setId(resultado.getInt("id"));
       }
     return cartao;
   }
 
  public void AlterarCartao(Cartao cartao) throws SQLException
   {
       Connection con = ConectaBanco.getConexao();
       PreparedStatement comando = con.prepareStatement(ALTERAR_CARTAO);
       comando.setString(1,cartao.getNomeCartao());
       comando.setString(2,cartao.getVencimento());
       comando.setString(3,cartao.getNumero());
       comando.setInt(4,cartao.getCnn());
       comando.setInt(5,cartao.getId());
       comando.execute();
       con.close();
       
   }
  
  public void ExcluirCartao(Cartao cartao) throws SQLException
   {
       Connection con = ConectaBanco.getConexao();
       PreparedStatement comando = con.prepareStatement(EXCLUIR_CARTAO);
       comando.setInt(1,cartao.getId());
       comando.execute();
       con.close();
   }
 
   public Cartao BuscarPorId(Cartao c) throws SQLException
   {
       Connection con = ConectaBanco.getConexao();
       PreparedStatement comando = con.prepareStatement(BUSCAR_CARTAO_POR_ID);
       comando.setInt(1,c.getId());
       ResultSet resultado = comando.executeQuery();
       Cartao cartao = new Cartao();
       while(resultado.next())
       {
           cartao.setCnn(resultado.getInt("cnn"));
           cartao.setNomeCartao(resultado.getString("nome_cartao"));
           cartao.setNumero(resultado.getString("numero"));
           cartao.setVencimento(resultado.getString("vencimento"));
           cartao.setId(resultado.getInt("id"));
       }
     return cartao;
   }
    
}
