package XDirectionProject.repository;

import XDirectionProject.model.lifters.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
