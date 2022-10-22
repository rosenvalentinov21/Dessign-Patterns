package model.lifters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Powerlifter extends Lifter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private PowerliftingExercises powerliftingExercises;

    public Powerlifter(final String firstName, final String lastName, final int age, final int weight, final int height) {
        super(firstName, lastName, age, weight, height);
    }
}
