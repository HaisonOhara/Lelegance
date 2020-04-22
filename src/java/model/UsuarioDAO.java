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
import java.sql.Statement;
import util.ConectaBanco;
import model.Usuario;
import model.PerfilDeAcesso;

public class UsuarioDAO {

    private static final String CADASTRA_NOVA_PESSOA = "INSERT INTO pessoa(nome) VALUES (?);";
    
    private static final String CADASTRA_NOVO_USUARIO = "INSERT INTO usuario (email,senha,perfil,pessoa) VALUES (?,?,?,?)";

    private static final String AUTENTICA_USUARIO = "SELECT * FROM usuario WHERE email=? AND senha=?";
    
    private static final String EXCLUIR_USUARIO = "delete from usuario where id=?";
    
    private static final String EXCLUIR_PESSOA = "delete from pessoa where id=?";
    
    private static final String CARREGAR_POR_ID = "SELECT pessoa.nome,usuario.email,usuario.pessoa FROM pessoa,usuario where usuario.pessoa=pessoa.id AND usuario.id=?";
    
    private static final String ALTERAR_USUARIO = "UPDATE usuario set email=? where id=?";
    
    private static final String ALTERAR_PESSOA = "UPDATE public.pessoa SET  nome=? WHERE id=? ;";

    
    
    
    
    public void cadastraNovoUsuario(Usuario usuario) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(CADASTRA_NOVA_PESSOA,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, usuario.getNome());
            pstmt.execute();
            
            ResultSet key = pstmt.getGeneratedKeys();
            key.next();
            
            pstmt = conexao.prepareStatement(CADASTRA_NOVO_USUARIO);
            pstmt.setString(1, usuario.getEmail());
            pstmt.setString(2, usuario.getSenha());
            pstmt.setString(3,usuario.getPerfil().toString());
            pstmt.setInt(4,key.getInt(1));
            pstmt.execute();
            
        } catch (SQLException sqlErro) {
            throw new RuntimeException(sqlErro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        
        
        
        }
    }

    public Usuario autenticaUsuario(Usuario usuario) {
        Usuario usuarioAutenticado = null;
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rsUsuario = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(AUTENTICA_USUARIO);
            pstmt.setString(1, usuario.getEmail());
            pstmt.setString(2, usuario.getSenha());
            rsUsuario = pstmt.executeQuery();
            if (rsUsuario.next()) {
                usuarioAutenticado = new Usuario();
                usuarioAutenticado.setEmail(rsUsuario.getString("email"));
                usuarioAutenticado.setSenha(rsUsuario.getString("senha"));
                usuarioAutenticado.setId(rsUsuario.getInt("id"));
                usuarioAutenticado.setPerfil(PerfilDeAcesso.valueOf(rsUsuario.getString("perfil")));
            }
        } catch (SQLException sqlErro) {
            throw new RuntimeException(sqlErro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return usuarioAutenticado;
    }
    
   public void excluirUsuario(Usuario usuario) throws ClassNotFoundException, SQLException
    {
        Connection con = ConectaBanco.getConexao();
        
        PreparedStatement comando = con.prepareStatement(EXCLUIR_USUARIO);
        comando.setInt(1,usuario.getId());
        comando.execute();
        
         comando = con.prepareStatement(EXCLUIR_PESSOA);
        comando.setInt(1,usuario.getIdpessoa());
        comando.execute();
        
        con.close();
        
    }
    
     public Usuario carregarPorId(Usuario usuario) throws SQLException, ClassNotFoundException
    {
         Connection con = ConectaBanco.getConexao();
               PreparedStatement comando = con.prepareStatement(CARREGAR_POR_ID);
               comando.setInt(1,usuario.getId());
               ResultSet resultado = comando.executeQuery();
               
               Usuario u = new Usuario();
               
               while(resultado.next())
               {
                  u.setNome(resultado.getString("nome"));
                  u.setEmail(resultado.getString("email"));
                  u.setIdpessoa(resultado.getInt("pessoa"));
               }
               con.close();
       return u;         
    }
    public void alterarUsuario(Usuario usuario) throws ClassNotFoundException, SQLException
    {
        
         Connection con = ConectaBanco.getConexao();
         PreparedStatement comando = con.prepareStatement(ALTERAR_USUARIO);
         comando.setString(1,usuario.getEmail());
         comando.setInt(2, usuario.getId());
         comando.execute();
         
         comando = con.prepareStatement(ALTERAR_PESSOA);
         comando.setString(1,usuario.getNome());
         comando.setInt(2,usuario.getIdpessoa());
         comando.execute();
         
         con.close();
         
    }
    
    
    
    
}


