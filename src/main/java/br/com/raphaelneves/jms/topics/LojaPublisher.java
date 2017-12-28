package br.com.raphaelneves.jms.topics;

import br.com.raphaelneves.jms.factories.JmsFactory;

import javax.jms.*;
import java.util.Random;

public class LojaPublisher {

    public static void main(String[] args) throws Exception {

        JmsFactory jms = new JmsFactory();
        jms.getConnection().setClientID("loja-publisher");
        Session session = jms.createSession();

        Topic requestTopic = (Topic) jms.createDestination("loja.request");
        Queue replyQueue = (Queue) jms.createDestination("loja.reply");

        MessageProducer producer = session.createProducer(requestTopic);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        String messageId = createRandomString();

        Message requestMessage = session.createTextMessage("Requesting a service");
        requestMessage.setJMSReplyTo(replyQueue);
        requestMessage.setJMSCorrelationID(messageId);
        requestMessage.setJMSExpiration(1000 * 60);

        producer.send(requestMessage);

        String selector = JmsFactory.CORRELATION_ID + "='" + messageId + "'";
        MessageConsumer consumer = session.createConsumer(replyQueue, selector);

        while(true){
            Message receivedMessage = consumer.receive(10000);
            if(receivedMessage != null)
                System.out.println(receivedMessage);
            else
                break;
        }

    }

    private static String createRandomString() {
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
    }

}
