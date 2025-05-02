package org.example.shippingplanner.service.impl;

import org.example.shippingplanner.bean.Edge;
import org.example.shippingplanner.bean.Node;
import org.example.shippingplanner.bean.Perturbation;
import org.example.shippingplanner.bean.RouteRequest;
import org.example.shippingplanner.bean.RouteResponse;
import org.example.shippingplanner.dao.EdgeDao;
import org.example.shippingplanner.dao.NodeDao;
import org.example.shippingplanner.dao.PerturbationDao;
import org.example.shippingplanner.service.facade.RoutePlanningService;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoutePlanningServiceImpl implements RoutePlanningService {

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private EdgeDao edgeDao;

    @Autowired
    private PerturbationDao perturbationDao;

    @Override
    public RouteResponse planRoute(RouteRequest request) {
        // 1. Charger les données
        List<Node> nodes = nodeDao.findAll();
        List<Edge> edges = edgeDao.findAll();
        List<Perturbation> perturbations = perturbationDao.findAll();

        // 2. Construire le graphe pondéré
        Graph<Node, Edge> graph = new DefaultDirectedWeightedGraph<>(Edge.class);
        Map<String, Node> codeToNode = new HashMap<>();
        for (Node node : nodes) {
            graph.addVertex(node);
            codeToNode.put(node.getCode(), node);
        }

        for (Edge edge : edges) {
            if (request.getTransportModes().contains(edge.getTransportMode())) {
                double cost = edge.getCost();
                double duration = edge.getDurationHours();

                for (Perturbation p : perturbations) {
                    if (p.getEdge().getId().equals(edge.getId())) {
                        cost *= p.getCostFactor();
                        duration *= p.getDurationFactor();
                    }
                }

                graph.addEdge(edge.getSource(), edge.getTarget(), edge);
                switch (request.getPriority()) {
                    case "TIME" -> graph.setEdgeWeight(edge, duration);
                    case "EMISSIONS" -> graph.setEdgeWeight(edge, edge.getCo2EmissionKg() != null ? edge.getCo2EmissionKg() : cost);
                    default -> graph.setEdgeWeight(edge, cost);
                }
            }
        }

        // 3. Trouver le chemin optimal
        Node origin = codeToNode.get(request.getOriginCode());
        Node destination = codeToNode.get(request.getDestinationCode());
        if (origin == null || destination == null) {
            throw new IllegalArgumentException("Origine ou destination introuvable");
        }

        var dijkstra = new DijkstraShortestPath<>(graph);
        var path = dijkstra.getPath(origin, destination);
        if (path == null) {
            throw new RuntimeException("Aucun chemin trouvé.");
        }

        // 4. Construire la réponse
        RouteResponse response = new RouteResponse();
        List<String> steps = new ArrayList<>();
        for (Node node : path.getVertexList()) {
            steps.add(node.getCode());
        }
        response.setSteps(steps);
        response.setTotalCost(path.getEdgeList().stream().mapToDouble(Edge::getCost).sum());
        response.setTotalTime(path.getEdgeList().stream().mapToDouble(Edge::getDurationHours).sum());
        response.setRationale("Optimal selon " + request.getPriority().toLowerCase());

        return response;
    }


}
