package my_project.DistanceOnEarth.distance_service;

public record Coordinates(double lat, double lon, String address){
    public Coordinates(double lat, double lon) {
        this(lat, lon, "");
    }
};
