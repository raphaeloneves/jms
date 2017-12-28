package br.com.raphaelneves.jms.adapters;

import br.com.raphaelneves.jms.models.Pedido;

import javax.xml.bind.JAXB;
import java.io.StringWriter;

public class PedidoAdapter {

    public static String toXML(Pedido pedido){
        StringWriter writer = new StringWriter();
        JAXB.marshal(pedido, writer);
        return writer.toString();
    }
}
