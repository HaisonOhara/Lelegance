package util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CorreiosService {
    
    private String destinationCEP;

    public CorreiosService(String originCEP) {
        this.destinationCEP = originCEP;
    }
    
    public  double getShippingValue() throws ParseException, IOException, SAXException, ParserConfigurationException {
        String value = "";
        try {

            URL url = new URL("http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?"
                    + "nCdEmpresa=08082650"
                    + "&sDsSenha=564321"
                    + "&nCdServico=04510"
                    + "&sCepOrigem=51020280"
                    + "&sCepDestino="+destinationCEP
                    + "&nVlPeso=1"
                    + "&nCdFormato=1"
                    + "&nVlComprimento=20"
                    + "&nVlAltura=20"
                    + "&nVlLargura=20"
                    + "&nVlDiametro=0"
                    + "&sCdMaoPropria=m"
                    + "&nVlValorDeclarado=0"
                    + "&sCdAvisoRecebimento=n"
                    + "&StrRetorno=xml"
                    + "&nIndicaCalculo=3"
            );
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                value = output;
            }

            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }

        //String value = "<Servicos><cServico><Codigo>04510</Codigo><Valor>44,70</Valor><PrazoEntrega>24</PrazoEntrega><ValorSemAdicionais>44,70</ValorSemAdicionais><ValorMaoPropria>0,00</ValorMaoPropria><ValorAvisoRecebimento>0,00</ValorAvisoRecebimento><ValorValorDeclarado>0,00</ValorValorDeclarado><EntregaDomiciliar>S</EntregaDomiciliar><EntregaSabado>N</EntregaSabado><obsFim></obsFim><Erro>0</Erro><MsgErro></MsgErro></cServico></Servicos>\n";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(value)));
        Element rootElement = document.getDocumentElement();

        String requestQueueName = getString("Valor", rootElement);
        requestQueueName = requestQueueName.replace(",", ".");
        double valueConverted = Double.parseDouble(requestQueueName);
        System.out.println(valueConverted);
        return valueConverted;

    }

    static protected String getString(String tagName, Element element) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list != null && list.getLength() > 0) {
            NodeList subList = list.item(0).getChildNodes();

            if (subList != null && subList.getLength() > 0) {
                return subList.item(0).getNodeValue();
            }
        }
        return null;
    }
}
