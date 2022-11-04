package XDirectionProject.dto;

import XDirectionProject.model.BodyParts;
import XDirectionProject.model.Categories;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExerciseDTO {

    private String name;

    private Categories category;

    private BodyParts bodyPart;

    private int Sets;

    private int Reps;
}
