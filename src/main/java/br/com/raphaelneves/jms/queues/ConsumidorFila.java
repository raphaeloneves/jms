package br.com.raphaelneves.jms.queues;

import br.com.raphaelneves.jms.factories.JmsFactory;
import br.com.raphaelneves.jms.listeners.MsgListener;

import javax.jms.*;
import java.util.Scanner;

public class ConsumidorFila {

    public static void main(String args[]) throws Exception {

        JmsFactory jms = new JmsFactory();
        Session session = jms.createSession();

        Destination destination = jms.createDestination ("financeiro");
        MessageConsumer consumer = session.createConsumer(destination);

        consumer.setMessageListener(new MsgListener());

        new Scanner(System.in).nextLine();

        session.close();
        jms.finish();
    }
}
