import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        /*
         * // prendi l'istanza e connettiti
         * DataBaseManager db1 = DataBaseManager.getIstanzaDb();
         * db1.connect();
         * 
         * // prendi l'istanza da un altro punto e connettiti ancora
         * DataBaseManager db2 = DataBaseManager.getIstanzaDb();
         * db2.connect();
         * 
         * // dimostra che è la stessa istanza
         * System.out.println("Totale connessioni: " + db1.getConnectionCount());
         * 
         * if (db1 == db2) {
         * System.out.println("È la stessa istanza!");
         * }
         */

        Scanner scanner = new Scanner(System.in);

        // connessione al db
        DataBaseManager db = DataBaseManager.getIstanzaDb();
        db.connect();

        Utente utente = Utente.getIstanzaUtente();

        int scelta = 0;
        while (scelta != 3) {
            System.out.println("\n1. Registrati");
            System.out.println("2. Cerca utente");
            System.out.println("3. Esci");
            System.out.print("Scelta: ");
            scelta = Integer.parseInt(scanner.nextLine());

            if (scelta == 1) {
                System.out.print("Inserisci nome: ");
                utente.setNome(scanner.nextLine());
                System.out.print("Inserisci password: ");
                utente.setPassword(scanner.nextLine());
                db.salvaUtente(utente.getNome(), utente.getPassword());

            } else if (scelta == 2) {
                System.out.print("Inserisci nome da cercare: ");
                String nomeCerca = scanner.nextLine();
                db.richiamaUtente(nomeCerca);

            } else if (scelta == 3) {
                System.out.println("Arrivederci!");

            } else {
                System.out.println("Scelta non valida.");
            }
        }

        scanner.close();

    }
}
