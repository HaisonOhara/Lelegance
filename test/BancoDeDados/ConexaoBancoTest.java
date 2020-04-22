/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BancoDeDados;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.ConectaBanco;

public class ConexaoBancoTest {

    public ConexaoBancoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @Test
    public void conectarBanco() {
        Connection conexao = null;
        conexao = ConectaBanco.getConexao();
        assertNotNull("Não foi possível estabelecer uma conexao com o banco de dados",conexao);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
