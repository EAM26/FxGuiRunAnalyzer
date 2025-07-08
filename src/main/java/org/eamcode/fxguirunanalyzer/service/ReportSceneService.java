package org.eamcode.fxguirunanalyzer.service;

import org.eamcode.fxguirunanalyzer.api.model.ReportResponse;
import org.eamcode.fxguirunanalyzer.api.request.Request;

public class ReportSceneService {

    private final Request request;

    public ReportSceneService() {
        this.request = new Request();
    }

    public ReportResponse createReport(String path) {
        return request.createReportResponse(path);
    }
}
