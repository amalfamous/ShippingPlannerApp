package org.example.shippingplanner.service.facade;

public interface RoutePlanningService {
    //Planifier un itinéraire optimal en fonction de critères complexes (coût, temps, perturbations, etc.)
    RouteResponse planRoute(RouteRequest request);

}
