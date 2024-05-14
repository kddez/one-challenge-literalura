package tech.kddez.literalura.model;

import jakarta.persistence.*;
import tech.kddez.literalura.model.dto.AuthorDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Author(){}

    public Author(AuthorDTO dto){
        this.name = dto.name();
        this.birthYear = dto.birthYear();
        this.deathYear = dto.deathYear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {

        var bookTitles = books.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining("; "));

        return """
                ------- INFO -------
                Name: %s
                Birth year: %d
                Death year: %d
                Books: [%s]
                ---------------------
                """.formatted(name, birthYear, deathYear, bookTitles);
    }
}
