package org.example.shippingplanner.service.facade;

import org.example.shippingplanner.bean.RouteRequest;
import org.example.shippingplanner.bean.RouteResponse;

public interface RoutePlanningService {
    //Planifier un itinéraire optimal en fonction de critères complexes (coût, temps, perturbations, etc.)
    RouteResponse planRoute(RouteRequest request);
    /**
     * Planifie un itinéraire en appliquant en plus des perturbations “what-if”
     * @param scenarioReq contient la requête de base + liste de PerturbationDto à injecter
     * @return réponse contenant la route principale + alternatives
     */
    RouteScenarioResponseDto planRouteWithScenario(RouteScenarioRequestDto scenarioReq);
}