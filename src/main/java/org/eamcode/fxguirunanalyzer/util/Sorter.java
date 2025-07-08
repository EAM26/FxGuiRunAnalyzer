package org.eamcode.fxguirunanalyzer.util;

import org.eamcode.fxguirunanalyzer.api.model.ReportSummaryResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class Sorter {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public void sortLastFirst(List<ReportSummaryResponse> reports) {
        reports.sort(
                Comparator.comparing(
                        (ReportSummaryResponse r)
                                -> LocalDateTime.parse(r.getName(), formatter)
                ).reversed());
    }
}
