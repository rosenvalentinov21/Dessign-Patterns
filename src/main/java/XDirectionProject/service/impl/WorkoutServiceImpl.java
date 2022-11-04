package XDirectionProject.service.impl;

import XDirectionProject.dto.ExerciseDTO;
import XDirectionProject.exception.NonExistingEntityException;
import XDirectionProject.model.TrainingLog;
import XDirectionProject.model.lifters.Exercise;
import XDirectionProject.model.lifters.Lifter;
import XDirectionProject.model.lifters.Workout;
import XDirectionProject.repository.ExerciseRepository;
import XDirectionProject.repository.LifterRepository;
import XDirectionProject.repository.TrainingLogRepository;
import XDirectionProject.repository.WorkoutRepository;
import XDirectionProject.service.WorkoutService;
import XDirectionProject.utillity.Constants;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private WorkoutRepository workoutRepository;
    private LifterRepository lifterRepository;
    private TrainingLogRepository trainingLogRepository;
    private ExerciseRepository exerciseRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, LifterRepository lifterRepository, ExerciseRepository exerciseRepository, TrainingLogRepository trainingLogRepository) {
        this.workoutRepository = workoutRepository;
        this.lifterRepository = lifterRepository;
        this.exerciseRepository = exerciseRepository;
        this.trainingLogRepository = trainingLogRepository;
    }

    @Override
    public Workout createWorkout(Workout workout, List<ExerciseDTO> exerciseDTOS, String email) {
        saveExercisesFromTheWorkout(exerciseDTOS);
        Workout savedWorkout = setWorkoutDetails(workout, exerciseDTOS, email);
        addWorkoutToLiftersList(email, savedWorkout);
        return savedWorkout;
    }

    private Workout setWorkoutDetails(Workout workout, List<ExerciseDTO> exerciseDTO, String email) {
        workout.setDate(LocalDateTime.now());
        workout.setWeightMoved(calculateTotalWeightMoved(exerciseDTO));
        workout.setPersonalRecords(calculatePRs(exerciseDTO));
        workout.setExercisesLog(createTrainingLog(exerciseDTO, email));

        return workoutRepository.save(workout);
    }

    private void addWorkoutToLiftersList(String email, Workout workout) {
        Lifter lifter = lifterRepository.findLifterByEmail(email)
                .orElseThrow(() -> new NonExistingEntityException(Constants.NON_EXISTING_ENTITY));
        lifter.getWorkouts().add(workout);
        lifterRepository.save(lifter);
    }

    private void saveExercisesFromTheWorkout(List<ExerciseDTO> exercisesDTO) {
        List<Exercise> exercises = new ArrayList<>();
        exercisesDTO.forEach(exercise -> {
            exercises.add(
                    new Exercise(String.valueOf(
                            Objects.hash(exercise.getName(), exercise.getBodyPart(), exercise.getCategory())),
                            exercise.getName(), exercise.getCategory(), exercise.getBodyPart()));
        });
        exerciseRepository.saveAll(exercises);
    }

    private List<TrainingLog> createTrainingLog(List<ExerciseDTO> exercisesDTO, String email) {
        Lifter lifter = lifterRepository.findLifterByEmail(email)
                .orElseThrow(() -> new NonExistingEntityException(Constants.NON_EXISTING_ENTITY));

        List<TrainingLog> trainingLogs = new ArrayList<>();
        exercisesDTO.forEach(exercise -> {
            Exercise foundExercise = exerciseRepository
                    .findExerciseByEmail(String.valueOf(Objects.hash(exercise.getName(), exercise.getBodyPart())))
                    .orElse(exerciseRepository.save(
                            new Exercise(String.valueOf(
                                    Objects.hash(exercise.getName(), exercise.getBodyPart(), exercise.getCategory())),
                                    exercise.getName(), exercise.getCategory(), exercise.getBodyPart())));

            trainingLogs.add(new TrainingLog(null, lifter, foundExercise, exercise.getSets(), exercise.getReps()));
        });
        return trainingLogRepository.saveAll(trainingLogs);
    }

    private double calculateTotalWeightMoved(List<ExerciseDTO> exercises) {
        double weightMoved = 0;
        for (ExerciseDTO e : exercises) {
            weightMoved += e.getSets() * e.getReps();
        }
        return weightMoved;
    }

    private int calculatePRs(List<ExerciseDTO> exercises) {
        return 0;
    }

}
