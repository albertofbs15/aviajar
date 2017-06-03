package domain.entity;

/**
 * Created by AHernandezS on 3/06/2017.
 */
public class Proveedor {
    private String host;
    private String port;

    public Proveedor(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }
}
