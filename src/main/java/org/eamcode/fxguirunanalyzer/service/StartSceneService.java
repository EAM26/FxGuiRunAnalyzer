package org.eamcode.fxguirunanalyzer.service;

import org.eamcode.fxguirunanalyzer.api.model.ReportSummaryResponse;
import org.eamcode.fxguirunanalyzer.api.request.Request;

import java.util.List;

public class StartSceneService {

    private final Request request;

    public StartSceneService() {
        this.request = new Request();
    }

    public List<ReportSummaryResponse> getAllSummaryReports() {
        return request.getAllSummaryReports();
    }
}
