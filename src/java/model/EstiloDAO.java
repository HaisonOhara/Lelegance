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
public class EstiloDAO {
        private static final String BUSCA_TUDO = "Select * from estilo";
        private static final String BUSCA_ESPECIFICA = "Select * from estilo WHERE id=?";
        
        
        
        public List<Estilo> trazEstilos() throws SQLException
        {
               Connection con = ConectaBanco.getConexao();
               PreparedStatement comando = con.prepareStatement(BUSCA_TUDO);
               ResultSet resultado = comando.executeQuery();

               List<Estilo> estilos = new ArrayList(); 
               while(resultado.next())
               {
                  Estilo e = new Estilo();
                  e.setId(resultado.getInt("id"));
                  e.setDescricao(resultado.getString("descricao"));
                  e.setNome(resultado.getString("nome"));
                  e.setValor(resultado.getDouble("preco"));
                  Funcionario f = new Funcionario();
                  f.setId(resultado.getInt("funcionario"));
                  e.setFuncionario(f);
                  estilos.add(e);
               }
               con.close();
       return estilos;    
        }
    
    public Estilo buscaEstilos(Estilo estilo) throws SQLException
    {
         Connection con = ConectaBanco.getConexao();
         PreparedStatement comando = con.prepareStatement(BUSCA_ESPECIFICA);
         comando.setInt(1,estilo.getId());
         ResultSet resultado = comando.executeQuery();
         
         Estilo e = new Estilo();
           while(resultado.next())
               {
                  e.setId(resultado.getInt("id"));
                  e.setDescricao(resultado.getString("descricao"));
                  e.setNome(resultado.getString("nome"));
                  e.setValor(resultado.getDouble("preco"));
                  Funcionario f = new Funcionario();
                  f.setId(resultado.getInt("funcionario"));
                  e.setFuncionario(f);
                  
               }
               con.close();
        return e;
    }
}
