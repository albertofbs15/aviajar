package domain.entity;

import java.io.Serializable;

/**
 * Created by AHernandezS on 2/06/2017.
 */
public class RespuestaOperacion implements Serializable{
    private int idReserva;
    private Boolean respuesta;

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public void setRespuesta(Boolean respuesta) {
        this.respuesta = respuesta;
    }

    public Boolean getRespuesta() {
        return respuesta;
    }
}
