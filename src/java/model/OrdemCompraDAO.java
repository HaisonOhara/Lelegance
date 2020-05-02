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
 * @author Haison
 */
public class OrdemCompraDAO {

    private static final String CADASTRA_ORDEM_COMPRA = "INSERT INTO public.\"ordemCompra\"(\"idForncedor\", \"idBox\", \"qtdVendida\", \"status\", \"data\")VALUES ( ?, ?, ?, ?, ?);";

    public void cadastraNovoEstilo(OrdemCompra ordem_compra) throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(CADASTRA_ORDEM_COMPRA);
        comando.setInt(1, ordem_compra.getForncedor().getId());
        comando.setInt(2, ordem_compra.getEstilo().getId());
        comando.setInt(3, ordem_compra.getQuantidade_vendida());
        comando.setString(4,ordem_compra.getStatus()); 
        comando.setDate(5, (Date) ordem_compra.getData());
        comando.execute();
        con.close();

    }

}
