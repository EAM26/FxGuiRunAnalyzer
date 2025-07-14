package org.eamcode.fxguirunanalyzer.service;

import org.eamcode.fxguirunanalyzer.api.request.Request;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class IntervalDialogService {
    private final Request request;

    public IntervalDialogService() {
        this.request = new Request();
    }

    public List<String> getCategoryItems() {
        try {
            return request.getPhaseCategories();
        } catch (IOException | InterruptedException e) {
            System.err.println("Error fetching category items: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void saveInterval(Long reportId, Integer hr1, Integer min1, Integer sec1, String category1,
                             Integer hr2, Integer min2, Integer sec2, String category2, String multiplier) {

        String duration1 = String.format("%02d:%02d:%02d", hr1, min1, sec1);
        String duration2 = String.format("%02d:%02d:%02d", hr2, min2, sec2);
        request.createInterval(Integer.parseInt(multiplier), reportId, category1, category2,
                duration1, duration2);
//
    }
}
