package org.example.shippingplanner.bean;

import lombok.Data;

import java.util.List;

@Data
public class RouteResponse {
    private List<String> steps; // codes des noeuds à traverser
    private double totalCost;
    private double totalTime;
    private String rationale; // pourquoi ce chemin ? (e.g. "Shortest Time")
}
