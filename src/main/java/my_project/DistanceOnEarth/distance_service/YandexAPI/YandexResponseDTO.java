package my_project.DistanceOnEarth.distance_service.YandexAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class YandexResponseDTO {

    private Response response;
    @JsonIgnore
    private double lat;
    @JsonIgnore
    private double lon;

    public YandexResponseDTO(Response response){
        if (response != null && !response.geoObjectCollection.featureMember.isEmpty()) {
            lat = Double.parseDouble(response.geoObjectCollection.featureMember.get(0).GeoObject.Point.pos.split(" ")[1]);
            lon = Double.parseDouble(response.geoObjectCollection.featureMember.get(0).GeoObject.Point.pos.split(" ")[0]);
        }
        this.response = response;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response {
        @JsonProperty("GeoObjectCollection")
        private GeoObjectCollection geoObjectCollection;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GeoObjectCollection {
        private List<FeatureMember> featureMember;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FeatureMember {
        @JsonProperty("GeoObject")
        private GeoObject GeoObject;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GeoObject {
        @JsonProperty("Point")
        private Point Point;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Point {
        private String pos;
    }
    @Override
    public String toString(){
        return "lat: " + lat + " lon: " + lon;
    }
}