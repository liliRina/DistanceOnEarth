package my_project.DistanceOnEarth.distance_service.YandexAPI;

import my_project.DistanceOnEarth.distance_service.Coordinates;
import my_project.DistanceOnEarth.controller.RequestDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class YandexApiService {
    WebClient webClient;

    @Value("${yandex.api.key}")
    private String yandexApiKey;

    YandexApiService(@Qualifier("yandexWebClient") WebClient webClient) {
        this.webClient = webClient;
    }
    public Optional<Coordinates> getResponse(RequestDTO requestDTO) {
        YandexResponseDTO response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("apikey", yandexApiKey)
                        .queryParam("geocode", requestDTO.getAddress())
                        .queryParam("format", "json")
                        .queryParam("results", 2)
                        .build())
                .retrieve()
                .bodyToMono(YandexResponseDTO.class)
                .block();
        if (response == null
                || response.getResponse().getGeoObjectCollection().getFeatureMember().size() != 1)
            return Optional.empty();

        return Optional.of(new Coordinates(response.getLat(), response.getLon()));
    }
}