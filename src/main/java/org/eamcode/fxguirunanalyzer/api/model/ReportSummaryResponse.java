package org.eamcode.fxguirunanalyzer.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportSummaryResponse {
    private Long id;
    private String name;
    private String path;
    private String distance;
    private LocalTime duration;
}
