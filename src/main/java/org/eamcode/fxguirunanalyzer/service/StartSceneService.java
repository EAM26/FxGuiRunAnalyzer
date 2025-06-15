package org.eamcode.fxguirunanalyzer.service;

import org.eamcode.fxguirunanalyzer.api.model.ReportResponse;
import org.eamcode.fxguirunanalyzer.api.request.Request;

import java.util.List;

public class StartSceneService {

    private final Request request;

    public StartSceneService() {
        this.request = new Request();
    }

    public List<ReportResponse> getAllReports() {
//        return ...... todo make request to localhost 8080 en api/reports
        return request.getAllReports();
    }
}
