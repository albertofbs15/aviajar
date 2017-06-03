package domain.entity;

import java.io.Serializable;

/**
 * Created by AHernandezS on 2/06/2017.
 */
public class DetalleServicio implements Serializable{
    private String destino;
    private String fecha;
    private long costo;

    public String getDestino() {
        return destino;
    }

    public String getFecha() {
        return fecha;
    }

    public long getCosto() {
        return costo;
    }
}
