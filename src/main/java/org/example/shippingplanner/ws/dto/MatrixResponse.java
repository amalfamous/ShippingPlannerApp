package org.example.shippingplanner.ws.dto;
import lombok.Data;

/**
 * Représente la réponse de l'API OpenRouteService pour la matrice distances.
 * Ex. JSON renvoyé :
 * {
 *   "distances": [[0, 12000], [12000, 0]],
 *   "durations": [[0, 600], [600, 0]],
 *   ...
 * }
 */
@Data
public class MatrixResponse {
    /**
     * distances[i][j] = distance en mètres entre le point i et j
     */
    private double[][] distances;

    /**
     * durations[i][j] = durée en secondes entre le point i et j
     * (optionnel, si vous voulez aussi la durée).
     */
    private double[][] durations;
}

