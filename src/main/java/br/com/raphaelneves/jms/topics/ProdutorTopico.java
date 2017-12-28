package br.com.raphaelneves.jms.topics;


import br.com.raphaelneves.jms.factories.JmsFactory;
import br.com.raphaelneves.jms.models.Pedido;
import br.com.raphaelneves.jms.models.PedidoFactory;

import javax.jms.*;

public class ProdutorTopico {

    public static void main(String args[]) throws Exception {

        JmsFactory jms = new JmsFactory();
        Session session = jms.createSession();

        Destination destination = jms.createDestination ("loja.request");
        MessageProducer producer = session.createProducer(destination);

        /*Pedido pedido = new PedidoFactory().geraPedidoComValores();
        String xml = pedido.toXML();*/

        Message stringMessage = session.createTextMessage("Teste");
        stringMessage.setBooleanProperty("ebook", false);
        stringMessage.setJMSCorrelationID("Raphael");
        producer.send(stringMessage);

        /*Message objMessage = session.createObjectMessage(pedido);
        objMessage.setBooleanProperty("ebook", true);
        producer.send(objMessage);*/

        session.close();
        jms.finish();
    }
}
