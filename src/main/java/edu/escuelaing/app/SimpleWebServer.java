package edu.escuelaing.app;

import java.net.ServerSocket;
import java.net.Socket;

public class SimpleWebServer {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Servidor en http://localhost:" + port);
            while (true) {
                Socket client = server.accept();
                new Thread(new HttpHandler(client, "")).start();
            }
        }
    }
}
