package exercise.controller;

import exercise.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @GetMapping("")
    public List<PostDTO> index() {
        return postRepository.findAll()
            .stream()
            .map(p -> PostDTO.builder()
                .id(p.getId())
                .title(p.getTitle())
                .body(p.getBody())
                .comments(commentRepository.findByPostId(p.getId())
                    .stream()
                    .map(c -> CommentDTO.builder()
                        .id(c.getId())
                        .body(c.getBody())
                        .build())
                    .toList())
                .build())
            .toList();
    }

    @GetMapping("/{id}")
    public PostDTO show(@PathVariable long id) {
        var post = postRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with id %d not found", id)));

        var comments = commentRepository.findByPostId(id)
            .stream()
            .map(c -> CommentDTO.builder()
                .id(c.getId())
                .body(c.getBody())
                .build())
            .toList();

        return PostDTO.builder()
            .id(post.getId())
            .title(post.getTitle())
            .body(post.getBody())
            .comments(comments)
            .build();
    }
}
// END
