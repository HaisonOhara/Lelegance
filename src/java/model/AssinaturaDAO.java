/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.ConectaBanco;

/**
 *
 * @author joaov
 */
public class AssinaturaDAO {
     private static final String CADASTRAR_ASSINATURA= "INSERT INTO assinatura(numero_meses,total,data_assinatura,estilo,usuario)"
            + "values(?,?,?,?,?);"; 
    
     
     public void Assinar(Assinatura a) throws SQLException
     {
         Connection con = ConectaBanco.getConexao();
         PreparedStatement comando = con.prepareStatement(CADASTRAR_ASSINATURA);
         comando.setInt(1,a.getNumeroMeses());
         comando.setDouble(2,a.getTotal());
         comando.setDate(3,  new java.sql.Date(a.getData_assinatura().getTime()));
         comando.setInt(4,a.getEstilo().getId());
         comando.setInt(5,a.getUsuario().getId());
         comando.execute();
         
     }
}
