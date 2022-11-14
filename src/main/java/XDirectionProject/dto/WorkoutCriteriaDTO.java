package XDirectionProject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class WorkoutCriteriaDTO {
    private String name;

    private String description;

    private LocalDateTime date;

    private String exerciseName;

    private double weightMoved;

    private int personalRecords;
}
