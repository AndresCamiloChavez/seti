package com.example.prueba_seti.service;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.example.prueba_seti.dto.PedidoRequest;
import com.example.prueba_seti.dto.PedidoResponse;

@Service
public class PedidoService {

    public PedidoResponse procesarPedido(PedidoRequest pedido) {
        System.out.println(">> Pedido recibido: " + pedido);

        String xmlRequest = construirXmlDesdeJson(pedido);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        HttpEntity<String> entity = new HttpEntity<>(xmlRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://run.mocky.io/v3/50cc099f-c6ea-499c-8ad3-5e5f3c6b06f4", entity, String.class);

        return extraerJsonDesdeXml(response.getBody());
    }

    private String construirXmlDesdeJson(PedidoRequest p) {
        return String.format(
                """
                        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:env="http://WSDLs/EnvioPedidos/EnvioPedidosAcme">
                           <soapenv:Header/>
                           <soapenv:Body>
                              <env:EnvioPedidoAcme>
                                 <EnvioPedidoRequest>
                                    <pedido>%s</pedido>
                                    <Cantidad>%s</Cantidad>
                                    <EAN>%s</EAN>
                                    <Producto>%s</Producto>
                                    <Cedula>%s</Cedula>
                                    <Direccion>%s</Direccion>
                                 </EnvioPedidoRequest>
                              </env:EnvioPedidoAcme>
                           </soapenv:Body>
                        </soapenv:Envelope>
                        """,
                p.getNumPedido(), p.getCantidadPedido(), p.getCodigoEAN(), p.getNombreProducto(),
                p.getNumDocumento(), p.getDireccion());
    }

    private PedidoResponse extraerJsonDesdeXml(String xml) {
        PedidoResponse response = new PedidoResponse();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document doc = factory.newDocumentBuilder()
                    .parse(new InputSource(new StringReader(xml)));

            response.setCodigoEnvio(doc.getElementsByTagName("Codigo").item(0).getTextContent());
            response.setEstado(doc.getElementsByTagName("Mensaje").item(0).getTextContent());
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar XML", e);
        }
        return response;
    }
}
