package org.example.shippingplanner.service.impl;

import org.example.shippingplanner.bean.*;
import org.example.shippingplanner.service.facade.RoutePlanningService;
import org.example.shippingplanner.ws.dto.RouteRequestDto;
import org.example.shippingplanner.ws.dto.RouteResponseDto;
import org.jgrapht.Graph;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoutePlanningServiceImpl implements RoutePlanningService {
    @Autowired
    private NodeFacade nodeFacade;

    @Autowired
    private EdgeFacade edgeFacade;

    @Autowired
    private PerturbationFacade perturbationFacade;
    @Override
    public RouteResponseDto planRoute(RouteRequestDto request) {
        // 1. Charger les noeuds et arêtes
        List<Node> nodes = nodeFacade.findAll();
        List<Edge> edges = edgeFacade.findAll();
        List<Perturbation> perturbations = perturbationFacade.findAll();

        // 2. Construire le graphe à partir des edges et nodes
        Graph graph = new Graph(nodes, edges, perturbations);

        // 3. Appliquer l’algorithme (Dijkstra, A*, etc.)
        List<Edge> bestPath = graph.findOptimalPath(
                request.getOriginCode(),
                request.getDestinationCode(),
                request.getPriority()
        );

        // 4. Construire la réponse
        return new RouteResponseDto(bestPath);
    }
}
