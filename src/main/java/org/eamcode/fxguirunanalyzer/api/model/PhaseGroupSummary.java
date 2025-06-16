package org.eamcode.fxguirunanalyzer.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhaseGroupSummary {
    private PhaseCategory category;
    private double totalDistance;
    private Duration totalDuration = Duration.ZERO;
    private double averageSpeed;
    private double averageHeartRate;
}
