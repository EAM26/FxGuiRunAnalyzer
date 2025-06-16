package org.eamcode.fxguirunanalyzer.api.model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ReportSummaryResponse {
    private Long id;
    private String name;
    private String path;
    private String distance;
    private LocalTime duration;
}
