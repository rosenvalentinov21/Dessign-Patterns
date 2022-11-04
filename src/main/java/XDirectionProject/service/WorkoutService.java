package XDirectionProject.service;

import XDirectionProject.dto.ExerciseDTO;
import XDirectionProject.model.lifters.Workout;

import java.util.List;

public interface WorkoutService {
    public Workout createWorkout(Workout workout, List<ExerciseDTO> exerciseDTO, String email);
}
