package XDirectionProject.model.lifters;

import XDirectionProject.model.BodyParts;
import XDirectionProject.model.Categories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exercise")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exercise {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private Categories category;

    @Column(name = "body_part")
    private BodyParts bodyPart;

}
