/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author joaov
 */
public class Formatar {
    
    public static String criptografar(String texto) throws NoSuchAlgorithmException{
        MessageDigest  m = MessageDigest.getInstance("MD5"); 
        m.update(texto.getBytes(),0,texto.length()); 
        BigInteger texto1= new BigInteger(1, m.digest());
        
        String senha = String.format("%1$032X", texto1);
        return senha;
    }
}
