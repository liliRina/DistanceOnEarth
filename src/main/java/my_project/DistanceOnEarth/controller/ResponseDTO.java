package my_project.DistanceOnEarth.controller;

public record ResponseDTO(double yandexLat,
                          double yandexLon,
                          double dadataLat,
                          double dadataLon,
                          double distance) {}
