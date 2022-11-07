package XDirectionProject.controller;

import XDirectionProject.dto.ExerciseDTO;
import XDirectionProject.dto.PageDTO;
import XDirectionProject.dto.WorkoutCriteriaDTO;
import XDirectionProject.dto.WorkoutDTO;
import XDirectionProject.model.lifters.Workout;
import XDirectionProject.service.WorkoutService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/workout")
public class WorkoutController {
    private WorkoutService workoutService;
    private ModelMapper modelMapper;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
        modelMapper = new ModelMapper();
    }

    @PostMapping("/add-workout/{email}")
    public ResponseEntity<WorkoutDTO> createWorkout(@RequestBody WorkoutDTO workoutDTO, @PathVariable(name = "email") String email) {
        Workout newWorkout = modelMapper.map(workoutDTO, Workout.class);

        return new ResponseEntity(
                createReturnDTOofWorkout(workoutService.createWorkout(newWorkout, workoutDTO.getExercises(), email)), HttpStatus.OK);
    }

    @GetMapping("/find-all-workouts-with-filters")
    public ResponseEntity<Page<Workout>> findAllWorkoutsWithFilters(PageDTO pageDTO, WorkoutCriteriaDTO workoutCriteriaDTO) {
        Page<Workout> workouts = workoutService.findAllWithFilters(pageDTO, workoutCriteriaDTO);
        Page<WorkoutDTO> returnPage = createPageWithWorkouts(workouts.toList());
        return new ResponseEntity(returnPage, HttpStatus.OK);
    }

    private WorkoutDTO createReturnDTOofWorkout(Workout savedWorkout) {
        ArrayList<ExerciseDTO> exerciseDTOS = new ArrayList<>();
        WorkoutDTO returnDTO = modelMapper.map(savedWorkout, WorkoutDTO.class);
        savedWorkout.getExercisesLog().forEach(ex -> {
            ExerciseDTO exerciseDTO = modelMapper.map(ex.getExercise(), ExerciseDTO.class);
            exerciseDTO.setReps(ex.getReps());
            exerciseDTO.setSets(ex.getSets());
            exerciseDTOS.add(exerciseDTO);
        });
        returnDTO.setExercises(exerciseDTOS);

        return returnDTO;
    }

    private Page<WorkoutDTO> createPageWithWorkouts(List<Workout> workoutList) {
        List<WorkoutDTO> workoutDTOS = new ArrayList<>();
        for (Workout workout : workoutList) {
            workoutDTOS.add(createReturnDTOofWorkout(workout));
        }

        return new PageImpl<>(workoutDTOS);
    }
}
