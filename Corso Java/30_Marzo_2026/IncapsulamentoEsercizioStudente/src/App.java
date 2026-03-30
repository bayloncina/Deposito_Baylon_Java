package IncapsulamentoEsercizioStudente.src;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        // test connessione
        try {
            Connection conn = DataBaseConnection.getConnection();
            System.out.println("Connessione riuscita!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Errore connessione: " + e.getMessage());
            return;
        }

        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        mostraMenu(scannerStr, scannerInt);
    }

    private static void mostraMenu(Scanner scannerStr, Scanner scannerInt) {
        int scelta = 0;

        while (scelta != 5) {
            System.out.println("\nMENU");
            System.out.println("1. Mostra tutti gli studenti");
            System.out.println("2. Aggiungi uno studente");
            System.out.println("3. Modifica voto");
            System.out.println("4. Cerca studente per nome");
            System.out.println("5. Esci");
            System.out.print("Scegli un'operazione: ");

            scelta = scannerInt.nextInt();

            switch (scelta) {
                case 1:
                    mostraTutti();
                    break;
                case 2:
                    aggiungiStudente(scannerStr, scannerInt);
                    break;
                case 3:
                    modificaVoto(scannerStr, scannerInt);
                    break;
                case 4:
                    cercaPerNome(scannerStr);
                    break;
                case 5:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }
    }

    // chiama il metodo statico di Studente che fa la SELECT sul db
    private static void mostraTutti() {
        Studente.mostraTutti();
    }

    // legge nome e voto e salva nel db tramite INSERT
    private static void aggiungiStudente(Scanner scannerStr, Scanner scannerInt) {
        System.out.print("Inserisci il nome: ");
        String nome = scannerStr.nextLine();
        System.out.print("Inserisci il voto (0-10): ");
        int voto = scannerInt.nextInt();
        new Studente(nome, voto).salva();
    }

    // legge nome e nuovo voto e aggiorna nel db tramite UPDATE
    private static void modificaVoto(Scanner scannerStr, Scanner scannerInt) {
        System.out.print("Inserisci il nome dello studente: ");
        String nome = scannerStr.nextLine();
        System.out.print("Inserisci il nuovo voto (0-10): ");
        int nuovoVoto = scannerInt.nextInt();
        Studente.modificaVoto(nome, nuovoVoto);
    }

    // legge il nome e cerca nel db tramite SELECT con WHERE
    private static void cercaPerNome(Scanner scannerStr) {
        System.out.print("Inserisci il nome da cercare: ");
        String nome = scannerStr.nextLine();
        Studente.cercaPerNome(nome);
    }
}