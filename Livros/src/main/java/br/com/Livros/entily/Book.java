package br.com.Livros.entily;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name="book")
@Table(name="book")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(name="name")
    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    public Book(String name, Author author)
    {
        this.name = name;
        this.author = author;
    }

}
