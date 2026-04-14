/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arniadigitale_bosi;



/**
 *
 * @author bosi.damiano
 */

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerRest {
    public static void avviaServer(int porta) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(porta), 0);
            
            // Endpoint per la gestione dell'arnia
            server.createContext("/api/arnia/post", new PostHandler());
            server.createContext("/api/arnia/get", new GetHandler());
            
            server.setExecutor(null); 
            server.start();
            
            System.out.println("==============================================");
            System.out.println("  Server REST Apicoltura avviato!");
            System.out.println("==============================================");
            System.out.println("Porta: " + porta);
            System.out.println();
            System.out.println("Endpoint disponibili:");
            System.out.println("  - POST: http://localhost:" + porta + "/api/arnia/post");
            System.out.println("  - GET:  http://localhost:" + porta + "/api/arnia/get");
            System.out.println();
            System.out.println("Premi Ctrl+C per fermare il server");
            System.out.println("==============================================");
            
        } catch (IOException e) {
            System.err.println("Errore nell'avvio del server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

