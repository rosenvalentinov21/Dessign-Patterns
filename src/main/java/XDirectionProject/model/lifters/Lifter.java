package XDirectionProject.model.lifters;

import XDirectionProject.model.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "lifter")
@Entity
public class Lifter extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "weight")
    private int weight;

    @Column(name = "height")
    private int height;

    @OneToMany
    @Column(name = "workouts")
    private List<Workout> workouts;

    public Lifter(final String firstName, final String lastName, final int age, final int weight, final int height) {
        super(firstName, lastName, age);
        this.weight = weight;
        this.height = height;
    }
}
