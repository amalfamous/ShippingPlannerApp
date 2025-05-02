package org.example.shippingplanner.bean;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class RouteResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String code;
    @ElementCollection
    @CollectionTable(
            name = "response_steps",
            joinColumns = @JoinColumn(name = "response_id")
    )
    @Column(name = "node_code")
    private List<String> steps;

    private double totalCost;
    private double totalTime;
    private String rationale;    // ex. "Optimal selon cost"
}
