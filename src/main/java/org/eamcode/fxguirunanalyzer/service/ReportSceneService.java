package org.eamcode.fxguirunanalyzer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.eamcode.fxguirunanalyzer.api.model.ReportResponse;
import org.eamcode.fxguirunanalyzer.api.request.Request;

import java.io.IOException;

public class ReportSceneService {

    private final Request request;

    public ReportSceneService() {
        this.request = new Request();
    }

    public ReportResponse createReport(String path) {
        return request.createReportResponse(path);
    }

    public ReportResponse getSingleReport(Long id) throws IOException, InterruptedException {
        try {
            return request.getSingleReportResponse(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
