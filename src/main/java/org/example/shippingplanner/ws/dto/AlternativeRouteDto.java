package org.example.shippingplanner.ws.dto;

import lombok.Data;

import java.util.List;
//permet d'afficher plusieurs itinéraires à l’utilisateur (utile dans un scénario de comparaison ou simulation).
//C’est une autre route possible, calculée selon une perturbation hypothétique ou un autre critère d’optimisation.
@Data
public class AlternativeRouteDto {
    private List<String> steps; // codes des noeuds
    private List<CoordinateDto> coordinates;
    private double totalCost;
    private double totalTime;
    private String rationale; // "Alternative (moins cher)", "Sans perturbation", etc.
}
