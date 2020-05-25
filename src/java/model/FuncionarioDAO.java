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
 * @author sergi
 */
public class FuncionarioDAO {

    private static final String PROMOVER = "UPDATE funcionario set perfil='ADMINISTRADOR' where id=?;";
    private static final String ALTERAR_FUNCIONARIO = "UPDATE funcionario set email=? where id=? ";
    private static final String ALTERAR_PESSOA = "UPDATE public.pessoa SET  nome=? WHERE id=? ;";
    private static final String EXCLUIR_FUNCIONARIO = "UPDATE funcionario SET status = 'Inativo' where id=?";
    private static final String EXCLUIR_PESSOA = "DELETE FROM pessoa where id=?";
    private static final String AUTENTICA_FUNCIONARIO = "SELECT * from funcionario where email=? and senha=? ";
    private static final String CARREGAR_POR_ID = "SELECT pessoa.nome,funcionario.email,funcionario.pessoa FROM pessoa,funcionario where funcionario.pessoa=pessoa.id AND funcionario.id=?";
    private static final String CARREGAR_TODOS = "select pessoa.nome,funcionario.email,funcionario.id,funcionario.perfil from pessoa,funcionario where funcionario.pessoa=pessoa.id and funcionario.id!=? and status='Ativo'";

    public Funcionario autenticaFuncionario(Funcionario funcionario) throws SQLException {
        Funcionario funcionarioAutenticado = null;
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(AUTENTICA_FUNCIONARIO);
        comando.setString(1, funcionario.getEmail());
        comando.setString(2, funcionario.getSenha());
        ResultSet resultado = comando.executeQuery();

        if (resultado.next()) {
            funcionarioAutenticado = new Funcionario();
            funcionarioAutenticado.setId(resultado.getInt("id"));
            funcionarioAutenticado.setPerfil(PerfilDeAcesso.valueOf(resultado.getString("perfil")));
        }

        return funcionarioAutenticado;
    }

    public Funcionario carregarPorId(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(CARREGAR_POR_ID);
        comando.setInt(1, funcionario.getId());
        ResultSet resultado = comando.executeQuery();

        Funcionario f = new Funcionario();

        while (resultado.next()) {
            f.setNome(resultado.getString("nome"));
            f.setEmail(resultado.getString("email"));
            f.setIdpessoa(resultado.getInt("pessoa"));
        }
        con.close();
        return f;
    }

    public void alterarFuncionario(Funcionario funcionario) throws ClassNotFoundException, SQLException {

        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(ALTERAR_FUNCIONARIO);
        comando.setString(1, funcionario.getEmail());
        comando.setInt(2, funcionario.getId());
        comando.execute();

        comando = con.prepareStatement(ALTERAR_PESSOA);
        comando.setString(1, funcionario.getNome());
        comando.setInt(2, funcionario.getIdpessoa());
        comando.execute();

        con.close();

    }

    public List<Funcionario> CarregarFuncionarios(Funcionario fun) throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(CARREGAR_TODOS);
        comando.setInt(1, fun.getId());
        ResultSet resultado = comando.executeQuery();

        List<Funcionario> funcionarios = new ArrayList();
        
        while (resultado.next()) {
            Funcionario f = new Funcionario();
            f.setId(resultado.getInt("id"));
            f.setNome(resultado.getString("nome"));
            f.setEmail(resultado.getString("email"));
            
                  if("COMUM".equalsIgnoreCase(resultado.getString("perfil"))){
                     f.setPerfil(PerfilDeAcesso.COMUM);
                }else{
                      f.setPerfil(PerfilDeAcesso.ADMINISTRADOR);
                  }
              funcionarios.add(f);
            }
            
        con.close();
        return funcionarios;
    }

    public void excluirFuncionario(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(EXCLUIR_FUNCIONARIO);
        comando.setInt(1, funcionario.getId());
        comando.execute();
        
        con.close();

    }
    
     public void Promover(Funcionario funcionario) throws SQLException
     {
         Connection con = ConectaBanco.getConexao();
         
         PreparedStatement comando = con.prepareStatement(PROMOVER);
         comando.setInt(1, funcionario.getId());
         comando.execute();
         
         con.close();
     }
    
}
