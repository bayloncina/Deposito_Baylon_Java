import java.sql.*;

public class Esercizio1 {
    public static void main(String[] args) throws Exception {

        String host = "localhost";
        String port = "3306";
        String url = "jdbc:mysql://" + host + ":" + port + "/sakila";
        String user = "root";
        String psw = "";

        // Recuperare i 10 film più noleggiati dalla tabella rental.

        String query = "select film.title, count(*) as noleggi_totali " +
                "from rental " +
                "join inventory on rental.inventory_id = inventory.inventory_id " +
                "join film on inventory.film_id = film.film_id " +
                "group by film.title " +
                "order by noleggi_totali desc " +
                "limit 10";

        try (Connection conn = DriverManager.getConnection(url, user, psw);
                Statement pstmt = conn.createStatement()) {

            // La query non ha parametri ? quindi si esegue direttamente
            // senza argomenti il PreparedStatement conosce già la query
            ResultSet result = pstmt.executeQuery(query);

            // ResultSetMetaData contiene le informazioni sulla struttura del risultato
            ResultSetMetaData meta = result.getMetaData();
            // Recupera il numero totale di colonne restituite dalla query
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
        }
    }
}