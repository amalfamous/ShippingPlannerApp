package org.example.shippingplanner.ws.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
@Data
public class RouteRequestDto {
    @NotEmpty
    private String originCode;
    @NotEmpty
    private String destinationCode;
    @NotEmpty
    private List<String> transportModes; // ["ROAD", "RAIL", "AIR", …]
    @NotEmpty
    private String priority;           // "COST", "TIME", "EMISSIONS"
}
