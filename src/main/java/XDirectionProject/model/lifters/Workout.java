package XDirectionProject.model.lifters;

import XDirectionProject.model.TrainingLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "workout")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @Column(name = "exercises")
    private List<TrainingLog> exercisesLog;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "weight_moved")
    private double weightMoved;

    @Column(name = "personal_records")
    private int personalRecords;
}
