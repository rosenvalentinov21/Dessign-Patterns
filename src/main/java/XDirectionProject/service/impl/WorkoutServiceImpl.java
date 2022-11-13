package XDirectionProject.service.impl;

import XDirectionProject.dto.ExerciseDTO;
import XDirectionProject.dto.PageDTO;
import XDirectionProject.dto.WorkoutCriteriaDTO;
import XDirectionProject.exception.NonExistingEntityException;
import XDirectionProject.model.TrainingLog;
import XDirectionProject.model.lifters.Exercise;
import XDirectionProject.model.lifters.Lifter;
import XDirectionProject.model.lifters.Workout;
import XDirectionProject.repository.*;
import XDirectionProject.service.WorkoutService;
import XDirectionProject.utillity.Constants;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private WorkoutRepository workoutRepository;
    private LifterRepository lifterRepository;
    private TrainingLogRepository trainingLogRepository;
    private WorkoutCriteriaSearch workoutCriteriaSearch;
    private ExerciseRepository exerciseRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository,
                              LifterRepository lifterRepository, ExerciseRepository exerciseRepository,
                              TrainingLogRepository trainingLogRepository, WorkoutCriteriaSearch workoutCriteriaSearch) {
        this.workoutRepository = workoutRepository;
        this.lifterRepository = lifterRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutCriteriaSearch = workoutCriteriaSearch;
        this.trainingLogRepository = trainingLogRepository;
    }

    @Override
    public Workout createWorkout(Workout workout, List<ExerciseDTO> exerciseDTOS, String email) {
        saveExercisesFromTheWorkout(exerciseDTOS);
        Workout savedWorkout = setWorkoutDetails(workout, exerciseDTOS, email);
        addWorkoutToLiftersList(email, savedWorkout);
        resetNumberOfWorkouts(email);
        return savedWorkout;
    }

    @Override
    public void deleteWorkout(int numberOfWorkoutToBeRemoved, String email) {
        Lifter lifter = lifterRepository.findLifterByEmail(email)
                .orElseThrow(() -> new NonExistingEntityException(Constants.NON_EXISTING_ENTITY));
        List<Workout> workouts = lifter.getWorkouts();
        for (int i = 0; i < workouts.size(); i++) {
            if (workouts.get(i).getNumberOfWorkout() == numberOfWorkoutToBeRemoved) {
                workouts.remove(i);
            }
        }
    }

    @Override
    public Page<Workout> findAllWithFilters(PageDTO pageDTO, WorkoutCriteriaDTO workoutCriteriaDTO) {
        return workoutCriteriaSearch.findAllWithFilters(pageDTO, workoutCriteriaDTO);
    }

    private Workout setWorkoutDetails(Workout workout, List<ExerciseDTO> exerciseDTO, String email) {
        workout.setDate(LocalDateTime.now());
        workout.setWeightMoved(calculateTotalWeightMoved(exerciseDTO));
        workout.setPersonalRecords(calculatePRs(exerciseDTO));
        workout.setExercisesLog(createTrainingLog(exerciseDTO, email));

        return workoutRepository.save(workout);
    }

    private void resetNumberOfWorkouts(String email) {
        int numberOfWorkout = 1;
        Lifter lifter = lifterRepository.findLifterByEmail(email).get();
        List<Workout> workouts = lifter.getWorkouts();
        Collections.sort(workouts, Comparator.comparing(Workout::getDate));
        for (int i = 0; i < workouts.size(); i++) {
            workouts.get(i).setNumberOfWorkout(i + 1);
        }
        workoutRepository.saveAll(workouts);
    }

    private void addWorkoutToLiftersList(String email, Workout workout) {
        Lifter lifter = lifterRepository.findLifterByEmail(email)
                .orElseThrow(() -> new NonExistingEntityException(Constants.NON_EXISTING_ENTITY));
        lifter.getWorkouts().add(workout);
        lifterRepository.save(lifter);
    }

    private void saveExercisesFromTheWorkout(List<ExerciseDTO> exercisesDTO) {
        List<Exercise> exercises = new ArrayList<>();
        exercisesDTO.forEach(exercise -> exercises.add(
                new Exercise(String.valueOf(
                        Objects.hash(exercise.getName(), exercise.getBodyPart(), exercise.getCategory())),
                        exercise.getName(), exercise.getCategory(), exercise.getBodyPart())));
        exerciseRepository.saveAll(exercises);
    }

    private List<TrainingLog> createTrainingLog(List<ExerciseDTO> exercisesDTO, String email) {
        List<TrainingLog> trainingLogs = new ArrayList<>();
        exercisesDTO.forEach(exercise -> {
            Exercise foundExercise = exerciseRepository
                    .findExerciseById(String.valueOf(Objects.hash(exercise.getName(), exercise.getBodyPart())))
                    .orElse(exerciseRepository.save(
                            new Exercise(String.valueOf(
                                    Objects.hash(exercise.getName(), exercise.getBodyPart(), exercise.getCategory())),
                                    exercise.getName(), exercise.getCategory(), exercise.getBodyPart())));

            trainingLogs.add(new TrainingLog(null, foundExercise, exercise.getSets(), exercise.getReps()));
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
