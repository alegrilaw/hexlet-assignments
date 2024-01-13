package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> findById(long id) {
        return userRepository.findById(id);
    }

    public Mono<User> update(User userData, long id) {
        var userMono = userRepository.findById(id);

        return userMono.flatMap(user -> {
            user.setFirstName(userData.getFirstName());
            user.setLastName(userData.getLastName());
            user.setEmail(userData.getEmail());
            return userRepository.save(user);
        });
    }

    public Mono<Void> delete(long id) {
        return userRepository.deleteById(id);
    }
    // END
}
