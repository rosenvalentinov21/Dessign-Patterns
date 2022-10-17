package model.lifters;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PowerliftingExercises implements Lifts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int squatPR;
    private int benchPR;
    private int deadliftPR;

    public PowerliftingExercises(final int squatPR, final int benchPR, final int deadliftPR) {
        this.squatPR = squatPR;
        this.benchPR = benchPR;
        this.deadliftPR = deadliftPR;
    }

    public PowerliftingExercises() {
    }

    public int getSquatPR() {
        return squatPR;
    }

    public void setSquatPR(final int squatPR) {
        this.squatPR = squatPR;
    }

    public int getBenchPR() {
        return benchPR;
    }

    public void setBenchPR(final int benchPR) {
        this.benchPR = benchPR;
    }

    public int getDeadliftPR() {
        return deadliftPR;
    }

    public void setDeadliftPR(final int deadliftPR) {
        this.deadliftPR = deadliftPR;
    }

    @Override
    public int getTotal() {
        return squatPR + benchPR + deadliftPR;
    }
}
