package cl.hcslearning;

public record Config(
    String brokerUsuario,
    String brokerPassword,
    String jndiQueue,
    String jndiConnectionFactory
) {}
