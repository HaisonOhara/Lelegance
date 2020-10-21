package util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

public class EmailSender {

    
    public void EnviarEmailOrdemCompra(String destinario) {
        String meuEmail = "llegance.pfc@gmail.com";
        String minhaSenha = "senhaPFC159$";

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
        email.setSSLOnConnect(true);

        try {
            email.setFrom(meuEmail);
            email.setSubject("Relatorio de Ordem de Compra");
            email.setMsg("Olá , segue anexo relatório com ordem de Compra \n\n Att, \n\nEquipe Lelegance ");
            email.addTo(destinario);

            EmailAttachment[] anexos = new EmailAttachment[2];
            anexos[0] = new EmailAttachment();
            anexos[0].setPath("C:\\Users\\Usuario\\Downloads\\dotiros-2.jpg");
            anexos[0].setName("Dois_Delicia.jpg");
            email.attach(anexos[0]);

            anexos[1] = new EmailAttachment();
            anexos[1].setPath("C:\\Users\\Usuario\\Downloads\\Aula05-RNA_1.pdf");
            anexos[1].setName("AulaRNA.pdf");
            email.attach(anexos[1]);

            email.send();

            System.out.println("EMAIL ENVIADO :)");
        } catch (Exception e) {
            System.out.print(e.getMessage().toString());
        }

    }
}
