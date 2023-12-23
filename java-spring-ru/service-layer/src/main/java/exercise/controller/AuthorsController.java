package exercise.controller;

import exercise.dto.AuthorDTO;
import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    // BEGIN
    @GetMapping("")
    public ResponseEntity<List<AuthorDTO>> index() {
        var authors = authorService.getAll();

        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> show(@PathVariable long id) {
        var author = authorService.findById(id);

        return ResponseEntity.ok(author);
    }

    @PostMapping("")
    public ResponseEntity<AuthorDTO> create(@Valid @RequestBody AuthorCreateDTO authorData) {
        var author = authorService.create(authorData);

        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable long id, @Valid @RequestBody AuthorUpdateDTO authorData) {
        var author = authorService.update(authorData, id);

        return ResponseEntity.ok(author);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        authorService.delete(id);
    }
    // END
}
