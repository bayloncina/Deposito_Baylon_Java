import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title;
    String author;
    boolean isAvailable;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String toString() {
        String disponibilita = isAvailable ? "Disponibile" : "Non disponibile";
        return "Titolo: " + title + " Autore: " + author + "  " + disponibilita;
    }
}

class User {
    String name;
    ArrayList<Book> libriInPrestito = new ArrayList<>();

    User(String name) {
        this.name = name;
    }

    // restituisce true se l'utente può prendere altri libri (massimo 3)
    public boolean canBorrow() {
        return libriInPrestito.size() < 3;
    }

    public String toString() {
        return "Utente: " + name + " Libri in prestito: " + libriInPrestito.size();
    }
}

class Library {

    ArrayList<Book> booksList = new ArrayList<>();
    ArrayList<User> usersList = new ArrayList<>();

    public void addBook(Book book) {
        booksList.add(book);
        System.out.println("Libro aggiunto: " + book.title);
    }

    public void displayBooks() {
        if (booksList.isEmpty()) {
            System.out.println("Nessun libro in biblioteca.");
            return;
        }
        System.out.println("\nLISTA DEI LIBRI IN BIBLIOTECA");
        for (Book book : booksList) {
            System.out.println(book.toString());
        }
    }

    // permette a un utente di prendere in prestito un libro (max 3 libri per
    // utente)
    public void borrowBook(String title, User user) {
        if (!user.canBorrow()) {
            System.out.println("Hai già 3 libri in prestito. Restituiscine uno prima.");
            return;
        }
        // scorro la lista per trovare il libro
        for (Book book : booksList) {
            if (book.title.equalsIgnoreCase(title)) {
                if (book.isAvailable) {
                    book.isAvailable = false;
                    user.libriInPrestito.add(book); // aggiungo il libro alla lista dell'utente
                    System.out.println("Hai preso in prestito: " + book.title);
                } else {
                    System.out.println("Il libro " + book.title + " non è disponibile.");
                }
                return; // libro trovato, esco dal metodo
            }
        }
        System.out.println("Libro non trovato.");
    }

    public void returnBook(String title, User user) {
        // scorro solo i libri in prestito dell'utente
        for (Book book : user.libriInPrestito) {
            if (book.title.equalsIgnoreCase(title)) {
                // segno il libro come disponibile
                book.isAvailable = true;
                // rimuovo il libro dalla lista dell'utente
                user.libriInPrestito.remove(book);
                System.out.println("Hai restituito: " + book.title);
                return;
            }
        }
        System.out.println("Non hai questo libro in prestito.");
    }

    public void addUser(User user) {
        if (findUser(user.name) != null) {
            System.out.println("Utente già esistente.");
        } else {
            usersList.add(user);
            System.out.println("Utente aggiunto: " + user.name);
        }
    }

    public User findUser(String name) {
        for (User user : usersList) {
            if (user.name.equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    // mostra i libri in prestito di un utente specifico
    public void displayUserBooks(String userName) {
        User user = findUser(userName);
        if (user == null) {
            System.out.println("Utente non trovato.");
            return;
        }
        System.out.println("\nLIBRI IN PRESTITO DI: " + user.name);
        if (user.libriInPrestito.isEmpty()) {
            System.out.println("Nessun libro in prestito.");
            return;
        }
        for (Book book : user.libriInPrestito) {
            System.out.println(book.toString());
        }
    }

    public void displayUsers() {
        if (usersList.isEmpty()) {
            System.out.println("Nessun utente registrato.");
            return;
        }
        System.out.println("\nLISTA UTENTI");
        for (User user : usersList) {
            System.out.println(user.toString());
        }
    }
}

public class GestioneBiblioteca {
    public static void main(String[] args) {

        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        Library library = new Library();

        // utenti di default
        library.addUser(new User("Miriam"));
        library.addUser(new User("Giorgia"));
        library.addUser(new User("Giovanni"));

        // libri di default
        library.addBook(new Book("Il Nome della Rosa", "Umberto Eco"));
        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("Il Piccolo Principe", "Antoine de Saint-Exupery"));
        library.addBook(new Book("Harry Potter", "J.K. Rowling"));
        library.addBook(new Book("Il Signore degli Anelli", "J.R.R. Tolkien"));

        mostraMenu(library, scannerStr, scannerInt);
    }

    private static void mostraMenu(Library library, Scanner scannerStr, Scanner scannerInt) {
        int scelta = 0;

        while (scelta != 9) { // fix: era != 8 ma Esci è il 9
            System.out.println("\nMENU");
            System.out.println("1. Mostra tutti i libri");
            System.out.println("2. Aggiungi un libro");
            System.out.println("3. Prendi in prestito un libro");
            System.out.println("4. Restituisci un libro");
            System.out.println("5. Mostra utenti");
            System.out.println("6. Aggiungi utente");
            System.out.println("7. Cerca libro");
            System.out.println("8. Visualizza libri in prestito di un utente");
            System.out.println("9. Esci");
            System.out.print("Scegli un'operazione: ");

            scelta = scannerInt.nextInt();

            switch (scelta) {
                case 1:
                    library.displayBooks();
                    break;

                case 2:

                    System.out.print("Inserisci il titolo: ");
                    String title = scannerStr.nextLine();
                    System.out.print("Inserisci l'autore: ");
                    String author = scannerStr.nextLine();
                    library.addBook(new Book(title, author));
                    break;

                case 3:

                    System.out.print("Inserisci il tuo nome: ");
                    String nomeP = scannerStr.nextLine();
                    User utenteP = library.findUser(nomeP);
                    if (utenteP == null) {
                        System.out.println("Utente non trovato.");
                        break;
                    }

                    library.displayBooks();
                    System.out.print("Inserisci il titolo del libro: ");
                    String titoloPrestito = scannerStr.nextLine();
                    library.borrowBook(titoloPrestito, utenteP);
                    break;

                case 4:
                    System.out.print("Inserisci il tuo nome: ");
                    String nomeR = scannerStr.nextLine();
                    User utenteR = library.findUser(nomeR);
                    if (utenteR == null) {
                        System.out.println("Utente non trovato.");
                        break;
                    }
                    System.out.print("Inserisci il titolo da restituire: ");
                    String titoloRestituzione = scannerStr.nextLine();
                    library.returnBook(titoloRestituzione, utenteR);
                    break;

                case 5:
                    library.displayUsers();
                    break;

                case 6:
                    System.out.print("Inserisci il nome dell'utente: ");
                    String nomeNuovo = scannerStr.nextLine();
                    library.addUser(new User(nomeNuovo));
                    break;

                case 7:
                    System.out.print("Inserisci titolo o autore: ");
                    String keyword = scannerStr.nextLine();
                    // cerca per titolo o autore
                    boolean trovato = false;
                    for (Book b : library.booksList) {
                        if (b.title.equalsIgnoreCase(keyword) || b.author.equalsIgnoreCase(keyword)) {
                            System.out.println(b.toString());
                            trovato = true;
                        }
                    }
                    if (!trovato)
                        System.out.println("Nessun risultato trovato.");
                    break;

                case 8:
                    System.out.print("Inserisci il nome dell'utente: ");
                    String nomeLibri = scannerStr.nextLine();
                    library.displayUserBooks(nomeLibri);
                    break;

                case 9:
                    System.out.println("Arrivederci!");
                    break;

                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }
    }
}