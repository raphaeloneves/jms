package br.com.raphaelneves.jms.queues;


import br.com.raphaelneves.jms.factories.JmsFactory;
import br.com.raphaelneves.jms.models.PedidoFactory;

import javax.jms.*;

public class ProdutorFila {

    public static void main(String args[]) throws Exception {

        JmsFactory jms = new JmsFactory();
        Session session = jms.createSession();

        Destination destination = jms.createDestination ("financeiro");
        MessageProducer producer = session.createProducer(destination);

        String xml = new PedidoFactory().geraPedidoComValores().toXML();

        Message message = session.createTextMessage(xml);
        //message.setBooleanProperty("ebook", false);
        producer.send(message);

        session.close();
        jms.finish();
    }
}
