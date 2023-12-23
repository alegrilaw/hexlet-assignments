package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        var authors = authorRepository.findAll();
        var result = authors.stream()
            .map(authorMapper::map)
            .toList();
        return result;
    }

    public AuthorDTO create(AuthorCreateDTO authorData) {
        var author = authorMapper.map(authorData);
        authorRepository.save(author);
        var authorDto = authorMapper.map(author);
        return authorDto;
    }

    public AuthorDTO findById(long id) {
        var author = authorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var authorDto = authorMapper.map(author);
        return authorDto;
    }

    public AuthorDTO update(AuthorUpdateDTO authorData, long id) {
        var author = authorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        authorMapper.update(authorData, author);
        authorRepository.save(author);
        var authorDto = authorMapper.map(author);
        return authorDto;
    }

    public void delete(long id) {
        authorRepository.deleteById(id);
    }
    // END
}
