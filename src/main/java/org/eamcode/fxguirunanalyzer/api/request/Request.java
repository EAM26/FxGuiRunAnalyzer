package org.eamcode.fxguirunanalyzer.api.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.eamcode.fxguirunanalyzer.api.model.ReportResponse;
import org.eamcode.fxguirunanalyzer.api.model.ReportSummaryResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class Request {

    public static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String protocol;
    private final String host;
    private final String port;

//    public Request(String protocol, String host, String port, String path) {
//        this.protocol = protocol;
//        this.host = host;
//        this.port = port;
//        this.path = path;
//
//    }

    public Request() {
        this.protocol = "http";
        this.host = "localhost";
        this.port = "8080";
    }

    public String getBaseUrl() {
        return protocol + "://" + host + ":" + port;
    }


    public ReportResponse createReportResponse(String absPath) {
        String encoded = URLEncoder.encode(absPath, StandardCharsets.UTF_8);
        String url      = getBaseUrl() + "/api/reports?path=" + encoded;

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IllegalStateException("status" + response.statusCode());
            }

            return MAPPER.readValue(response.body(), new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private HttpResponse<String> getReport(String url) throws IOException, InterruptedException {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IllegalStateException("status" + response.statusCode());
            }
            return response;


    }


    public List<ReportSummaryResponse> getAllSummaryReports() throws IOException, InterruptedException {
        String url = getBaseUrl() + "/api/reports/summary";
            HttpResponse<String> response = getReport(url);
            return MAPPER.readValue(response.body(), new TypeReference<>() {
            });
    }

    public ReportResponse getSingleReportResponse(Long id) throws IOException, InterruptedException {
        String url = getBaseUrl() + "/api/reports/" + id;
        HttpResponse<String> response = getReport(url);
        return MAPPER.readValue(response.body(), new TypeReference<>() {

        });
    }

}
