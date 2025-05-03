package org.example.shippingplanner.ws.dto;


import lombok.Data;

import java.util.List;
@Data
public class RouteResponseDto {
    private Long id;
    private String code;
    private List<String> steps;
    private double totalCost;
    private double totalTime;
    private String rationale;
    // pour tracer la route sur la carte
    private List<CoordinateDto> coordinates;
}

