package org.eamcode.fxguirunanalyzer.service;

import org.eamcode.fxguirunanalyzer.api.model.PhaseCategory;
import org.eamcode.fxguirunanalyzer.api.model.PhaseRequest;
import org.eamcode.fxguirunanalyzer.api.request.Request;
import org.eamcode.fxguirunanalyzer.util.MyTimeFormatter;

import java.io.IOException;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;

public class PhaseDialogService {
    private final Request request;

    public PhaseDialogService() {
        this.request = new Request();
    }

    public void savePhase(Long reportId, Integer hr, Integer min, Integer sec, String category) {
        PhaseRequest phaseRequest = new PhaseRequest();
        phaseRequest.setReportId(reportId);
        MyTimeFormatter formatter = new MyTimeFormatter();
        phaseRequest.setDuration(formatter.formatDuration(hr, min, sec));
        System.out.println(category);
        phaseRequest.setCategory(PhaseCategory.valueOf(category));
        request.createPhase(phaseRequest);
    }

    public List<String> getCategoryItems() {
        try {
            return request.getPhaseCategories();
        } catch (IOException | InterruptedException e) {
            System.err.println("Error fetching category items: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
