import java.sql.*;
import java.util.Scanner;

public class Esercizio2 {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        String port = "3306";
        String url = "jdbc:mysql://" + host + ":" + port + "/sakila";
        String user = "root";
        String psw = "";

        // Creare un programma che chieda
        // all'utente di inserire una stringa (es."ACADEMY"). Il programma deve
        // restituire l'elenco
        // di tutti i film che contengono quella stringa nel
        // titolo, mostrando: Titolo, Descrizione e Anno di rilascio.

        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci una stringa da cercare nel titolo: ");
        String input = scanner.nextLine();

        String query = "SELECT film.title, film.description, film.release_year " +
                "FROM film " +
                "WHERE title LIKE ?";

        try (Connection conn = DriverManager.getConnection(url, user, psw);
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + input + "%");
            ResultSet result = pstmt.executeQuery();

            ResultSetMetaData meta = result.getMetaData();
            int numColum = meta.getColumnCount();

            // Itera riga per riga il ResultSet.
            // next() sposta il cursore alla riga successiva:
            // restituisce true finché ci sono righe, false quando finiscono
            while (result.next()) {
                for (int i = 1; i <= numColum; i++) {
                    // Recupera il nome della colonna dai metadati
                    String colName = meta.getColumnName(i);
                    // Recupera il valore come Object perché non conosciamo il tipo a priori
                    Object val = result.getObject(i);
                    System.out.print(colName + ": " + val);
                    if (i < numColum) {
                        System.out.print(" | ");
                    }
                }
                System.out.println();
            }
            scanner.close();
        }
    }

}
