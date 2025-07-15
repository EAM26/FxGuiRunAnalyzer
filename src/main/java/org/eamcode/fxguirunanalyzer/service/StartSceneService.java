package org.eamcode.fxguirunanalyzer.service;

import org.eamcode.fxguirunanalyzer.api.model.ReportSummaryResponse;
import org.eamcode.fxguirunanalyzer.api.request.Request;
import org.eamcode.fxguirunanalyzer.util.Sorter;

import java.io.IOException;
import java.util.List;

public class StartSceneService {

    private final Request request;
    private final Sorter sorter;

    public StartSceneService() {
        this.request = new Request();
        this.sorter = new Sorter();
    }

    public List<ReportSummaryResponse> getAllSummaryReports() throws IOException, InterruptedException {
            List<ReportSummaryResponse> responses = request.getAllSummaryReports();
            sorter.sortLastFirst(responses);
            return responses;
    }

    public void deleteReport(Long id) {
        request.deleteReport(id);
    }
}
