package org.example.shippingplanner.bean;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Edge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code ;

    // Nœud de départ
    @ManyToOne
    @JoinColumn(name = "source_node_id")
    private Node source;

    // Nœud d’arrivée
    @ManyToOne
    @JoinColumn(name = "target_node_id")
    private Node target;

    // Distance en kilomètres
    @Column(nullable = false)
    private double distanceKm;

    // Durée en heures
    @Column(nullable = false)
    private double durationHours;

    // Coût estimé pour ce segment
    @Column(nullable = false)
    private double cost;

    // Mode de transport (e.g., "ROAD", "RAIL", "SEA", "AIR")
    @Column(nullable = false)
    private String transportMode;

    // Émissions de CO₂ estimées (kg)
    @Column
    private Double co2EmissionKg;

}
