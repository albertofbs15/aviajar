package http;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.*;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import domain.commands.Operacion;
import domain.commands.RegistrarProveedor;
import domain.entity.*;
import servicios.proveedor.ProveedorServiceProvider;
import util.JacksonJdk8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletionStage;

/**
 * Created by AHernandezS on 22/03/2017.
 */

public class HttpAesDirective extends AllDirectives {

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("routes");

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        HttpAesDirective app = new HttpAesDirective();


        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
                ConnectHttp.toHost("localhost", 9080), materializer);

        System.out.println("Server online at http://localhost:9080/\nPress RETURN to stop...");
        System.in.read();

        binding.thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }

    private Route createRoute() {
        return route(
                pathPrefix("servicios", () ->
                        post( () -> route(
                                path("operacion", () -> entity(JacksonJdk8.unmarshaller(Operacion.class), operacion -> handleOperacion(operacion))),
                                path("registrarProveedor", () -> entity(JacksonJdk8.unmarshaller(RegistrarProveedor.class), opregistrarProveedorracion -> handleRegistrarProveedor(opregistrarProveedorracion)))
                        ))
                )
        );
    }

    private Route handleRegistrarProveedor(RegistrarProveedor opregistrarProveedorracion) {
        System.out.println(LocalDate.now() + ": handleRegistrarProveedor ");
        Proveedores.proveedorManager.addProveedor(opregistrarProveedorracion.getTipoServicio(), opregistrarProveedorracion.getHost(), opregistrarProveedorracion.getPuerto());
        return complete(StatusCodes.OK);
    }

    private Route handleOperacion(Operacion operacion) {
        System.out.println(LocalDate.now() + ": handleOperacion ");
        ProveedorServiceProvider proveedor = new ProveedorServiceProvider();

        if (operacion.getTipoOperacion().equals("cot")) {

            List<Proveedor> prove = new ArrayList<>();
            if  (operacion.getTipoServicion().equals("paquete")) {
                prove.add(proveedorMasEconomico(operacion, "aereo"));
                prove.add(proveedorMasEconomico(operacion, "terrestre"));
                prove.add(proveedorMasEconomico(operacion, "paseo"));
                prove.add(proveedorMasEconomico(operacion, "alojamiento"));
            } else {
                prove.add(proveedorMasEconomico(operacion, operacion.getTipoServicion()));
            }
            PaqueteConsulta paquete = new PaqueteConsulta();

            if (prove.size() > 0) {
                for (Proveedor p :  prove) {
                    if (p != null) {
                        paquete.getRespuestaConsultaDisponibilidads().add(proveedor.consultarProveedor(p, operacion));
                    }
                }
            }

            return complete(StatusCodes.OK, paquete, Jackson.<PaqueteConsulta>marshaller());

        } else if (operacion.getTipoOperacion().equals("res")) {
            List<Proveedor> prove = new ArrayList<>();
            if  (operacion.getTipoOperacion().equals("paquete")) {
                prove.add(proveedorMasEconomico(operacion, "aereo"));
                prove.add(proveedorMasEconomico(operacion, "terrestre"));
                prove.add(proveedorMasEconomico(operacion, "paseo"));
                prove.add(proveedorMasEconomico(operacion, "alojamiento"));
            } else {
                prove.add(proveedorMasEconomico(operacion, operacion.getTipoServicion()));
            }

            PaqueteConsultaConID paquete = new PaqueteConsultaConID();
            if (prove.size() > 0) {
                ArrayList<ReservaProveedor> reservaProveedors = new ArrayList<>();
                for (Proveedor p :  prove) {
                    if (p != null) {
                        RespuestaConsultaDisponibilidadConID resp = proveedor.reservarProveedor(p, operacion);
                        if (resp.getId() > -1) {
                            paquete.getRespuesta().add(resp);
                            reservaProveedors.add(new ReservaProveedor(resp.getId(), p, resp.getCostoTotal()));
                        }
                    }
                }

                if (reservaProveedors.size()>0) {
                    long total = paquete.getRespuesta().stream().mapToLong(RespuestaConsultaDisponibilidadConID::getCostoTotal).sum();
                    paquete.setTotal(total);
                    paquete.setIdReserva(Reservas.saveReserva(reservaProveedors));
                }
            }
            return complete(StatusCodes.OK, paquete, Jackson.<PaqueteConsultaConID>marshaller());
        }else if (operacion.getTipoOperacion().equals("can")) {


            ArrayList<ReservaProveedor> reservaProveedors = Reservas.getReserva(operacion.getIdReserva());

            RespuestaCancelacion respuestaCancelacion = new RespuestaCancelacion();
            respuestaCancelacion.setIdReserva(operacion.getIdReserva());

            long total = 0;
            for (ReservaProveedor reserva : reservaProveedors) {
                total +=reserva.getValor();
                proveedor.cancelarReservaProveedor(reserva.getProveedor(), operacion, operacion.getIdReserva());
            }

            respuestaCancelacion.setValorDevolucion(total);

            return complete(StatusCodes.OK, respuestaCancelacion, Jackson.<RespuestaCancelacion>marshaller());
        }
        return  null;
    }


    public ArrayList<Proveedor> asdfasdf (Operacion operacion){
        ProveedorServiceProvider serviceProvider = new ProveedorServiceProvider();

        List<Proveedor> prove = Proveedores.proveedorManager.getProveedores().get(operacion.getTipoServicion());
        if (prove == null)
            return null;
        PaqueteConsulta paquete = new PaqueteConsulta();

        RespuestaConsultaDisponibilidad minima = new RespuestaConsultaDisponibilidad();
        Proveedor minProveedor = null;
        if (prove.size() > 0) {
            for (Proveedor p :  prove) {
                RespuestaConsultaDisponibilidad nuevo = serviceProvider.consultarProveedor(p, operacion);

                if (minima.getTipoServivio() == null) {
                    minima = nuevo;
                    minProveedor = p;
                }else if (minima.getCostoTotal() > nuevo.getCostoTotal()) {
                    minProveedor = p;
                    minima = nuevo;
                }
            }
        }

        ArrayList<Proveedor>  array = new ArrayList<>();
        array.add(minProveedor);
        return array;

    }

    public Proveedor proveedorMasEconomico(Operacion operacion, String tipoServicio){
        ProveedorServiceProvider serviceProvider = new ProveedorServiceProvider();

        List<Proveedor> prove = Proveedores.proveedorManager.getProveedores().get(tipoServicio);
        if (prove == null)
            return null;
        PaqueteConsulta paquete = new PaqueteConsulta();

        RespuestaConsultaDisponibilidad minima = new RespuestaConsultaDisponibilidad();
        Proveedor minProveedor = null;
        if (prove.size() > 0) {
            for (Proveedor p :  prove) {
                RespuestaConsultaDisponibilidad nuevo = serviceProvider.consultarProveedor(p, operacion);

                if (minima.getTipoServivio() == null) {
                    minima = nuevo;
                    minProveedor = p;
                }else if (minima.getCostoTotal() > nuevo.getCostoTotal()) {
                    minProveedor = p;
                    minima = nuevo;
                }
            }
        }

        return minProveedor;
    }
}
