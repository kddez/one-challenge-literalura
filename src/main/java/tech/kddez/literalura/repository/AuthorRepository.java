package tech.kddez.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.kddez.literalura.model.Author;
import tech.kddez.literalura.model.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorByNameContainingIgnoreCase(String name);

    @Query("SELECT b FROM Book b")
    List<Book> findBooks();

    @Query("SELECT a FROM Author a WHERE :year BETWEEN a.birthYear AND a.deathYear")
    List<Author> findAuthorsAliveInYear(int year);

    @Query("SELECT b FROM Book b WHERE b.language ILIKE %:language%")
    List<Book> findBooksByLanguage(String language);

    @Query("SELECT b FROM Book b ORDER BY b.downloadCount DESC LIMIT 10")
    List<Book> findTop10DownloadedBooks();

    @Query("SELECT b FROM Book b WHERE b.title ILIKE %:title%")
    Optional<Book> findBookByTitleContainingIgnoreCase(String title);

    List<Author> findAuthorsByBirthYear(int year);
}
