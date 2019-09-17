package com.netlight.Norrsken.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netlight.Norrsken.clients.domain.EricssonWeatherData;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class EricssonWeatherClient {
    String url;
    String path;
    DateTimeFormatter dateTimeFormatter;

    private ObjectMapper objectMapper;

    public EricssonWeatherClient(String url, String path, ObjectMapper objectMapper) {
        this.url = url;
        this.path = path;
        this.objectMapper = objectMapper;
        dateTimeFormatter = DateTimeFormatter.ofPattern("YYYYMMddHHmm");
    }
    public EricssonWeatherData getWeatherData(String locationId, OffsetDateTime time, String token) {
        String timeString = dateTimeFormatter.format(time);
        String target = path + "/" + timeString;
        Response response = ClientBuilder.newClient()
                .target(url)
                .path(target)
                .queryParam("location", locationId)
                .request()
                .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token))
                .get();

        String responseString = response.readEntity(String.class);

        try {
            return objectMapper.readValue(responseString, EricssonWeatherData.class);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to parse json", e);
        }
    }
}
