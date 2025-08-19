package edu.escuelaing.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimpleWebServerTest {

    @BeforeClass
    @SuppressWarnings("CallToPrintStackTrace")
    public static void startServer() throws Exception {
        
        new Thread(() -> {
            try {
                SimpleWebServer.main(new String[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);
    }

    private String getResponse(String method, String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method);

        StringBuilder resp;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            resp = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                resp.append(line);
            }
        }
        return resp.toString();
    }

    @Test
    public void testHelloGet() throws Exception {
        String resp = getResponse("GET", "http://localhost:8080/hello?name=Juan");
        assertTrue(resp.contains("Hola Juan desde GET"));
    }

    @Test
    public void testHelloPost() throws Exception {
        String resp = getResponse("POST", "http://localhost:8080/hellopost?name=Maria");
        assertTrue(resp.contains("Hola Maria desde POST"));
    }

    @Test
    public void testApiTime() throws Exception {
        String resp = getResponse("GET", "http://localhost:8080/api/time");
        assertTrue(resp.contains("time"));
    }
}
