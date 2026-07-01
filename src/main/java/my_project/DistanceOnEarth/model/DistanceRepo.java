package my_project.DistanceOnEarth.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface DistanceRepo extends JpaRepository<DistanceEntity, Long> {
    boolean existsByAddress(String address);
}
