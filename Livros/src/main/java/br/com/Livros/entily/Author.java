package br.com.Livros.entily;

import javax.persistence.*;

@Table(name="author")
@Entity(name="author")

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name")
    private String name;

    public Author(String name){
        this.name = name;
    }
}
