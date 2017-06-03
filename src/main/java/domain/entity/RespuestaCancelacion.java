package domain.entity;

import java.io.Serializable;

/**
 * Created by AHernandezS on 2/06/2017.
 */
public class RespuestaCancelacion implements Serializable{
    private int idReserva;
    private long valorDevolucion;

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public long getValorDevolucion() {
        return valorDevolucion;
    }

    public void setValorDevolucion(long valorDevolucion) {
        this.valorDevolucion = valorDevolucion;
    }
}
