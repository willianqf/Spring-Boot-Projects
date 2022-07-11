package br.com.Livros.services;
import java.util.List;

import br.com.Livros.entily.Author;
import br.com.Livros.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {


    @Autowired
    private AuthorRepository authorRepository;
    public List<Author> getAuthorList()

    {
        Iterable<Author> authorIterable = this.authorRepository.findAll();
        return Streamable.of(authorIterable).toList();
    }
}
