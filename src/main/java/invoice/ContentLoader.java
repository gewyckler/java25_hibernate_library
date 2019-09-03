package invoice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ContentLoader {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Client updateClientInformation(Client client) {
        System.out.println("Zmienić imie? Obecne: " + client.getName() + " true/false");
        if (truOrFalse()) {
            client.setName(typeName());
        }
        System.out.println("Zmienić nazwisko? Obecne: " + client.getSurName() + " true/false");
        if (truOrFalse()) {
            client.setSurName(typeSurName());
        }
        System.out.println("Zmienić numer Id? Obecna: " + client.getIdNumber() + " true/false");
        if (truOrFalse()) {
            client.setIdNumber(typeIdNumber());
        }
        return client;
    }

    public Author updateAuthorInformation(Author author) {
        System.out.println("Zmienić imie? Obecne: " + author.getName() + " true/false");
        if (truOrFalse()) {
            author.setName(typeName());
        }
        System.out.println("Zmienić nazwisko? Obecne: " + author.getSurName() + " true/false");
        if (truOrFalse()) {
            author.setSurName(typeSurName());
        }
        System.out.println("Zmienić date urodzenia? Obecna: " + author.getBirthTime() + " true/false");
        if (truOrFalse()) {
            author.setBirthTime(typeBirthDate());
        }
        return author;
    }

    public Client createClient() {
        Client client = new Client();

        client.setId(null);
        client.setName(typeName());
        client.setSurName(typeSurName());
        return client;
    }

    public String typeName() {
        System.out.println("Podaj imie:");
        return scanner.nextLine();
    }

    public String typeSurName() {
        System.out.println("Podaj nazwisko:");
        return scanner.nextLine();
    }

    public String menu() {
        return scanner.nextLine();
    }

    public Long getId() {
        System.out.println("Podaj Id:");
        return Long.valueOf(scanner.nextLine());
    }

    public String typeIdNumber() {
        System.out.println("Podaj numer Id klienta:");
        return scanner.nextLine();
    }

    public String waitForUser() {
        System.out.println("Wciśnij 'ENTER' aby kontynuować...");
        return scanner.nextLine();
    }

    public Boolean truOrFalse() {
        return Boolean.valueOf(scanner.nextLine());
    }

    public Author createAuthor() {
        Author author = new Author();
        author.setId(null);
        author.setName(typeName());
        author.setSurName(typeSurName());

        author.setBirthTime(typeBirthDate());

        return author;
    }

    public LocalDate typeBirthDate() {
        System.out.println("Wpisz date urodzin (dd-MM-yyyy");
        String date = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(date, formatter);
        return birthDate;
    }

    public Book createBook() {
        Book book = new Book();

        book.setId(null);
        book.setTitle(typeTitle());
        book.setYearWritten(typeYear());
        book.setNumberOfPages(typeNumberOfPages());
        book.setNumberOfAvailableCopies(typeNumberOfPages());

        return book;
    }

    public String typeTitle() {
        System.out.println("Podaj tytuł ksiązki:");
        return scanner.nextLine();
    }

    public int typeYear() {
        System.out.println("Wpisz rok wydania:");
        return Integer.parseInt(scanner.nextLine());
    }

    public int typeNumberOfPages() {
        int number;
        do {
            System.out.println("Podaj liczbe stron:");
            number = Integer.parseInt(scanner.nextLine());

        } while (number <= 0);
        return number;
    }
}
