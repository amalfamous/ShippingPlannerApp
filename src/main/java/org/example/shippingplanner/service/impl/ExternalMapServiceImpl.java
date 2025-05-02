package org.example.shippingplanner.service.impl;
/*
Appelle l’API publique d’OSRM.

Récupère la distance routière réelle entre deux points (lat/lon).
*/

import org.example.shippingplanner.service.facade.ExternalMapService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
        // URL de l'API OpenRouteService pour obtenir les distances
        String url = "https://api.openrouteservice.org/v2/matrix/driving-car";

        // Construire les coordonnées pour l'API (format : longitude, latitude)
        String coordinates = String.format("[[%f,%f],[%f,%f]]", lon1, lat1, lon2, lat2);

        // Construire l'URI
        String finalUrl = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("api_key", apiKey)
                .queryParam("locations", coordinates)
                .toUriString();

        // Faire la requête
        String response = restTemplate.getForObject(finalUrl, String.class);

        // Extraire la distance du JSON de réponse (supposons que la distance est en km)
        return extractDistanceFromResponse(response);
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
