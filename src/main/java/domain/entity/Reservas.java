package domain.entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by AHernandezS on 3/06/2017.
 */
public class Reservas {

    private static int contReserva=0;
    public static HashMap<Integer, ArrayList<ReservaProveedor>> reservas = new HashMap<>();

    public static int saveReserva(ArrayList<ReservaProveedor> reservaProveedors) {
        int id = contReserva++;
        reservas.put(id, reservaProveedors);
        return id;
    }

    public static ArrayList<ReservaProveedor> getReserva(int idReserva) {
        return reservas.get(idReserva);
    }

}
