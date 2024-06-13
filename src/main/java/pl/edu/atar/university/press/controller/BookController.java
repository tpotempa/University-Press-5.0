package pl.edu.atar.university.press.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.atar.university.press.model.PaperBook;
import pl.edu.atar.university.press.repository.PaperBookRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BookController {

    final
    PaperBookRepository paperBookRepository;

    public BookController(PaperBookRepository paperBookRepository) {
        this.paperBookRepository = paperBookRepository;
    }

    @GetMapping("/universitypress")
    public ResponseEntity<List<PaperBook>> getAllPaperBooks(@RequestParam(required = false) String title) {
        try {
            List<PaperBook> books = new ArrayList<>();

            if (title == null)
                paperBookRepository.findAll().forEach(books::add);
            else
                paperBookRepository.findByTitleContaining(title).forEach(books::add);

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<PaperBook> getBookById(@PathVariable("id") long id) {
        Optional<PaperBook> bookData = paperBookRepository.findById(id);

        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<PaperBook> createBook(@RequestBody PaperBook paperBook) {
        try {
            PaperBook book = paperBookRepository
                    .save(new PaperBook(paperBook.getIsbn(), paperBook.getTitle(), paperBook.getDescription(), paperBook.getPublishingDate(), paperBook.getBinding()));
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<PaperBook> updateBook(@PathVariable("id") long id, @RequestBody PaperBook paperBook) {
        Optional<PaperBook> bookData = paperBookRepository.findById(id);

        if (bookData.isPresent()) {
            PaperBook book = bookData.get();
            book.setTitle(paperBook.getTitle());
            book.setDescription(paperBook.getDescription());
            book.setPublishingDate(paperBook.getPublishingDate());
            return new ResponseEntity<>(paperBookRepository.save(book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
        try {
            paperBookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/books")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            paperBookRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/books/published")
    public ResponseEntity<List<PaperBook>> findByPublished() {
        try {
            List<PaperBook> books = paperBookRepository.findByPublishingDateBefore(LocalDate.now());

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}