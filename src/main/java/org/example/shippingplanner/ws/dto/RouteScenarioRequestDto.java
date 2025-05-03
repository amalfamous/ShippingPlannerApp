package org.example.shippingplanner.ws.dto;

import lombok.Data;
import java.util.List;

@Data
public class RouteScenarioRequestDto {
    // Tout ce qui existait dans RouteRequestDto
    private String originCode;
    private String destinationCode;
    private List<String> transportModes;
    private String priority;

    // Liste de perturbations “what-if” à appliquer
    private List<PerturbationDto> perturbations;
}

