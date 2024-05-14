package tech.kddez.literalura.main;

import tech.kddez.literalura.service.AuthorService;

import java.util.Scanner;

public class Main {

    private final Scanner sc = new Scanner(System.in);
    private final AuthorService service;

    public Main(AuthorService service) {
        this.service = service;
    }

    public void displayMenu(){

        int option = -1;

        while (option != 0){

            menu();
            System.out.println("Enter the option number: ");
            option = sc.nextInt();

            switch (option){
                case 1:
                    service.searchBooks();
                    break;
                case 2:
                    service.listAllBooks();
                    break;
                case 3:
                    service.listAllAuthors();
                    break;
                case 4:
                    service.listAuthorsAliveInYear();
                    break;
                case 5:
                    languageMenu();
                    service.listBooksInLanguage();
                    break;
                case 6:
                    service.listTop10DownloadedBooks();
                    break;
                case 7:
                    service.searchAuthorByName();
                    break;
                case 8:
                    service.calculateBookStatistics();
                    break;
                case 9:
                    service.listAuthorsByBirthYear();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Inválid Option!");

            }
        }

    }

    public void menu(){

        System.out.println("""
            -------- CHOOSE A NUMBER --------
            1- Search for a book in the API
            2- List all books
            3- List all authors
            4- List authors alive in a certain year
            5- List books in a certain language
            6- List top 10 downloaded books
            7- Search for author by name
            8- Get book statistics
            9- list authors by birth year
            0- Exit
            """);

    }

    private void languageMenu(){
        System.out.println("""
            
            PT - PORTUGUESE
            FR - FRENCH
            EN - ENGLISH
            ES - SPANISH
            
            """);
    }


}
