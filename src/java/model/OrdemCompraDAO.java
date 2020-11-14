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
 * @author Haison
 */
public class OrdemCompraDAO {

    private static final String CADASTRA_ORDEM_COMPRA = "INSERT INTO public.\"ordemCompra\"(\"idfornecedor\", \"idbox\", \"qtdvendida\", \"status\", \"data\")VALUES ( ?, ?, ?, ?, ?);";
    private static final String CARREGAR_ORDEM_COMPRA_MENSAL = "select * from public.\"ordemCompra\" where  EXTRACT(MONTH FROM data) =EXTRACT(MONTH FROM CURRENT_DATE)";
    private static final String ALTERAR_ORDEM_COMPRA_STATUS = "UPDATE public.\"ordemCompra\"\n"
            + "   SET status=?\n"
            + " WHERE id=?";

    public void cadastraOrdemCompra(OrdemCompra ordem_compra) throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(CADASTRA_ORDEM_COMPRA);
        comando.setInt(1, ordem_compra.getFornecedor().getId());
        comando.setInt(2, ordem_compra.getEstilo().getId());
        comando.setInt(3, ordem_compra.getQuantidade_vendida());
        comando.setString(4, ordem_compra.getStatus());
        comando.setDate(5, (Date) ordem_compra.getData());
        comando.execute();
        con.close();

    }
    
        public void alterarOrdemCompraStatus(OrdemCompra ordem_compra, String status) throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(ALTERAR_ORDEM_COMPRA_STATUS);
        comando.setString(1, status);
        comando.setInt(2, ordem_compra.getId());
        comando.execute();
        con.close();

    }
    

    public OrdemCompra carregarOrdemCompradoMes() throws SQLException, ClassNotFoundException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement(CARREGAR_ORDEM_COMPRA_MENSAL);
        ResultSet resultado = comando.executeQuery();

        OrdemCompra ordem_compra = new OrdemCompra();
        EstiloDAO est_dao = new EstiloDAO();
        FornecedorDAO forn_dao = new FornecedorDAO();
        if (resultado.next()) {

            Estilo estilo = new Estilo();
            estilo.setId(resultado.getInt("idbox"));
            estilo = est_dao.carregarPorId(estilo);
            ordem_compra.setEstilo(estilo);

            ordem_compra.setId(resultado.getInt("id"));
            ordem_compra.setStatus(resultado.getString("status"));
            ordem_compra.setData(resultado.getDate("data"));
            ordem_compra.setQuantidade_vendida(resultado.getInt("qtdvendida"));

            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(resultado.getInt("idfornecedor"));
            fornecedor = forn_dao.carregarFornecedor(fornecedor);
            ordem_compra.setFornecedor(fornecedor);

        } else {
//            Setando menos 1 para indicar a inexistencia do pedido de compra, sen causar NULL POINTER EXCEPETION
            ordem_compra.setId(-1);
        }
        con.close();
        return ordem_compra;
    }

}
