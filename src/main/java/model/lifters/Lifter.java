package model.lifters;

import model.Person;

public class Lifter extends Person {

    int weight;

    int height;

    public Lifter(final String firstName, final String lastName, final int age, final int weight, final int height) {
        super(firstName, lastName, age);
        this.weight = weight;
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(final int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }
}
