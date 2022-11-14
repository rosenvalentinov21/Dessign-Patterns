package XDirectionProject.repository;

import XDirectionProject.dto.PageDTO;
import XDirectionProject.dto.WorkoutCriteriaDTO;
import XDirectionProject.model.TrainingLog;
import XDirectionProject.model.lifters.Exercise;
import XDirectionProject.model.lifters.Workout;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class WorkoutCriteriaSearch {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public WorkoutCriteriaSearch(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Workout> findAllWithFilters(PageDTO pageDTO, WorkoutCriteriaDTO workoutCriteriaDTO) {
        CriteriaQuery<Workout> criteriaQuery = criteriaBuilder.createQuery(Workout.class);
        Root<Workout> workoutRoot = criteriaQuery.from(Workout.class);
        Predicate predicate = getPredicate(workoutCriteriaDTO, workoutRoot);
        criteriaQuery.where(predicate);
        setOrder(pageDTO, criteriaQuery, workoutRoot);
        TypedQuery<Workout> typedQuery = entityManager.createQuery(criteriaQuery);
        int totalRows = typedQuery.getResultList().size();
        typedQuery.setFirstResult(pageDTO.getPageNumber() * pageDTO.getPageSize());
        typedQuery.setMaxResults(pageDTO.getPageSize());

        Pageable pageable = getPageable(pageDTO);
        return new PageImpl<>(typedQuery.getResultList(), pageable, totalRows);
    }

    private Predicate getPredicate(WorkoutCriteriaDTO workoutCriteriaDTO, Root<Workout> workoutRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(workoutCriteriaDTO.getName())) {
            predicates.add(criteriaBuilder.like(workoutRoot.get("name"), "%" + workoutCriteriaDTO.getName() + "%"));
        }
        if (Objects.nonNull(workoutCriteriaDTO.getDescription())) {
            predicates.add(criteriaBuilder.like(workoutRoot.get("description"), "%" + workoutCriteriaDTO.getDescription() + "%"));
        }
        if (workoutCriteriaDTO.getWeightMoved() != 0) {
            predicates.add(criteriaBuilder.greaterThan(workoutRoot.get("weightMoved"), workoutCriteriaDTO.getWeightMoved()));
        }
        if (workoutCriteriaDTO.getPersonalRecords() != 0) {
            predicates.add(criteriaBuilder.greaterThan(workoutRoot.get("personalRecords"), workoutCriteriaDTO.getPersonalRecords()));
        }
        if (Objects.nonNull(workoutCriteriaDTO.getDate())) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(workoutRoot.get("date"), workoutCriteriaDTO.getDate()));
        }
        if (Objects.nonNull(workoutCriteriaDTO.getExerciseName())) {
            Join<Workout, TrainingLog> trainingLogJoin = workoutRoot.joinList("exercisesLog");
            Join<TrainingLog, Exercise> exercisesLog = trainingLogJoin.join("exercise");
            predicates.add(criteriaBuilder.like(exercisesLog.get("name"), "%" + workoutCriteriaDTO.getExerciseName() + "%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(PageDTO pageDTO, CriteriaQuery<Workout> criteriaQuery, Root<Workout> workoutRoot) {
        if (pageDTO.getSortDirection().equals(Sort.Direction.ASC))
            criteriaQuery.orderBy(criteriaBuilder.asc(workoutRoot.get(pageDTO.getSortBy())));
        else criteriaQuery.orderBy(criteriaBuilder.desc(workoutRoot.get(pageDTO.getSortBy())));
    }

    private Pageable getPageable(PageDTO pageDTO) {
        Sort sort = Sort.by(pageDTO.getSortDirection(), pageDTO.getSortBy());
        return PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize(), sort);
    }
}
