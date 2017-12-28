package br.com.raphaelneves.jms.topics;

import br.com.raphaelneves.jms.factories.JmsFactory;

import javax.jms.*;

public class AffiliateBReceiver {

    public static void main(String[] args) throws Exception {

        final JmsFactory jms = new JmsFactory();
        jms.getConnection().setClientID("affiliate-02");
        final Session session = jms.createSession();

        Topic requestTopic = (Topic) jms.createDestination("loja.request");

        MessageConsumer consumer = session.createDurableSubscriber(requestTopic, "assinatura02");
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if(message != null){
                    try {
                        System.out.println("Received message: " + ((TextMessage)message).getText());
                        Destination reply = message.getJMSReplyTo();
                        MessageProducer producer = session.createProducer(reply);
                        TextMessage response = session.createTextMessage("Response from B");
                        producer.send(response);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
