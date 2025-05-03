
package org.example.shippingplanner.ws.facade;

import org.example.shippingplanner.service.facade.ExternalMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/maps/")
@CrossOrigin("*")
public class ExternalMapServiceProvided {

    @Autowired
    private ExternalMapService externalMapService;

    /**
     * Estime la distance routière (en km) entre deux points.
     */
    @GetMapping("distance")
    public ResponseEntity<Double> estimateDistance(
            @RequestParam double lat1,
            @RequestParam double lon1,
            @RequestParam double lat2,
            @RequestParam double lon2) {
        double distanceKm = externalMapService.estimateDistanceKm(lat1, lon1, lat2, lon2);
        return ResponseEntity.ok(distanceKm);
    }
}