package org.example.shippingplanner.bean;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class RouteRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String originCode;
    @Column(unique = true, nullable = false)
    private String code;
    @Column(nullable = false)
    private String destinationCode;

    @ElementCollection
    @CollectionTable(
            name = "request_transport_modes",
            joinColumns = @JoinColumn(name = "request_id")
    )
    @Column(name = "mode")
    private List<String> transportModes;

    @Column(nullable = false)
    private String priority;     // "COST", "TIME" ou "EMISSIONS"
}
