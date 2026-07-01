package my_project.DistanceOnEarth.distance_service.DadataAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadataResponseDTO {
    private String result;
    @JsonProperty("geo_lat")
    private double geoLat;
    @JsonProperty("geo_lon")
    private double geoLon;
    @JsonProperty("qc_geo")
    private Integer qcGeo;
}