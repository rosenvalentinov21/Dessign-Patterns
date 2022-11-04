package XDirectionProject.repository;

import XDirectionProject.model.lifters.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query(value = "SELECT * FROM exercise as e WHERE e.id=:id", nativeQuery = true)
    Optional<Exercise> findExerciseByEmail(String id);
}
