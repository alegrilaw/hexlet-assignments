package exercise.controller;

import java.util.List;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    // BEGIN
    @GetMapping("")
    public ResponseEntity<List<BookDTO>> index() {
        var books = bookService.getAll();

        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> show(@PathVariable long id) {
        var book = bookService.findById(id);

        return ResponseEntity.ok(book);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookCreateDTO bookData) {
        var book = bookService.create(bookData);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable long id, @Valid @RequestBody BookUpdateDTO bookData) {
        var book = bookService.update(bookData, id);

        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        bookService.delete(id);
    }
    // END
}
