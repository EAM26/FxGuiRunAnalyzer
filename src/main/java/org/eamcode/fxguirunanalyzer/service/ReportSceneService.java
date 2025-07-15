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

    public ReportResponse getSingleReport(Long id) {
        try {
            return request.getSingleReportResponse(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void deleteAllPhases(Long reportId) {
        System.out.println("service deleteAllPhases called with reportId: " + reportId);
        try {
            request.deleteAllPhases(reportId);
        } catch (Exception e) {
            System.err.println("Error deleting phases: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
