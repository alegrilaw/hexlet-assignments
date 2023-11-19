package exercise.controller.users;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {

    private final List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> index(@PathVariable String id) {
        var intId = Integer.parseInt(id);
        var result = posts.stream()
            .filter(p -> p.getUserId() == intId)
            .toList();

        return ResponseEntity.ok(result);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> create(@PathVariable String id, @RequestBody Post data) {
        var intId = Integer.parseInt(id);
        var post = new Post();
        post.setUserId(intId);
        post.setSlug(data.getSlug());
        post.setTitle(data.getTitle());
        post.setBody(data.getBody());
        posts.add(post);

        return ResponseEntity.status(201).body(post);
    }
}
// END
