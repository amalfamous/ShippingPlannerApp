package org.example.shippingplanner.ws.dto;

import lombok.Data;
import org.example.shippingplanner.bean.Edge;

import java.time.LocalDateTime;

@Data
public class PerturbationDto {
    private Long id;
    private String code ;
    // Segment impacté par la perturbation
    private Edge edge;
    // Type (e.g., "FLOOD", "STRIKE", "PORT_CLOSURE")
    private String type;
    // Facteur multiplicatif du coût (1.2 = +20%)
    private double costFactor;
    // Facteur multiplicatif de la durée (1.5 = +50%)
    private double durationFactor;
    // Plage temporelle de validité de la perturbation
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
