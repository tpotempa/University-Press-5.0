package pl.edu.atar.university.press.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.NaturalId;

@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String name;

    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }
}