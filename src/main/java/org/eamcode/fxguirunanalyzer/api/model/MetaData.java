package org.eamcode.fxguirunanalyzer.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaData {

    Long id;
    String name;
    String sport;
    String date;
    LocalTime duration;
    String totalDistance;
    String heartRateAvg;
    String speedAvg;
}
