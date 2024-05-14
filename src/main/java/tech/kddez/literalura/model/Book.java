package tech.kddez.literalura.model;

import jakarta.persistence.*;
import tech.kddez.literalura.model.dto.AuthorDTO;
import tech.kddez.literalura.model.dto.BookDTO;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String title;
    private String language;
    private Integer downloadCount;
    @ManyToOne
    private Author author;

    public Book(){}

    public Book(BookDTO dto){
        this.title = dto.title();
        this.language = dto.languages().getFirst();
        this.downloadCount = dto.downloadCount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titulo) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return """
                ------- LIVRO -------
                Title: %s
                Author: %s
                Language: %s
                Downloads: %d
                ---------------------
                """.formatted(title, author.getName(), language, downloadCount);
    }
}
