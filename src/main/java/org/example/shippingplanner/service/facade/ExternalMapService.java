package org.example.shippingplanner.service.facade;

public interface ExternalMapService {
    double estimateDistanceKm(double lat1, double lon1, double lat2, double lon2);
}
