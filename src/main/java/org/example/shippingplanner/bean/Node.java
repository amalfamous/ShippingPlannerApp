package org.example.shippingplanner.bean;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nom du point (ville, port, gare…)
    @Column(nullable = false, unique = true)
    private String name;
    private String code ;

    // Coordonnées géographiques
    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    // Type de nœud (ex. “CITY”, “PORT”, “RAIL_STATION”, etc.)
    @Column(nullable = false)
    private String nodeType;

}
