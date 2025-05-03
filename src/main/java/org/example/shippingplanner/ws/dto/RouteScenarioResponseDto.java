package org.example.shippingplanner.ws.dto;

import lombok.Data;
import java.util.List;

@Data
public class RouteScenarioResponseDto {
    private RouteResponseDto mainRoute;
    private List<AlternativeRouteDto> alternatives;
}