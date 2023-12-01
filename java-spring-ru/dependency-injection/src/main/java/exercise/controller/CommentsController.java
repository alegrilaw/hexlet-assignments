package exercise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentsController {

    private static final String NOT_FOUND = "Comment with id %d not found";

    private final CommentRepository commentRepository;

    @GetMapping("")
    public List<Comment> index() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment show(@PathVariable long id) {
        return commentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND, id)));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable long id, @RequestBody Comment data) {
        var comment = commentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND, id)));

        comment.setBody(data.getBody());
        return commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        var comment = commentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND, id)));

        commentRepository.delete(comment);
    }
}
// END
