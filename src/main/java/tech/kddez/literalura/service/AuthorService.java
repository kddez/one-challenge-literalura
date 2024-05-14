package tech.kddez.literalura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.kddez.literalura.model.Author;
import tech.kddez.literalura.model.Book;
import tech.kddez.literalura.model.dto.AuthorDTO;
import tech.kddez.literalura.model.dto.BookDTO;
import tech.kddez.literalura.model.dto.Data;
import tech.kddez.literalura.repository.AuthorRepository;
import tech.kddez.literalura.utils.APIConsumer;
import tech.kddez.literalura.utils.DataConverter;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final Scanner sc = new Scanner(System.in);
    private final APIConsumer apiConsumer = new APIConsumer();
    private final DataConverter converter = new DataConverter(new ObjectMapper());

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void searchBooks() {

        try {
            System.out.println("Enter the book title: ");
            String title = sc.nextLine();

            String searchUrl = "https://gutendex.com/books/?search=";
            String apiUrl = searchUrl + title.replace(" ", "+");

            String json = apiConsumer.fetchData(apiUrl);
            Data data = converter.extractData(json, Data.class);

            if(!data.books().isEmpty()){
                processBookData(data.books().getFirst());
            }else{
                System.out.println("No books found for this title: " + title);
            }

        }catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
        }

    }

    public void processBookData(BookDTO bookDTO){
        //AuthorDTO recebido pela api
        AuthorDTO authorDTO = bookDTO.authors().getFirst();
        //Verifico se o autor existe no banco, realizando uma consulta com o nome recebido no DTO.
        var authorExists = authorRepository.findAuthorByNameContainingIgnoreCase(authorDTO.name());

        //Caso o autor já exista, eu o aloco em uma variável. Do contrário, um novo autor é criado a partir do DTO recebido.
        Author author = authorExists.orElseGet(() -> new Author(authorDTO));

        //Verifico se o livro já está registrado no banco de dados, para que não haja duplicatas
        var bookExists = authorRepository.findBookByTitleContainingIgnoreCase(bookDTO.title());
        //Caso exista, o usuário é informado.
        if(bookExists.isPresent()){
            System.out.println("This book already exists in the database: " + bookDTO.title());
            //Senão, eu sigo com a criação do livro e o relaciono com autor (seja novo ou existente) e vice-versa.

        } else {

            Book newBook = new Book(bookDTO);
            newBook.setAuthor(author);
            author.getBooks().add(newBook);

            authorRepository.save(author);
            System.out.println(newBook);

        }

    }

    public void listAllBooks() {
        authorRepository.findBooks().stream()
                .sorted(Comparator.comparing(Book::getDownloadCount).reversed())
                .forEach(System.out::println);
    }

    public void listAllAuthors() {
        authorRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Author::getName))
                .forEach(System.out::println);
    }

    public void listAuthorsAliveInYear() {

        System.out.println("Enter the year: ");
        int year = sc.nextInt();

        List<Author> foundAuthors = authorRepository.findAuthorsAliveInYear(year);

        if(foundAuthors.isEmpty()){
            System.out.println("No authors found for this year!");
        }
        foundAuthors.forEach(System.out::println);

    }

    public void listBooksInLanguage() {

        System.out.println("Enter the language code: ");
        String language = sc.nextLine();

        List<Book> foundBooks = authorRepository.findBooksByLanguage(language);

        if(foundBooks.isEmpty()){
            System.out.println("No books found in this language!");
        }

        foundBooks.forEach(System.out::println);
    }

    public void listTop10DownloadedBooks() {
        authorRepository.findTop10DownloadedBooks()
                .forEach(System.out::println);
    }



    public void searchAuthorByName() {

        System.out.println("Enter the author name: ");
        var authorName = sc.nextLine();

        if(!authorName.isEmpty()) {
            authorRepository.findAuthorByNameContainingIgnoreCase(authorName)
                    .ifPresentOrElse(System.out::println,
                            () -> System.out.println("No authors found with this name: " + authorName));
        }
    }

    public void calculateBookStatistics() {

        List<Book> books = authorRepository.findBooks();

        DoubleSummaryStatistics stats = books.stream()
                .mapToDouble(Book::getDownloadCount)
                .summaryStatistics();

        System.out.printf("""
                Sum of download counts: %.2f
                Average download count: %.2f
                Min download count: %.2f
                Max download count: %.2f
                Count of books: %d
                %n""", stats.getSum(), stats.getAverage(), stats.getMin(), stats.getMax(), stats.getCount());

    }

    public void listAuthorsByBirthYear() {
        System.out.println("Enter the year: ");
        var year = sc.nextInt();

        List<Author> authors = authorRepository.findAuthorsByBirthYear(year);

        if(authors.isEmpty()){
            System.out.println("No authors born this year were found");
        } else {
            authors.forEach(System.out::println);
        }

    }
}

