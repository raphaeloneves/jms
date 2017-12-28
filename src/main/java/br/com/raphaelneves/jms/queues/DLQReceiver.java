package br.com.raphaelneves.jms.queues;

import br.com.raphaelneves.jms.factories.JmsFactory;

import javax.jms.*;
import java.util.Scanner;

public class DLQReceiver {

    public static void main(String... args) throws Exception {

        JmsFactory jms = new JmsFactory();
        Session session = jms.createSession();

        Destination destination = jms.createDestination("DLQ");
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                System.out.println("Cannot consume message");
            }
        });

        new Scanner(System.in).nextLine();

        session.close();
        jms.finish();
    }

}
