/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arniadigitale_bosi;

/**
 *
 * @author bosi.damiano
 */
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class GetHandler implements HttpHandler {
    private ArniaService arniaService = new ArniaService();
    private Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Aggiunge i CORS per permettere a Swagger di comunicare con il server
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        if ("OPTIONS".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        if ("GET".equals(exchange.getRequestMethod())) {
            try {
               // 1. Recupera la lista reale dal database tramite il servizio
                List<ArniaResponse> arnie = arniaService.getTutteLeArnie(); 
                String jsonResponse = gson.toJson(arnie); 
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(jsonResponse.getBytes());
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
                String error = "{\"errore\": \"Errore nel caricamento dei dati\"}";
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(500, error.getBytes().length);
                exchange.getResponseBody().write(error.getBytes());
                exchange.getResponseBody().close();
            }
        } else {
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
        }
    }
}