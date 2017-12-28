package br.com.raphaelneves.jms.factories;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JmsFactory {

    private final InitialContext context;
    private final String FACTORY = "ConnectionFactory";
    private Connection connection;
    public static final String CORRELATION_ID = "JMSCorrelationID";

    public JmsFactory() throws Exception {
        this.context = new InitialContext();
    }

    private void createConnection(){
        try {
            if(this.connection == null){
                ConnectionFactory factory = (ConnectionFactory) context.lookup(FACTORY);
                this.connection = factory.createConnection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        createConnection();
        return connection;
    }

    public Session createSession(){
        createConnection();
        Session session = null;
        try {
            session = connection.createSession(false , Session.AUTO_ACKNOWLEDGE);
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return session;
    }

    public Destination createDestination(String name){
        Destination destination = null;
        try {
            destination = (Destination) context.lookup(name);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return destination;
    }

    public void finish(){
        try {
            connection.close();
            context.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
