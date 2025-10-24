package cl.hcslearning.sync;

import cl.hcslearning.Config;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSConsumerSync {

    private final Logger logger = LoggerFactory.getLogger(JMSConsumerSync.class);
    private Config config;
    private Connection conexion;

    public JMSConsumerSync(Config config) {
        this.config = config;
    }

    public void iniciarConexion() {
        try {
            InitialContext jndi = new InitialContext();
            ConnectionFactory factory = (ConnectionFactory) jndi.lookup(config.jndiConnectionFactory());
            conexion = factory.createConnection(config.brokerUsuario(), config.brokerPassword());
        } catch (NamingException e) {

        } catch (JMSException e) {

        }
    }

    public void recibirMensaje() {

    }
}
