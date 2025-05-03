package org.example.shippingplanner.ws.dto;

import lombok.Data;
import org.example.shippingplanner.bean.Node;

import java.io.Serializable;

@Data
public class EdgeDto implements Serializable {
    private Long id;
    private String code ;
    // Nœud de départ
    private Node source;
    // Nœud d’arrivée
    private Node target;
    // Distance en kilomètres
    private double distanceKm;
    // Durée en heures
    private double durationHours;
    // Coût estimé pour ce segment
    private double cost;
    // Mode de transport (e.g., "ROAD", "RAIL", "SEA", "AIR")
    private String transportMode;
    // Émissions de CO₂ estimées (kg)
    private Double co2EmissionKg;
}
