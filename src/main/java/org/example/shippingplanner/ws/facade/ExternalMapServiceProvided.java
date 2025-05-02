
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
     * Exemple d'appel: GET /api/v1/maps/distance?lat1=48.8566&lon1=2.3522&lat2=51.5074&lon2=-0.1278
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