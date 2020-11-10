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
import java.util.ArrayList;
import java.util.List;
import util.ConectaBanco;

/**
 *
 * @author joaov
 */
public class EnderecoDAO {
    private static final String CADASTRAR_ENDERECO = "INSERT INTO endereco(cidade,estado,cep,complemento,numero,logradouro,usuario)"
            + "values(?,?,?,?,?,?,?);"; 
    
    private static final String EXCLUIR_ENDERECO = "DELETE FROM endereco WHERE id=?";
    
    private static final String ALTERAR_ENDERECO = "UPDATE FROM endereco "
            + "SET cidade=?, estado=?, cep=?, complemento=?,numero=?,endereco=? "
            + "WHERE id=?;";
    
    private static final String CARREGAR_TODOS = "SELECT * FROM endereco WHERE usuario = ?;";
    private static final String BUSCAR_ENDERECOPORID = "SELECT * from endereco where id = ?";
    private static final String BUSCAR_ENDERECOPORID_USUARIO = "SELECT * from endereco where usuario = ?";
    private static final String BUSCAR_ENDERECO = "SELECT * from endereco where usuario = ? order by id desc limit 1";
    
   public void Cadastrar(Endereco endereco) throws SQLException
   {
       Connection con = ConectaBanco.getConexao();
       PreparedStatement comando = con.prepareStatement(CADASTRAR_ENDERECO);
       comando.setString(1,endereco.getCidade());
       comando.setString(2,endereco.getEstado());
       comando.setString(3,endereco.getCep());
       comando.setString(4,endereco.getComplemento());
       comando.setInt(5,endereco.getNumero());
       comando.setString(6,endereco.getEndereco());
       comando.setInt(7,endereco.getUsuario().getId());
       comando.execute();
       con.close();
       
        
   }
   public Endereco BuscarEnderecoPorId(Endereco e) throws SQLException
   {
       Connection con = ConectaBanco.getConexao();
       PreparedStatement comando = con.prepareStatement(BUSCAR_ENDERECOPORID);
       comando.setInt(1,e.getId());
       ResultSet resultado = comando.executeQuery();
       Endereco endereco = new Endereco();
       while(resultado.next())
       {
           endereco.setId(resultado.getInt("id"));
           endereco.setCep(resultado.getString("cep"));
           endereco.setCidade(resultado.getString("cidade"));
           endereco.setEstado(resultado.getString("estado"));
           endereco.setEndereco(resultado.getString("endereco"));
           endereco.setNumero(resultado.getInt("numero"));
           endereco.setComplemento(resultado.getString("complemento"));
       }
     return endereco;
   }
   
      public Endereco BuscarEnderecoPorIdUsuario(Usuario u) throws SQLException
   {
       Connection con = ConectaBanco.getConexao();
       PreparedStatement comando = con.prepareStatement(BUSCAR_ENDERECOPORID_USUARIO);
       comando.setInt(1,u.getId());
       ResultSet resultado = comando.executeQuery();
       Endereco endereco = new Endereco();
       while(resultado.next())
       {
           endereco.setId(resultado.getInt("id"));
           endereco.setCep(resultado.getString("cep"));
           endereco.setCidade(resultado.getString("cidade"));
           endereco.setEstado(resultado.getString("estado"));
           endereco.setEndereco(resultado.getString("logradouro"));
           endereco.setNumero(resultado.getInt("numero"));
           endereco.setComplemento(resultado.getString("complemento"));
       }
     return endereco;
   }
   public Endereco BuscarEndereco(Endereco e) throws SQLException
   {
       Connection con = ConectaBanco.getConexao();
       PreparedStatement comando = con.prepareStatement(BUSCAR_ENDERECO);
       comando.setInt(1,e.getUsuario().getId());
       ResultSet resultado = comando.executeQuery();
       Endereco endereco = new Endereco();
       while(resultado.next())
       {
           endereco.setId(resultado.getInt("id"));
           endereco.setCep(resultado.getString("cep"));
           endereco.setCidade(resultado.getString("cidade"));
           endereco.setEstado(resultado.getString("estado"));
           endereco.setEndereco(resultado.getString("logradouro"));
           endereco.setNumero(resultado.getInt("numero"));
           endereco.setComplemento(resultado.getString("complemento"));
       }
     return endereco;
   }
   
   public void ExcluirEndereco(Endereco endereco) throws SQLException
   {
       Connection con = ConectaBanco.getConexao();
       PreparedStatement comando = con.prepareStatement(EXCLUIR_ENDERECO);
       comando.setInt(1,endereco.getId());
       comando.execute();
       con.close();
   }
   
   public void AlterarEndereco(Endereco endereco) throws SQLException
   {
       Connection con = ConectaBanco.getConexao();
       PreparedStatement comando = con.prepareStatement(ALTERAR_ENDERECO);
       comando.setString(1,endereco.getCidade());
       comando.setString(2,endereco.getEstado());
       comando.setString(3,endereco.getCep());
       comando.setString(4,endereco.getComplemento());
       comando.setInt(5,endereco.getNumero());
       comando.setString(6,endereco.getEndereco());
       comando.setInt(7,endereco.getId());
       comando.execute();
       con.close();
       
   }
   
   public List<Endereco> CarregarTodosEndereco(Usuario usuario) throws SQLException
   {
       
       Connection con = ConectaBanco.getConexao();
       PreparedStatement comando = con.prepareStatement(CARREGAR_TODOS);
       comando.setInt(1,usuario.getId());
       ResultSet resultado = comando.executeQuery();
       
       List<Endereco> enderecos = new ArrayList();
       Endereco endereco = new Endereco();
       while(resultado.next())
       {
           endereco.setId(resultado.getInt("id"));
           endereco.setCep(resultado.getString("cep"));
           endereco.setCidade(resultado.getString("cidade"));
           endereco.setEstado(resultado.getString("estado"));
           endereco.setEndereco(resultado.getString("endereco"));
           endereco.setNumero(resultado.getInt("numero"));
           endereco.setComplemento(resultado.getString("complemento"));
           enderecos.add(endereco);
       }
       con.close();
       
       return enderecos;
   } 
}
