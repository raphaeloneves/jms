package br.com.raphaelneves.jms.topics;

import br.com.raphaelneves.jms.factories.JmsFactory;
import br.com.raphaelneves.jms.listeners.MsgListener;

import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;
import java.util.Scanner;

public class ConsumidorTopicoFinanceiro {

    public static void main(String... args) throws Exception {

        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", "*");

        JmsFactory jms = new JmsFactory();
        jms.getConnection().setClientID("financeiro-module");
        Session session = jms.createSession();

        Topic topic = (Topic) jms.createDestination("loja");
        MessageConsumer consumer = session.createDurableSubscriber(topic,
                "assinatura", "ebook is null or ebook = true", false);

        consumer.setMessageListener(new MsgListener());

        new Scanner(System.in).nextLine();

        session.close();
        jms.finish();



    }
}
