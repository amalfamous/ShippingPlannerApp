package org.example.shippingplanner.ws.dto;

import lombok.Data;
//utile pour tracer le chemin précisément sur la carte (pas juste les villes, mais la ligne).
@Data
public class CoordinateDto {
    private double latitude;
    private double longitude;
}
