package domain.commands;

import java.time.LocalDate;

/**
 * Created by AHernandezS on 3/06/2017.
 */
public class RegistrarProveedor {
    private String tipoServicio;
    private String host;
    private String puerto;

    public String getTipoServicio() {
        return tipoServicio;
    }

    public String getHost() {
        return host;
    }

    public String getPuerto() {
        return puerto;
    }
}
