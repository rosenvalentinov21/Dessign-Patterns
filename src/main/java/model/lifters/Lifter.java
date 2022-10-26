package model.lifters;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Person;

@NoArgsConstructor
@Getter
@Setter
public class Lifter extends Person {
    private int weight;
    private int height;

    public Lifter(final String firstName, final String lastName, final int age, final int weight, final int height) {
        super(firstName, lastName, age);
        this.weight = weight;
        this.height = height;
    }
}
