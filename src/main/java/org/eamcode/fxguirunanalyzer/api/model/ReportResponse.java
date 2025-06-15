package org.eamcode.fxguirunanalyzer.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportResponse {

    private Long id;
    private String name;
    private String path;
    private List<PhaseResponse> phaseResponses;
    private MetaData metaData;
    private List<PhaseGroupSummary> summaries;
}
