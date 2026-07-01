package my_project.DistanceOnEarth.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my_project.DistanceOnEarth.distance_service.DistanceCalculator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class DistanceController {
    private final DistanceCalculator distanceCalculator;
    @PostMapping("/api/geo/")
    public ResponseEntity<ResponseDTO> calculate(@Valid @RequestBody RequestDTO requestDTO) {
        log.info("Введён адрес: {}", requestDTO.getAddress());
        return ResponseEntity.ok(distanceCalculator.calculateDistance(requestDTO));
    }
}
