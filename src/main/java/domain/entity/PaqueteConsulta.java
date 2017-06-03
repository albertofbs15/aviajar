package domain.entity;

import java.util.ArrayList;

/**
 * Created by AHernandezS on 3/06/2017.
 */
public class PaqueteConsulta {
    ArrayList<RespuestaConsultaDisponibilidad> respuestaConsultaDisponibilidads = new ArrayList<>();

    public ArrayList<RespuestaConsultaDisponibilidad> getRespuestaConsultaDisponibilidads() {
        return respuestaConsultaDisponibilidads;
    }

    public void setRespuestaConsultaDisponibilidads(ArrayList<RespuestaConsultaDisponibilidad> respuestaConsultaDisponibilidads) {
        this.respuestaConsultaDisponibilidads = respuestaConsultaDisponibilidads;
    }

}
