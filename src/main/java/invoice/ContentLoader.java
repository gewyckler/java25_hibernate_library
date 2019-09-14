package invoice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class ContentLoader {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private EntityDao entityDao = new EntityDao();

    public Client updateClientInformation(Client client) {
        System.out.println("Zmienić imie? Obecne: " + client.getName() + " t/f");
        if (truOrFalse()) {
            client.setName(typeName());
        }
        System.out.println("Zmienić nazwisko? Obecne: " + client.getSurName() + " t/n");
        if (truOrFalse()) {
            client.setSurName(typeSurName());
        }
        System.out.println("Zmienić numer Id? Obecna: " + client.getIdNumber() + " t/n");
        if (truOrFalse()) {
            client.setIdNumber(typeIdNumber());
        }
        return client;
    }

    public Author updateAuthorInformation(Author author) {
        System.out.println("Zmienić imie? Obecne: " + author.getName() + " t/n");
        if (truOrFalse()) {
            author.setName(typeName());
        }
        System.out.println("Zmienić nazwisko? Obecne: " + author.getSurName() + " t/n");
        if (truOrFalse()) {
            author.setSurName(typeSurName());
        }
        System.out.println("Zmienić date urodzenia? Obecna: " + author.getBirthTime() + " t/n");
        if (truOrFalse()) {
            author.setBirthTime(typeBirthDate());
        }
        return author;
    }

    public Book updateBookInformation(Book book) {
        System.out.println("Zmienić tytuł? Obecny: " + book.getTitle() + " t/n");
        if (truOrFalse()) {
            book.setTitle(typeName());
        }
        System.out.println("Zmienić rok napisania? Obecny: " + book.getYearWritten() + " t/n");
        if (truOrFalse()) {
            book.setYearWritten((typeYear()));
        }
        System.out.println("Zmienić liczbę stron w książce? Obecnie: " + book.getNumberOfPages() + " t/n");
        if (truOrFalse()) {
            book.setNumberOfPages(typeNumberOfPages());
        }
        return book;
    }


    public Client createClient() {
        Client client = new Client();

        client.setId(null);
        client.setName(typeName());
        client.setSurName(typeSurName());
        return client;
    }

    public Author createAuthor() {
        Author author = new Author();
        author.setId(null);
        author.setName(typeName());
        author.setSurName(typeSurName());

        author.setBirthTime(typeBirthDate());

        return author;
    }

    public Book createBook() {
        Book book = new Book();

        book.setId(null);
        book.setTitle(typeTitle());
        book.setYearWritten(typeYear());
        book.setNumberOfPages(typeNumberOfPages());
        book.setNumberOfAvailableCopies(typeNumberOfAvailableCopies());

        return book;
    }

    public BookLent createBookLent(Client client) {
        BookLent bookLent = new BookLent();
        bookLent.setId(null);
        bookLent.setClient(client);
        bookLent.setBook(addBookToBookLent());
        bookLent.setDateLent(LocalDate.now());

        return bookLent;
    }

    public Client addClientToBookLent() {
        Optional<Client> optionalClient;
        do {
            printSet(entityDao.getAll(Client.class));
            System.out.println("Wybierz Id klienta który wypożycza");
            Long id = getId();
            optionalClient = entityDao.getById(Client.class, id);
            if (!optionalClient.isPresent()) {
                System.out.println("Wpisano błędne Id.");
            }
        } while (!optionalClient.isPresent());
        return optionalClient.get();
    }

    public Book addBookToBookLent() {
        Optional<Book> optionalBook;
        do {
            printSet(entityDao.getAll(Book.class));
            System.out.println("Wybierz Id ksiązki którą chcesz dodać");
            Long idB = getId();
            optionalBook = entityDao.getById(Book.class, idB);
            if (!optionalBook.isPresent()) {
                System.out.println("Wpisano błedne Id.");
            }
        } while (!optionalBook.isPresent());
        return optionalBook.get();
    }

    public void addBookToAuthor(Author author) {
        Optional<Book> optionalBook;
        do {
            printSet(entityDao.getAll(Book.class));
            System.out.println("Wpisz Id książki którą chcesz dodać");
            Long id = getId();
            optionalBook = entityDao.getById(Book.class, id);
            if (optionalBook.isPresent()) {
                author.getBooks().add(optionalBook.get());
            }
        } while (!optionalBook.isPresent());

    }


    public <T> void printSet(Set<T> tSet) {
        for (T t : tSet) {
            System.out.println(t);
        }
    }

    public <T> void printList(List<T> tList) {
        for (T t : tList) {
            System.out.println(t + "cos");
        }
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


    public boolean truOrFalse() {
        String chose;
        do {
            chose = scanner.nextLine();
            if (chose.equalsIgnoreCase("t")) {
                return true;
            } else if (chose.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Nie ma takie opcji.");
            }
        } while (!chose.equalsIgnoreCase("t") || !chose.equalsIgnoreCase("n"));
        return false;
    }


    public LocalDate typeBirthDate() {
        System.out.println("Wpisz date urodzin (dd-MM-yyyy");
        String date = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(date, formatter);
        return birthDate;
    }

    public int typeNumberOfAvailableCopies() {
        System.out.println("Wpisz liczbę dostępnych kopi tej ksiązki:");
        return Integer.parseInt(scanner.nextLine());
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

    public String waitForUser() {
        System.out.println("Wciśnij 'ENTER' aby kontynuować...");
        return scanner.nextLine();
    }
}
