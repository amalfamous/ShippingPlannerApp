package org.example.shippingplanner.service.facade;

import org.example.shippingplanner.ws.dto.RouteRequestDto;
import org.example.shippingplanner.ws.dto.RouteResponseDto;

public interface RoutePlanningService {
    //Planifier un itinéraire optimal en fonction de critères complexes (coût, temps, perturbations, etc.)
    RouteResponseDto planRoute(RouteRequestDto request);

}
