package org.example.shippingplanner.ws.dto;


import lombok.Data;

import java.util.List;
@Data
public class RouteResponseDto {
    private List<String> steps; // codes des noeuds à traverser
    private double totalCost;
    private double totalTime;
    private String rationale; // pourquoi ce chemin ? (e.g. "Shortest Time")
}

