package servicios.proveedor;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.commands.Operacion;
import domain.entity.Proveedor;
import domain.entity.RespuestaConsultaDisponibilidad;
import domain.entity.RespuestaConsultaDisponibilidadConID;
import domain.entity.RespuestaOperacion;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by alber on 5/24/2017.
 */
public class ProveedorServiceProvider  {

    public RespuestaConsultaDisponibilidad consultarProveedor(Proveedor proveedor, Operacion operacion){
        try {
            String r = "http://"+proveedor.getHost().trim()+":"+proveedor.getPort().trim()+"/servicios/disponibilidad/consultar";
            URL url = new URL(r);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String input = "{\"destino\":\""+ operacion.getDestino()+"\",\"fechaInicial\":\""+ operacion.getFechaInicial()+"\",\"fechaFinal\":\""+ operacion.getFechaFinal()+"\"}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            StringBuffer sb;

            sb = new StringBuffer();
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            ObjectMapper mapper = new ObjectMapper();

            RespuestaConsultaDisponibilidad respuesta = mapper.readValue(sb.toString(), RespuestaConsultaDisponibilidad.class);

            conn.disconnect();
            return respuesta;
        } catch (JsonGenerationException e){
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public RespuestaConsultaDisponibilidadConID reservarProveedor(Proveedor proveedor, Operacion operacion){
        try {
            String r = "http://"+proveedor.getHost().trim()+":"+proveedor.getPort().trim()+"/servicios/disponibilidad/reservar";
            URL url = new URL(r);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String input = "{\"destino\":\""+ operacion.getDestino()+"\",\"fechaInicial\":\""+ operacion.getFechaInicial()+"\",\"fechaFinal\":\""+ operacion.getFechaFinal()+"\"}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            StringBuffer sb;

            sb = new StringBuffer();
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            ObjectMapper mapper = new ObjectMapper();

            RespuestaConsultaDisponibilidadConID respuesta = mapper.readValue(sb.toString(), RespuestaConsultaDisponibilidadConID.class);

            conn.disconnect();
            return respuesta;
        } catch (JsonGenerationException e){
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cancelarReservaProveedor(Proveedor proveedor, Operacion operacion, long idReserva){
        try {
            String r = "http://"+proveedor.getHost().trim()+":"+proveedor.getPort().trim()+"/servicios/disponibilidad/"+idReserva+"/cancelar";
            URL url = new URL(r);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String input = "{\"destino\":\""+ operacion.getDestino()+"\",\"fechaInicial\":\""+ operacion.getFechaInicial()+"\",\"fechaFinal\":\""+ operacion.getFechaFinal()+"\"}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
        } catch (JsonGenerationException e){
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public Respuesta pagar(Pago pago){
//        Respuesta response = new Respuesta();
//        Factura factura= new Factura();
//        factura.setReferenciaFactura(pago.getFactura().getReferenciaFactura());
//        response.setFactura(factura);
//        response.setMensaje("COMPLETO!");
//        try {
//            URL url = new URL("http://localhost:8080/RESTfulExample/r/router/pagar");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setDoOutput(true);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json");
//            String input = "{\"referenciaFactura\":"+pago.getFactura().getReferenciaFactura()+",\"totalPagar\":\""+pago.getFactura().getValor()+"\"}";
//            OutputStream os = conn.getOutputStream();
//            os.write(input.getBytes());
//            os.flush();
//            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + conn.getResponseCode());
//            }
//            BufferedReader br = new BufferedReader(new InputStreamReader(
//                    (conn.getInputStream())));
//            String output;
//            StringBuffer sb;
//            sb = new StringBuffer();
//            while ((output = br.readLine()) != null) {
//                sb.append(output);
//            }
//            ObjectMapper mapper = new ObjectMapper();
//            response = mapper.readValue(output, Respuesta.class);
//            conn.disconnect();
//        } catch (JsonGenerationException e){
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        }catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return response;
//    }
}
