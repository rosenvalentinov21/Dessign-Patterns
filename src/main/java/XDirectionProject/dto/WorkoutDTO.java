package XDirectionProject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class WorkoutDTO {
    private String name;

    private List<ExerciseDTO> exercises;

    private String description;
}
