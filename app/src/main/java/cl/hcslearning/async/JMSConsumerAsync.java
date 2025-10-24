package cl.hcslearning.async;

import cl.hcslearning.JMSHelper;
import jakarta.jms.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JMSConsumerAsync {
    private final Logger logger = LoggerFactory.getLogger(JMSConsumerAsync.class);
    JMSHelper jmsHelper;

    public JMSConsumerAsync(JMSHelper jmsHelper) {
        this.jmsHelper = jmsHelper;
    }

    public void recibirMensajes(){
        try (Connection conexion = jmsHelper.crearConexion()) {
            Session sesion = conexion.createSession(Session.CLIENT_ACKNOWLEDGE);
            Destination cola = jmsHelper.getCola();
            MessageConsumer consumidor = sesion.createConsumer(cola);
            consumidor.setMessageListener( new EjemploListener() );
            conexion.start();

            Thread.currentThread().join();

        } catch (JMSException e) {
            logger.error("Error al recibir un mensaje", e);
        } catch (InterruptedException e) {
            logger.error("Hilo principal interrumpido. Deteniendo el consumidor.");
            throw new RuntimeException(e);
        }
    }
}
