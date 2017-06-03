package domain.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AHernandezS on 3/06/2017.
 */
public class Proveedores {

    public static Proveedores proveedorManager = new Proveedores();

    Map<String, List<Proveedor>> proveedores = new HashMap<>();

    public void addProveedor (String tipoProveedor, String host, String puerto) {
        if (proveedores.get(tipoProveedor) == null) {
            ArrayList<Proveedor> provedores = new ArrayList<>();
            provedores.add(new Proveedor(host, puerto));
            proveedores.put(tipoProveedor, provedores);
        } else {
            proveedores.get(tipoProveedor).add(new Proveedor(host, puerto));
        }
        System.out.println("Prooveedor added " + tipoProveedor + "  " + host+":"+puerto);
    }

    public Map<String, List<Proveedor>> getProveedores() {
        return proveedores;
    }
}
