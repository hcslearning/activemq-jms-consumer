package cl.hcslearning;

import cl.hcslearning.async.JMSConsumerAsync;
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

        // ###### SYNC #########################################
        //JMSConsumerSync consumidorSincronico = new JMSConsumerSync(jmsHelper);
        //consumidorSincronico.recibirMensaje();

        // ###### ASYNC #########################################
        JMSConsumerAsync consumidorAsincronico = new JMSConsumerAsync(jmsHelper);
        consumidorAsincronico.recibirMensajes();

    }
}
