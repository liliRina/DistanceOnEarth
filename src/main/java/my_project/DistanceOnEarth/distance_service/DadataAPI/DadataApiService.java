package my_project.DistanceOnEarth.distance_service.DadataAPI;

import my_project.DistanceOnEarth.distance_service.Coordinates;
import my_project.DistanceOnEarth.controller.RequestDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class DadataApiService {

    WebClient webClient;
    public DadataApiService(@Qualifier("dadataWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Optional<Coordinates> getResponse(RequestDTO requestDTO){
        DadataResponseDTO[] response = webClient.post()
                .bodyValue(List.of(requestDTO.getAddress()))
                .retrieve()
                .bodyToMono(DadataResponseDTO[].class)
                .block();
        if (response == null || response.length != 1 || response[0].getQcGeo() != 0){
            return Optional.empty();
        }
        return Optional.of(new Coordinates(response[0].getGeoLat(),
                response[0].getGeoLon(), response[0].getResult()));
    }
}