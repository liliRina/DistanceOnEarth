package my_project.DistanceOnEarth.distance_service;

import lombok.RequiredArgsConstructor;
import my_project.DistanceOnEarth.controller.ResponseDTO;
import my_project.DistanceOnEarth.model.DistanceEntity;
import my_project.DistanceOnEarth.model.DistanceRepo;
import my_project.DistanceOnEarth.controller.RequestDTO;
import my_project.DistanceOnEarth.distance_service.DadataAPI.DadataApiService;
import my_project.DistanceOnEarth.distance_service.YandexAPI.YandexApiService;
import my_project.DistanceOnEarth.exceptions.AmbiguousAddressException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistanceCalculator {
    private final DadataApiService dadataApiService;
    private final YandexApiService yandexApiService;
    private final DistanceRepo distanceRepo;
    private final double EARTH_RADIUS = 6_371_008.7714;

    public ResponseDTO calculateDistance(RequestDTO requestDTO) {
        Coordinates yandexCoordinates = yandexApiService.getResponse(requestDTO)
                .orElseThrow(() -> new AmbiguousAddressException(requestDTO.getAddress()));
        Coordinates dadataCoordinates = dadataApiService.getResponse(requestDTO)
                .orElseThrow(() -> new AmbiguousAddressException(requestDTO.getAddress()));

        double lat1 = yandexCoordinates.lat();
        double lon1 = yandexCoordinates.lon();
        double lat2 = dadataCoordinates.lat();
        double lon2 = dadataCoordinates.lon();

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(Math.min(1.0, Math.max(0.0, a))));
        double res_m = EARTH_RADIUS * c;

        if (!distanceRepo.existsByAddress(requestDTO.getAddress())) {
            DistanceEntity distanceEntity = new DistanceEntity(dadataCoordinates.address()
                    , yandexCoordinates.lat()
                    , yandexCoordinates.lon()
                    , dadataCoordinates.lat()
                    , dadataCoordinates.lon()
                    , res_m);
            distanceRepo.save(distanceEntity);
        }
        ResponseDTO responseDTO = new ResponseDTO(yandexCoordinates.lat()
                , yandexCoordinates.lon()
                , dadataCoordinates.lat()
                , dadataCoordinates.lon()
                , res_m);
        return responseDTO;
    }
}