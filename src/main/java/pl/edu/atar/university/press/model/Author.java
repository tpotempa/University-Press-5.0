package pl.edu.atar.university.press.model;

import java.io.Serializable;
import java.text.MessageFormat;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "author")
public class Author implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50, nullable = false)
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;

    @NaturalId
    @Column(length = 19, nullable = true, unique = true)
    private String orcid;
    public Author() {
    }

    public Author(String firstName, String middleName, String lastName, LocalDate dateOfBirth, String orcid) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth  = dateOfBirth;
        this.orcid = orcid;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Autor [ID={0}, Imię={1}, Drugie imię={2}, Nazwisko={3}, Data urodzenia={4}, ORCID={5}]", id, firstName, middleName, lastName, dateOfBirth, orcid);
    }
}