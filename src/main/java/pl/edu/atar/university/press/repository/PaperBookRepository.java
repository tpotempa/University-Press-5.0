package pl.edu.atar.university.press.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.atar.university.press.model.PaperBook;

public interface PaperBookRepository extends JpaRepository<PaperBook, Long> {
    List<PaperBook> findByTitleContaining(String title);
    List<PaperBook> findByPublishingDateBefore(LocalDate publishingDate);
}