package org.eamcode.fxguirunanalyzer.api.request;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.eamcode.fxguirunanalyzer.api.model.PhaseRequest;
import org.eamcode.fxguirunanalyzer.api.model.PhaseResponse;
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

//    public void createInterval(String multiplier, Long reportId, String cat1, String cat2, String duration1, String duration2) {
//        {
//            String url = getBaseUrl() + "/api/phases/multi";
//            try {
//                String json = String.format("{\"multiplier\": %s, \"reportId\": %d, \"category1\": \"%s\", \"category2\": \"%s\", \"duration1\": \"%s\", \"duration2\": \"%s\"}",
//                        multiplier, reportId, cat1, cat2, duration1, duration2);
//                System.out.println("createInterval in Request called with json: " + json);
//                HttpRequest httpRequest = HttpRequest.newBuilder()
//                        .uri(URI.create(url))
//                        .header("Content-Type", "application/json")
//                        .POST(HttpRequest.BodyPublishers.ofString(json))
//                        .build();
//                HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//
//                if (response.statusCode() != 200) {
//                    throw new IllegalStateException("status " + response.statusCode());
//                }
//            } catch (IOException | InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    public void createInterval(int multiplier,
                               long reportId,
                               String cat1,
                               String cat2,
                               String duration1,
                               String duration2) {

        String base  = getBaseUrl() + "/api/phases/multi";
        String query = String.format(
                "?multiplier=%d&reportId=%d&category1=%s&category2=%s"
                        + "&duration1=%s&duration2=%s",
                multiplier, reportId,
                URLEncoder.encode(cat1, StandardCharsets.UTF_8),
                URLEncoder.encode(cat2, StandardCharsets.UTF_8),
                URLEncoder.encode(duration1, StandardCharsets.UTF_8),
                URLEncoder.encode(duration2, StandardCharsets.UTF_8));

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(base + query))
                .POST(HttpRequest.BodyPublishers.noBody())
                .header("Accept", "application/json")
                .build();

        sendOrThrow(req);
    }

    private void sendOrThrow(HttpRequest req) {
        try {
            HttpResponse<String> res = httpClient.send(req,
                    HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() != 200) {
                throw new IllegalStateException(
                        "status " + res.statusCode() + "\nbody: " + res.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public PhaseResponse createPhase(PhaseRequest phaseRequest) {
        String url = getBaseUrl() + "/api/phases";

        try {
            String json = MAPPER.writeValueAsString(phaseRequest);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IllegalStateException("status" + response.statusCode());
            }

            return MAPPER.readValue(response.body(), PhaseResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    public List<String> getPhaseCategories() throws IOException, InterruptedException {
        String url = getBaseUrl() + "/api/phases/categories";
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

    public void deleteReport(Long id) {
        String url = getBaseUrl() + "/api/reports/" + id;

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IllegalStateException("status " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAllPhases(Long reportId) {
        System.out.println("request deleteAllPhases called with reportId: " + reportId);
        String url = getBaseUrl() + "/api/phases/" + reportId;

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 204) {
                throw new IllegalStateException("status " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
