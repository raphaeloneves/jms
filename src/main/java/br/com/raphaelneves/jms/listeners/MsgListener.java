package br.com.raphaelneves.jms.listeners;

import br.com.raphaelneves.jms.models.Pedido;

import javax.jms.*;

public class MsgListener implements MessageListener {

    private Pedido pedido;

    public void onMessage(Message message) {
        if(message instanceof ObjectMessage){
            ObjectMessage objectMessage = (ObjectMessage) message;
            setObject(objectMessage);
            print(pedido.toString());
            return;
        }

        TextMessage textMessage = (TextMessage) message;
        try {
            print(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void print(String str){
        System.out.println(str);
    }

    private void setObject(ObjectMessage message){
        try {
            pedido = (Pedido) message.getObject();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
