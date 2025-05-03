package org.example.shippingplanner.ws.facade;

import jakarta.validation.Valid;
import org.example.shippingplanner.bean.RouteRequest;
import org.example.shippingplanner.bean.RouteResponse;
import org.example.shippingplanner.service.facade.RoutePlanningService;
import org.example.shippingplanner.ws.converter.RoutePlanningConverter;
import org.example.shippingplanner.ws.dto.RouteRequestDto;
import org.example.shippingplanner.ws.dto.RouteResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/routes/")
@CrossOrigin("*")
public class RoutePlanningProvided {
    @Autowired private RoutePlanningService service;
    @Autowired private RoutePlanningConverter converter;
    /**
     * Planifie un itinéraire selon les paramètres fournis.
     * @param dto les codes d'origine, de destination, modes de transport et priorité
     * @return réponse contenant les étapes, coût, temps et justification
     */
    @PostMapping("plan")
    public ResponseEntity<RouteResponseDto> planRoute(@Valid @RequestBody RouteRequestDto dto) {
        RouteRequest bean = converter.toBean(dto);
        // Calcul de la route
        RouteResponse response = service.planRoute(bean);
        // Conversion bean -> DTO
        RouteResponseDto dtoResponse = converter.toDto(response);
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }
    /**
     * Endpoint de test pour vérifier la disponibilité de l'API.
     */
    @GetMapping("health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("RoutePlanning API is up and running");
    }


}
