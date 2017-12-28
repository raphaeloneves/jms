package br.com.raphaelneves.jms.topics;

import br.com.raphaelneves.jms.factories.JmsFactory;
import br.com.raphaelneves.jms.listeners.MsgListener;

import javax.jms.*;
import java.util.Scanner;

public class ConsumidorTopicoEstoque {

    public static void main(String args[]) throws Exception {

        JmsFactory jms = new JmsFactory();
        jms.getConnection().setClientID("estoque-module");
        Session session = jms.createSession();

        Topic destination = (Topic) jms.createDestination("loja.request");

        MessageConsumer consumer = session.createDurableSubscriber(destination,
                "assinatura-selector",
                "ebook is null OR ebook = false", false);

        consumer.setMessageListener(new MsgListener());

        new Scanner(System.in).nextLine();

        session.close();
        jms.finish();
    }
}

