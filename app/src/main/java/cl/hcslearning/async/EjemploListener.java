package cl.hcslearning.async;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EjemploListener implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(EjemploListener.class);

    @Override
    public void onMessage(Message mensaje) {
        String body = null;
        try {
            body = mensaje.getBody(String.class);
            logger.info("Message Body: {}", body);
            mensaje.acknowledge();
        } catch (JMSException e) {
            logger.error("Error al recibir un mensaje", e);
        }

    }
}
