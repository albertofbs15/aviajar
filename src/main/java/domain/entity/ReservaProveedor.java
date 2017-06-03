package domain.entity;

/**
 * Created by AHernandezS on 3/06/2017.
 */
public class ReservaProveedor {

        private int id;
        private Proveedor proveedor;
        private long valor;

        public ReservaProveedor(int id, Proveedor proveedor, long valor) {
            this.id = id;
            this.proveedor = proveedor;
            this.valor = valor;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Proveedor getProveedor() {
            return proveedor;
        }

        public void setProveedor(Proveedor proveedor) {
            this.proveedor = proveedor;
        }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }
}
