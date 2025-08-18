package edu.escuelaing.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class HttpHandler implements Runnable {

    private final Socket client;

    public HttpHandler(Socket client, String webRoot) {
        this.client = client;
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream())); OutputStream out = client.getOutputStream()) {

            String line = in.readLine();
            if (line == null) {
                return;
            }

            String[] parts = line.split(" ");
            String method = parts[0];
            String uri = parts[1];

            String path = uri;
            String query = null;
            int q = uri.indexOf('?');
            if (q >= 0) {
                path = uri.substring(0, q);
                query = uri.substring(q + 1);
            }

            if (path.equals("/")) {
                path = "/index.html";
            }

            // GET /hello?name=Juan
            if (path.equals("/hello") && method.equals("GET")) {
                String name = getParam(query, "name");
                String msg = "Hola " + name + " desde GET!";
                send(out, msg, "text/plain");
                return;
            }

            // POST /hellopost?name=Juan
            if (path.equals("/hellopost") && method.equals("POST")) {
                String name = getParam(query, "name");
                String msg = "Hola " + name + " desde POST!";
                send(out, msg, "text/plain");
                return;
            }

            // GET /api/time
            if (path.equals("/api/time") && method.equals("GET")) {
                String now = java.time.LocalDateTime.now().toString();
                String json = "{\"time\": \"" + now + "\"}";
                send(out, json, "application/json");
                return;
            }

            // Enlace a Resources
            try (InputStream file = getClass().getResourceAsStream(path)) {
                if (file != null) {
                    byte[] data = file.readAllBytes();
                    send(out, data, MimeTypes.get(path));
                } else {
                    send(out, "<h1>404 Not Found</h1>", "text/html");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getParam(String query, String key) {
        if (query == null || query.isEmpty()) {
            return "";
        }
        for (String pair : query.split("&")) {
            String[] kv = pair.split("=", 2);
            if (kv.length == 2 && kv[0].equals(key)) {
                return URLDecoder.decode(kv[1], StandardCharsets.UTF_8);
            }
        }
        return "";
    }

    private void send(OutputStream out, String body, String type) throws IOException {
        byte[] data = body.getBytes(StandardCharsets.UTF_8);
        send(out, data, type);
    }

    private void send(OutputStream out, byte[] body, String type) throws IOException {
        out.write(("HTTP/1.1 200 OK\r\n").getBytes());
        out.write(("Content-Type: " + type + "\r\n").getBytes());
        out.write(("Content-Length: " + body.length + "\r\n").getBytes());
        out.write("\r\n".getBytes());
        out.write(body);
    }
}
