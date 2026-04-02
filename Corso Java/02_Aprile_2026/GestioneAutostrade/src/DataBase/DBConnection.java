// DBConnection.java
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static Connection connection = null;

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String URL_DB = "jdbc:mysql://localhost:3306/autostrada";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DBConnection() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // prima si connette senza specificare il database
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                creaDatabase();

                // poi si riconnette al database autostrada
                connection = DriverManager.getConnection(URL_DB, USER, PASSWORD);
                creaTabella();

                System.out.println("Connessione al database avvenuta!");
            } catch (SQLException e) {
                System.out.println("Errore connessione: " + e.getMessage());
            }
        }
        return connection;
    }

    // ── crea il database se non esiste ──
    private static void creaDatabase() throws SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate("CREATE DATABASE IF NOT EXISTS autostrada");
        System.out.println("Database 'autostrada' pronto!");
    }

    // ── crea la tabella se non esiste ──
    // ho optato per una tabella singola per semplificare
    private static void creaTabella() throws SQLException {
        Statement st = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS veicoli (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "tipo VARCHAR(20) NOT NULL, " +
                "targa VARCHAR(10) NOT NULL, " +
                "velocita DOUBLE NOT NULL, " +
                "numero_assi INT NOT NULL, " +
                "caratteristica VARCHAR(50), " +
                "pedaggio DOUBLE NOT NULL" +
                ")";
        st.executeUpdate(sql);
        System.out.println("Tabella veicoli pronta");
    }
}