package XDirectionProject.model;

import XDirectionProject.model.lifters.Exercise;
import XDirectionProject.model.lifters.Lifter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrainingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ManyToOne
    private Lifter lifter;
    @ManyToOne
    private Exercise exercise;
    private int Sets;
    private int Reps;
}
