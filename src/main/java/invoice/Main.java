package invoice;

import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityDao entityDao = new EntityDao();
        ClientDao clientDao = new ClientDao();
        ContentLoader ct = new ContentLoader();

        String toDo;

        do {
            System.out.println("Witaj w bibliotece.");
            System.out.println("1.  Dodawanie klientów.");
            System.out.println("2.  Listowanie klientów.");
            System.out.println("3.  Usuwanie klientów po Id.");
            System.out.println("4.  Modyfikuj imienia/nazwiska lub numeru id klienta.");
            System.out.println("\n5.  Dodawanie autorów.");
            System.out.println("6.  Listowanie autorów.");
            System.out.println("7.  Usuwanie autorów.");
            System.out.println("8.  Modyfikuj imienia/nazwiska lub date narodzin autora.");
            System.out.println("\n9.  Dodawanie ksiażek.");
            System.out.println("10. Listowanie książek.");
            System.out.println("11. Usuwanie książek.");
            System.out.println("12. Modyfikuj tytuł/rok napsania/ilość stron w książce.");
            System.out.println("0.  Wyjście...");
            toDo = ct.menu();

            if (toDo.equalsIgnoreCase("1")) { // dodawania autorów
                Client client = ct.createClient();
                entityDao.saveOrUpdate(client);

            } else if (toDo.equalsIgnoreCase("2")) { // listowanie autorów
                Set<Client> clientList = entityDao.getAll(Client.class);
                for (Client c : clientList) {
                    System.out.println(c);
                }
                ct.waitForUser();

            } else if (toDo.equalsIgnoreCase("3")) { // usuwanie autorów po Id
                Long id = ct.getId();
                entityDao.delete(Client.class, id);

            } else if (toDo.equalsIgnoreCase("4")) { // modyfikacja imienia/nazwiska lub roku urodzenia autora
                Long id = ct.getId();
                Optional<Client> optionalClient = entityDao.getById(Client.class, id);
                if (optionalClient.isPresent()) {
                    Client client = ct.updateClientInformation(optionalClient.get());
                    entityDao.saveOrUpdate(client);
                }

            } else if (toDo.equalsIgnoreCase("5")) { // Dodawanie autorów
                Author author = ct.createAuthor();
                entityDao.saveOrUpdate(author);

            } else if (toDo.equalsIgnoreCase("6")) { // Listowanie autorów
                Set<Author> authorList = entityDao.getAll(Author.class);
                for (Author a : authorList) {
                    System.out.println(a);
                }
                ct.waitForUser();

            } else if (toDo.equalsIgnoreCase("7")) { // Usuwanie autorów
                Long id = ct.getId();
                entityDao.delete(Author.class, id);

            } else if (toDo.equalsIgnoreCase("8")) { // Modyfikuj imienia/nazwiska lub date narodzin autora
                Long id = ct.getId();
                Optional<Author> optionalAuthor = entityDao.getById(Author.class, id);
                if (optionalAuthor.isPresent()) {
                    Author author = ct.updateAuthorInformation(optionalAuthor.get());
                    entityDao.saveOrUpdate(author);
                }

            } else if (toDo.equalsIgnoreCase("9")) { // Dodawanie ksiażek
                Book book = ct.createBook();
                entityDao.saveOrUpdate(book);

            } else if (toDo.equalsIgnoreCase("10")) { // Listowanie książek
                Set<Book> bookList = entityDao.getAll(Book.class);
                for (Book b : bookList) {
                    System.out.println(b);
                }
                ct.waitForUser();

            } else if (toDo.equalsIgnoreCase("11")) { // Usuwanie książek
                Long id = ct.getId();
                entityDao.delete(Book.class, id);

            } else if (toDo.equalsIgnoreCase("12")) { // Modyfikuj tytuł/rok napsania/ilość stron w książce
                Long id = ct.getId();
                Optional<Book> optionalBook = entityDao.getById(Book.class, id);
                if (optionalBook.isPresent()) {
                    Book book = ct.updateBookInformation(optionalBook.get());
                    entityDao.saveOrUpdate(book);
                }
            }
        } while (!toDo.equalsIgnoreCase("0"));
    }
}