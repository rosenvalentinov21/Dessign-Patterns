package model.lifters;

import javax.persistence.*;

@Entity
public class Powerlifter extends Lifter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private PowerliftingExercises powerliftingExercises;


    public Powerlifter(final String firstName, final String lastName, final int age, final int weight, final int height) {
        super(firstName, lastName, age, weight, height);
    }

    public Powerlifter() {
        super();
    }

    public void setSquatPR(final int squatPR) {
        powerliftingExercises.setSquatPR(squatPR);
    }

    public int getSquatPR() {
        return powerliftingExercises.getSquatPR();
    }

    public void setBenchPR(final int benchPR) {
        powerliftingExercises.setBenchPR(benchPR);
    }

    public int getBenchPR() {
        return powerliftingExercises.getBenchPR();
    }

    public void setDeadliftPR(final int deadliftPR) {
        powerliftingExercises.setDeadliftPR(deadliftPR);
    }

    public int getDeadliftPR() {
        return powerliftingExercises.getDeadliftPR();
    }

    public int getTotal() {
        return powerliftingExercises.getTotal();
    }
}
