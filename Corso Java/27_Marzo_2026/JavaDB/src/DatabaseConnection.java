/*Importa tutto il package java.sql, 
che contiene le classi necessarie per lavorare con i database: Connection, DriverManager, SQLException*/

import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) throws Exception {
        // parametri di connessione
        String url = "jdbc:mysql://localhost:3306/sakila";
        String user = "root";
        String psw = "";

        try {
            // Connection è un oggetto che rappresenta la sessione attiva con il database.
            // DriverManager è una classe statica di java.sql che fa da intermediario tra il
            // codice Java e il database.
            // Il suo compito è trovare il driver giusto e usarlo per stabilire la
            // connessione.
            Connection conn = DriverManager.getConnection(url, user, psw);
            System.out.println("Connessione andata a buon fine");
            // ------------------------------------------------------------------------------------------
            // Statement è un oggetto che rappresenta una istruzione SQL da inviare al
            // database.
            // Lo crei chiamando createStatement() sull'oggetto conn (la connessione
            // aperta).
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM actor";
            // la query viene effettivamente inviata ed eseguita sul database.
            ResultSet result = stmt.executeQuery(query);
            System.out.println(result);

            // ------------------------------------------------------------------------------------------------------
            String query2 = "SELECT * FROM actor where actor_id=?";
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            // posizone del ? + valore in questo caso l'id di attore
            stmt2.setInt(1, 1);
            ResultSet result2 = stmt2.executeQuery();
            System.out.println(result2);
            // cattura riga per riga
            while (result2.next()) {
                System.out.println("Attore: " + result2.getString("first_name"));
            }
            // -------------------------------------------------------------------------------

            // ResultSetMetaData contiene le informazioni SULLA struttura della tabella
            // (nomi colonne, tipi, numero colonne...)
            ResultSetMetaData meta = result.getMetaData();

            // getColumnCount() restituisce il numero totale di colonne della tabella
            int numColum = meta.getColumnCount();

            // itera le righe
            while (result.next()) {
                // cicla per il numero delle colonne
                for (int i = 1; i <= numColum; i++) {

                    // partiamo dalla prima colonna (JDBC conta da 1, non da 0!)
                    String colName = meta.getColumnName(i);

                    // non sapendo il tipo del valore usiamo Object
                    Object val = result.getObject(i);

                    // stampa nome di colonna e valore
                    System.out.print(colName + ": " + val);

                    // stampa il separatore | solo se NON siamo all'ultima colonna
                    if (i < numColum) {
                        System.out.print(" | ");
                    }
                }
                // va a capo dopo ogni riga
                System.out.println();
            }

    // ------------------------------------------------------------------------

            conn.close();
            System.out.println("Connessione JDBC ok");

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}
