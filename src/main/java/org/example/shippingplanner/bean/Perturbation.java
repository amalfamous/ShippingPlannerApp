package org.example.shippingplanner.bean;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
public class Perturbation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Segment impacté par la perturbation
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "edge_id")
    private Edge edge;

    // Type (e.g., "FLOOD", "STRIKE", "PORT_CLOSURE")
    @Column(nullable = false)
    private String type;

    // Facteur multiplicatif du coût (1.2 = +20%)
    @Column(nullable = false)
    private double costFactor;

    // Facteur multiplicatif de la durée (1.5 = +50%)
    @Column(nullable = false)
    private double durationFactor;

    // Plage temporelle de validité de la perturbation
    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

}
