package domain.entity;

import java.util.ArrayList;

/**
 * Created by AHernandezS on 3/06/2017.
 */
public class PaqueteCancelacion {
    ArrayList<RespuestaOperacion> respuesta = new ArrayList<>();

    public ArrayList<RespuestaOperacion> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(ArrayList<RespuestaOperacion> respuesta) {
        this.respuesta = respuesta;
    }
}
