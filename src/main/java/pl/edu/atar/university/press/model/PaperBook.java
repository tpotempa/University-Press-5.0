package pl.edu.atar.university.press.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

@Entity
@NamedEntityGraph(name = "Book.authors",
        attributeNodes = @NamedAttributeNode("authors")
)
@NamedEntityGraph(name = "Book.authors-categories",
        attributeNodes = {
                @NamedAttributeNode("authors"),
                @NamedAttributeNode("categories")
        }
)

public class PaperBook extends Book {

    private String binding;

    @ManyToMany(fetch = EAGER)
    @Fetch(FetchMode.JOIN)
    private List<Author> authors = new ArrayList<>();

    @ManyToMany(fetch = LAZY)
    private Set<Category> categories = new LinkedHashSet<>();

    public PaperBook() {
    }

    public PaperBook(String isbn, String title, String description, LocalDate publishingDate, String binding) {
        super(isbn, title, description, publishingDate);
        this.binding = binding;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    @Override
    public List<Author> getAuthors() {
        return null;
    }

    @Override
    public void setAuthors(List<Author> authors) {

    }

    @Override
    public Set<Category> getCategories() {
        return null;
    }

    @Override
    public void setCategories(Set<Category> categories) {

    }
}