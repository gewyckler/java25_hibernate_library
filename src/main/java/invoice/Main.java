package invoice;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityDao entityDao = new EntityDao();
        ContentLoader ct = new ContentLoader();
        ClientDao clientDao = new ClientDao();
        AuthorDao authorDao = new AuthorDao();
        BookLentDao bookLentDao = new BookLentDao();

        String toDo;

        do {
            System.out.println("Witaj w bibliotece.");
            System.out.println("1. Dodawanie, usuwanie, listowanie, modyfikowanie:");
            System.out.println("2. Dodawanie powiązań, znajdowanie podstawowe:");
            System.out.println("3. Praktyczne:");
            System.out.println("0. Wyjście...:");
            toDo = ct.menu();
            switch (toDo) {
                case "1":
                    do {
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
                        System.out.println("q.  Cofnij...");
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
                    }
                    while (!toDo.equalsIgnoreCase("q"));
                    break;

                case "2":
                    do {
                        System.out.println("1. Dodawanie powiązania między książką a autorem (ten autor napisał daną książkę");
                        System.out.println("2. Dodawanie wypożyczeń (BookLent) danemu klientowi na daną książkę");
                        System.out.println("3. Znajdowanie autorów(liczba mnoga) po nazwisku");
                        System.out.println("4. Znajdowanie klientów po nazwisku");
                        System.out.println("5. Znajdowanie klientA po id number");
                        System.out.println("q. Cofnij...");
                        toDo = ct.menu();

                        if (toDo.equalsIgnoreCase("1")) { // Dodaj ksiazke do autora
                            ct.printSet(entityDao.getAll(Author.class));
                            System.out.println("Wybierz Id autora któremu chcesz dodać książkę:");
                            Long idA = ct.getId();
                            Optional<Author> optionalAuthor = entityDao.getById(Author.class, idA);
                            do {
                                if (optionalAuthor.isPresent()) {
                                    ct.addBookToAuthor(optionalAuthor.get());
                                    entityDao.saveOrUpdate(optionalAuthor.get());
                                }
                                System.out.println("Dodać kolejną ksiązkę? t/n");
                            } while (ct.truOrFalse());
                        } else if (toDo.equalsIgnoreCase("2")) { // Dodawanie wypożyczeń danemu klientowi
                            ct.printSet(entityDao.getAll(Client.class));
                            Client client = ct.addClientToBookLent();
                            BookLent bookLent = ct.createBookLent(client);
                            entityDao.saveOrUpdate(bookLent);

                        } else if (toDo.equalsIgnoreCase("3")) {      // Znajdowanie autorów(liczba mnoga) po nazwisku
                            String surName = ct.typeSurName();
                            List<Author> authorList = authorDao.getAuthorBySurName(surName);
                            ct.printList(authorList);

                        } else if (toDo.equalsIgnoreCase("4")) {       // znajdowanie klientów po nazwisku
                            String surName = ct.typeSurName();
                            List<Client> clientList = clientDao.getClientBySurName(surName);
                            ct.printList(clientList);

                        } else if (toDo.equalsIgnoreCase("5")) {       // znajdowanie klientA po id number
                            Long id = ct.getId();
                            Set<Client> clientSet = clientDao.getClientById(id);
                            ct.printSet(clientSet);

                        } else if (toDo.equalsIgnoreCase("q")) {
                            continue;
                        }
                    } while (!toDo.equalsIgnoreCase("q"));
                    break;

                case "3":
                    do {
                        System.out.println("1. Listowanie książek wypożyczonych przez klienta");
                        System.out.println("2. Listowanie książek nie zwróconych przez klienta");
                        System.out.println("3. Listowanie książek których SĄ jeszcze kopie");
                        System.out.println("4. Listowanie książek których nie ma już kopii");
                        System.out.println("5. Listowanie książek które nie zostały zwrócone");
                        System.out.println("5. Listowanie książek które zostały zwrócone w ciągu ostatnich N godzin.");
                        System.out.println("6. Listowanie książek które zostały wypożyczone w ciągu ostatnich 24 h");
                        System.out.println("7. Listowanie najczęściej wypożyczanych książek");
                        System.out.println("8. Znalezienie najbardziej aktywnego klienta(takiego który najczęściej wypożycza)");
                        System.out.println("q. Cofnij...");
                        toDo = ct.menu();

                        if (toDo.equalsIgnoreCase("1")) {
                            ct.printSet(entityDao.getAll(Client.class));
                            Long id = ct.getId();
                            List<Book> booksLentByClient = bookLentDao.getBooksLentByClient(id);
                            ct.printList(booksLentByClient);
                            ct.waitForUser();
                        }
                    } while (!toDo.equalsIgnoreCase("q"));

                    break;


            }
        } while (!toDo.equalsIgnoreCase("0"));
    }
}
