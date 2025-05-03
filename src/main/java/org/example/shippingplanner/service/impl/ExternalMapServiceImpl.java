package org.example.shippingplanner.service.impl;
/*
Appelle l’API publique d’OSRM.

Récupère la distance routière réelle entre deux points (lat/lon).
*/

import org.example.shippingplanner.service.facade.ExternalMapService;
import org.example.shippingplanner.ws.dto.MatrixResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Map;

@Service
public class ExternalMapServiceImpl implements ExternalMapService {

    @Value("${openrouteservice.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public ExternalMapServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public double estimateDistanceKm(double lat1, double lon1, double lat2, double lon2) {
        String url = "https://api.openrouteservice.org/v2/matrix/driving-car";

        // 1) Construire le corps JSON de la requête
        Map<String, Object> body = Map.of(
                "locations", List.of(
                        List.of(lon1, lat1),
                        List.of(lon2, lat2)
                ),
                "metrics", List.of("distance")
        );

        // 2) Préparer les headers Spring
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // ORS attend la clé dans "Authorization"
        headers.set("Authorization", apiKey);

        HttpEntity<Map<String,Object>> request = new HttpEntity<>(body, headers);

        // 3) Appel POST + binding automatique en MatrixResponse
        MatrixResponse resp = restTemplate
                .postForObject(url, request, MatrixResponse.class);

        if (resp == null || resp.getDistances() == null) {
            throw new RuntimeException("Impossible de récupérer la matrice distances");
        }

        // 4) distances en mètres → km
        double meters = resp.getDistances()[0][1];
        return meters / 1000.0;
    }

    // Méthode pour extraire la distance en km de la réponse JSON de l'API
    private double extractDistanceFromResponse(String response) {
        // Assumer que la réponse est un JSON au format suivant :
        // { "distances" : [[0, 1567.5]] } (en km)
        // Tu peux utiliser une bibliothèque JSON comme Jackson pour le parsing réel

        String[] parts = response.split(":");
        String distanceString = parts[1].split("}")[0].trim();
        return Double.parseDouble(distanceString);
    }


}
