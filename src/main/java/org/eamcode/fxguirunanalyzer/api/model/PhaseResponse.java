package org.eamcode.fxguirunanalyzer.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhaseResponse {

    private Long reportId;

    private LocalTime startTime;
    private LocalTime stopTime;
    private PhaseCategory category;

    private Double distance;
    private Double speed;
    private Duration duration;
    private double heartRateAvg;
}
