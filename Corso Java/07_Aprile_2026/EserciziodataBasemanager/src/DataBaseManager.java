import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBaseManager {

    private static DataBaseManager istanzaDb;
    // conta quante volte ci si è connessi
    private int connectionCount = 0;
    // rappresenta il canale aperto verso il database
    private Connection connection;

    // credenziali di accesso al database
    private static final String URL = "jdbc:mysql://localhost:3306/esempio_login_singleton";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DataBaseManager() {
    }

    // restituisce sempre la stessa istanza, la crea solo la prima volta
    public static DataBaseManager getIstanzaDb() {
        if (istanzaDb == null) {
            istanzaDb = new DataBaseManager();
        }
        return istanzaDb;
    }

    // apre la connessione reale
    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connectionCount++;
            System.out.println("Connessione stabilita. Connessioni attive: " + connectionCount);
        } catch (Exception e) {
            System.out.println("Errore di connessione: " + e.getMessage());
        }
    }

    // restituisce il numero totale di connessioni effettuate
    public int getConnectionCount() {
        return connectionCount;
    }

    // inserisce un nuovo utente nella tabella utenti
    public void salvaUtente(String nome, String password) {
        try {
            String query = "INSERT INTO utenti (nome, password) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nome);
            stmt.setString(2, password);
            stmt.executeUpdate();
            System.out.println("Utente salvato!");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    // cerca un utente nel database per nome e stampa il risultato
    public void richiamaUtente(String nome) {
        try {
            String query = "SELECT * FROM utenti WHERE nome = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Utente trovato: " + rs.getString("nome"));
            } else {
                System.out.println("Utente non trovato.");
            }
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}