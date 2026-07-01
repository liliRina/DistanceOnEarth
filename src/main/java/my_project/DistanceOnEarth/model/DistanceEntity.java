package my_project.DistanceOnEarth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "distance_requests")
@NoArgsConstructor
public class DistanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public DistanceEntity(String address,
                          double yandexLat,
                          double yandexLon,
                          double dadataLat,
                          double dadataLon,
                          double distance){
        this.address = address;
        this.yandexLat = yandexLat;
        this.yandexLon = yandexLon;
        this.dadataLat = dadataLat;
        this.dadataLon = dadataLon;
        this.distance = distance;
    }
    @NotBlank
    @Column(unique = true)
    private String address;
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    private double yandexLat;
    @DecimalMin(value = "-180.0", message = "Longitude must be greater than or equal to -180.0")
    @DecimalMax(value = "180.0", message = "Longitude must be less than or equal to 180.0")
    private double yandexLon;
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")

    private double dadataLat;
    @DecimalMin(value = "-180.0", message = "Longitude must be greater than or equal to -180.0")
    @DecimalMax(value = "180.0", message = "Longitude must be less than or equal to 180.0")
    private double dadataLon;

    @DecimalMin(value = "0.0", message = "Distance must be greater than or equal to 0")
    private double distance;
}
