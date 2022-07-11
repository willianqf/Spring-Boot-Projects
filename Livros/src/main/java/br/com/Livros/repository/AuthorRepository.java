package br.com.Livros.repository;

import br.com.Livros.entily.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
