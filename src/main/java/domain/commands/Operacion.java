package domain.commands;

import java.time.LocalDate;

/**
 * Created by AHernandezS on 3/06/2017.
 */
public class Operacion {
    private String tipoOperacion;
    private String tipoServicion;
    private String destino;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private int idReserva;

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public String getTipoServicion() {
        return tipoServicion;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public int getIdReserva() {
        return idReserva;
    }
}
