package model;

public class Lifter extends Person{
    
    int squatPR;
    int benchPR;
    int deadliftPR;
    int weight;

    public Lifter(final String firstName,final String lastName,final int age,final int squatPR,final int benchPR,final int deadliftPR, final int weight) {
        super(firstName, lastName, age);
        this.squatPR = squatPR;
        this.benchPR = benchPR;
        this.deadliftPR = deadliftPR;
        this.weight = weight;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTotal(){
        return squatPR + benchPR + deadliftPR;
    }
}
