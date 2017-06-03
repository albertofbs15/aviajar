package domain.entity;

import java.util.ArrayList;

/**
 * Created by AHernandezS on 3/06/2017.
 */
public class PaqueteConsultaConID {
    private int idReserva;
    private long total;
    ArrayList<RespuestaConsultaDisponibilidadConID> respuesta = new ArrayList<>();

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public ArrayList<RespuestaConsultaDisponibilidadConID> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(ArrayList<RespuestaConsultaDisponibilidadConID> respuesta) {
        this.respuesta = respuesta;
    }
}
