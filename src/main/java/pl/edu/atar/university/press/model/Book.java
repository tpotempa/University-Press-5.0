package pl.edu.atar.university.press.model;

import java.io.Serializable;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import com.sun.istack.NotNull;
import org.hibernate.annotations.NaturalId;

@MappedSuperclass
public abstract class Book implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String isbn;

    @NotNull
    private String title;

    private String description;

    private LocalDate publishingDate;

    protected Book() {

    }

    protected Book(String isbn, String title, String description, LocalDate publishingDate) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.publishingDate = publishingDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }

    public abstract List<Author> getAuthors();

    public abstract void setAuthors(List<Author> authors);

    public abstract Set<Category> getCategories();

    public abstract void setCategories(Set<Category> categories);

    public String toString() {
        return MessageFormat.format("Książka [ISBN={0}, Tytuł={1}, Opis={2}, Data wydania={3}]", isbn, title, description, publishingDate);
    }
}