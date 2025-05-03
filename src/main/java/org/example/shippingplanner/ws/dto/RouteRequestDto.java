package org.example.shippingplanner.ws.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
@Data
public class RouteRequestDto {
    private Long id;
    private String code;
    @NotEmpty
    private String originCode;
    @NotEmpty
    private String destinationCode;
    @NotEmpty
    private List<String> transportModes;  // e.g. ["ROAD","RAIL"]
    @NotEmpty
    private String priority;           // "COST", "TIME", "EMISSIONS"
}
