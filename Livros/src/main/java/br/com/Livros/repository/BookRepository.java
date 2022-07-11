package br.com.Livros.repository;

import br.com.Livros.entily.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    
}
