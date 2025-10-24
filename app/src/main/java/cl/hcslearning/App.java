package cl.hcslearning;

import cl.hcslearning.sync.JMSConsumerSync;
import jakarta.jms.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Config config = new Config(
                "iplacex",
                "Iplacex25",
                "colas/ejemplo",
                "servidorArtemis");

        JMSHelper jmsHelper = new JMSHelper(config);
        try (Connection conexion = jmsHelper.crearConexion()) {
            Session sesion = conexion.createSession(Session.CLIENT_ACKNOWLEDGE);
            Destination cola = jmsHelper.getCola();
            MessageConsumer consumidor = sesion.createConsumer(cola);
            conexion.start();

            Message mensaje = consumidor.receive();
            String body = mensaje.getBody(String.class);
            LOGGER.info("Message Body: {}", body);
            mensaje.acknowledge();

        } catch (JMSException e) {
            LOGGER.error("Error al crear la sesión", e);
        }
    }
}
