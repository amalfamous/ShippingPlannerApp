package org.example.shippingplanner.service.impl;

import org.example.shippingplanner.bean.*;
import org.example.shippingplanner.dao.EdgeDao;
import org.example.shippingplanner.dao.NodeDao;
import org.example.shippingplanner.dao.PerturbationDao;
import org.example.shippingplanner.service.facade.RoutePlanningService;
import org.example.shippingplanner.ws.converter.*;
import org.example.shippingplanner.ws.dto.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoutePlanningServiceImpl implements RoutePlanningService {

    @Autowired private NodeDao nodeDao;
    @Autowired private EdgeDao edgeDao;
    @Autowired private PerturbationDao perturbationDao;
    @Autowired private RouteRequestConverter routeRequestConverter;
    @Autowired private RouteResponseConverter routeResponseConverter;
    @Autowired private PerturbationConverter perturbationConverter;

    @Override
    public RouteResponse planRoute(RouteRequest request) {
        return computeRouteWithPerturbations(request, perturbationDao.findAll());
    }

    private RouteResponse computeRouteWithPerturbations(RouteRequest request, List<Perturbation> perturbations) {
        List<Node> nodes = nodeDao.findAll();
        Graph<Node, Edge> graph = new DefaultDirectedWeightedGraph<>(Edge.class);
        Map<String, Node> codeToNode = new HashMap<>();

        for (Node node : nodes) {
            graph.addVertex(node);
            codeToNode.put(node.getCode(), node);
        }

        List<Edge> edges = edgeDao.findAll();
        for (Edge edge : edges) {
            if (request.getTransportModes().contains(edge.getTransportMode())) {
                double cost = edge.getCost();
                double duration = edge.getDurationHours();

                for (Perturbation p : perturbations) {
                    if (p.getEdge().getId().equals(edge.getId())) {
                        switch (p.getType()) {
                            case "DELAY" -> duration *= p.getDurationFactor();
                            case "COST_INCREASE" -> cost *= p.getCostFactor();
                            case "ALL" -> {
                                cost *= p.getCostFactor();
                                duration *= p.getDurationFactor();
                            }
                        }
                    }
                }

                graph.addEdge(edge.getSource(), edge.getTarget(), edge);
                switch (request.getPriority()) {
                    case "TIME" -> graph.setEdgeWeight(edge, duration);
                    case "EMISSIONS" -> graph.setEdgeWeight(edge,
                            edge.getCo2EmissionKg() != null ? edge.getCo2EmissionKg() : cost);
                    default -> graph.setEdgeWeight(edge, cost);
                }
            }
        }

        Node origin = codeToNode.get(request.getOriginCode());
        Node destination = codeToNode.get(request.getDestinationCode());

        var path = new DijkstraShortestPath<>(graph).getPath(origin, destination);
        if (path == null) return null;

        RouteResponse response = new RouteResponse();
        List<String> steps = new ArrayList<>();
        for (Node n : path.getVertexList()) {
            steps.add(n.getCode());
        }

        response.setSteps(steps);
        response.setTotalCost(path.getEdgeList().stream().mapToDouble(Edge::getCost).sum());
        response.setTotalTime(path.getEdgeList().stream().mapToDouble(Edge::getDurationHours).sum());

        Set<Long> edgeIdsInPath = path.getEdgeList().stream()
                .map(Edge::getId)
                .collect(Collectors.toSet());

        Set<String> appliedPerturbationTypes = perturbations.stream()
                .filter(p -> edgeIdsInPath.contains(p.getEdge().getId()))
                .map(Perturbation::getType)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        String pertTypes = String.join(", ", appliedPerturbationTypes);

        response.setRationale("Optimal selon " + request.getPriority().toLowerCase()
                + (!pertTypes.isEmpty() ? " avec perturbation: " + pertTypes : ""));

        return response;
    }

    @Override
    public RouteScenarioResponseDto planRouteWithScenario(RouteScenarioRequestDto scenarioReq) {
        // 1) Construire l'objet RouteRequest à partir du DTO
        RouteRequest request = new RouteRequest();
        request.setOriginCode(scenarioReq.getOriginCode());
        request.setDestinationCode(scenarioReq.getDestinationCode());
        request.setTransportModes(scenarioReq.getTransportModes());
        request.setPriority(scenarioReq.getPriority());

        // 2) Main route SANS perturbations
        List<Perturbation> noPerturbs = Collections.emptyList();
        RouteResponse mainRoute = computeRouteWithPerturbations(request, noPerturbs);

        // 3) Alternative AVEC uniquement les perturbations "what-if"
        List<Perturbation> scenarioPerturbs = scenarioReq.getPerturbations().stream()
                .map(perturbationConverter::toBean)
                .collect(Collectors.toList());
        RouteResponse alternativeRoute = computeRouteWithPerturbations(request, scenarioPerturbs);

        // 4) Construction du DTO de réponse
        RouteScenarioResponseDto dto = new RouteScenarioResponseDto();
        dto.setMainRoute(routeResponseConverter.toDto(mainRoute));

        AlternativeRouteDto altDto = new AlternativeRouteDto();
        altDto.setSteps(alternativeRoute.getSteps());
        altDto.setTotalCost(alternativeRoute.getTotalCost());
        altDto.setTotalTime(alternativeRoute.getTotalTime());
        altDto.setRationale(alternativeRoute.getRationale());
        altDto.setCoordinates(null); // à remplir ultérieurement si vous avez un calcul de géométrie

        dto.setAlternatives(List.of(altDto));
        return dto;
    }

}
