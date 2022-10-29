package XDirectionProject.model.lifters;

import XDirectionProject.model.BodyParts;
import XDirectionProject.model.Categories;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "exercise")
@Data
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private Categories category;

    @Column(name = "body_part")
    private BodyParts bodyPart;
}
