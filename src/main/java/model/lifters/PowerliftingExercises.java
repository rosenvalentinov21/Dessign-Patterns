package model.lifters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PowerliftingExercises {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double squatPR;
    private double benchPR;
    private double deadliftPR;
}
