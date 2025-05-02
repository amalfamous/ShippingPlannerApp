package org.example.shippingplanner.ws.dto;


import lombok.Data;

import java.util.List;
@Data
public class RouteRequestDto {
    private String originCode;
    private String destinationCode;
    private List<String> transportModes; // ["ROAD", "RAIL", "AIR"]
    private String priority; // "COST", "TIME", "EMISSIONS"
}
