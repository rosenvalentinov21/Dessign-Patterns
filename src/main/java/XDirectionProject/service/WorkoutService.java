package XDirectionProject.service;

import XDirectionProject.dto.ExerciseDTO;
import XDirectionProject.dto.PageDTO;
import XDirectionProject.dto.WorkoutCriteriaDTO;
import XDirectionProject.model.lifters.Workout;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WorkoutService {
     Workout createWorkout(Workout workout, List<ExerciseDTO> exerciseDTO, String email);
     void deleteWorkout(int number,String email);
     Page<Workout> findAllWithFilters(PageDTO pageDTO, WorkoutCriteriaDTO workoutCriteriaDTO);
}
