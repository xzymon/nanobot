package com.xzymon.nanobot.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class NoteClient {

    private final Client client;

    public NoteClient() {
        // Tworzenie klienta JAX-RS
        client = ClientBuilder.newClient();
    }

    /**
     * Wywołuje metodę GET z zewnętrznej usługi REST.
     *
     * @param url URL usługi REST.
     * @return Zwrócony wynik jako String.
     */
    public String performGetRequest(String url) {
        WebTarget target = client.target(url);
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(String.class);
        } else {
            throw new RuntimeException("Request failed with status: " + response.getStatus());
        }
    }

    /**
     * Wywołuje metodę POST z zewnętrznej usługi REST.
     *
     * @param url  URL usługi REST.
     * @param body Treść żądania (np. JSON), którą chcemy wysłać.
     * @return Zwrócony wynik jako String.
     */
    public String performPostRequest(String url, String body) {
        WebTarget target = client.target(url);
        Response response = target.request(MediaType.APPLICATION_JSON)
                                  .post(Entity.entity(body, MediaType.APPLICATION_JSON));

        if (response.getStatus() == Response.Status.OK.getStatusCode() ||
            response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            return response.readEntity(String.class);
        } else {
            throw new RuntimeException("Request failed with status: " + response.getStatus());
        }
    }

    // Metoda zamykająca klienta
    public void close() {
        client.close();
    }
}