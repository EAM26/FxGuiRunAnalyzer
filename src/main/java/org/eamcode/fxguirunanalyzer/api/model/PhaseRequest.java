package org.eamcode.fxguirunanalyzer.api.model;

import lombok.Data;

@Data
public class PhaseRequest {

    private String duration;
    private PhaseCategory category;
    private Long reportId;

}