package cl.hcslearning;

import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSHelper {

    private final Logger logger = LoggerFactory.getLogger(JMSHelper.class);
    private final Config config;
    private InitialContext jndi;

    public JMSHelper(Config config) {
        this.config = config;
    }

    public Destination getCola() {
        if(jndi == null) {
            throw new RuntimeException("Antes de recuperar la cola, primero debe crear una conexión");
        }
        try {
            return (Destination) jndi.lookup(config.brokerQueue());
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection crearConexion() {
        try {
            jndi = new InitialContext();
            ConnectionFactory factory = (ConnectionFactory) jndi.lookup(config.jndiConnectionFactory());
            return factory.createConnection(config.brokerUsuario(), config.brokerPassword());
        } catch (NamingException e) {
            logger.error("Problemas al recuperar ConnectionFactory usando JNDI", e);
        } catch (JMSException e) {
            logger.error("Problemas al crear la conexión con el Broker", e);
        }
        throw new RuntimeException("Problemas al intentar crear la conexión");
    }
}
