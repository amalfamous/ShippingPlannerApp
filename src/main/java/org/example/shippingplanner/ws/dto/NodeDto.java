package org.example.shippingplanner.ws.dto;

import lombok.Data;

@Data
public class NodeDto {
    private Long id;
    // Nom du point (ville, port, gare…)
    private String name;
    private String code ;
    // Coordonnées géographiques
    private double latitude;
    private double longitude;
    // Type de nœud (ex. “CITY”, “PORT”, “RAIL_STATION”, etc.)
    private String nodeType;
}
