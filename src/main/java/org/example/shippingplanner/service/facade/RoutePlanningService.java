package org.example.shippingplanner.service.facade;

import org.example.shippingplanner.bean.RouteRequest;
import org.example.shippingplanner.bean.RouteResponse;

public interface RoutePlanningService {
    //Planifier un itinéraire optimal en fonction de critères complexes (coût, temps, perturbations, etc.)
    RouteResponse planRoute(RouteRequest request);

}
