import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBaseManagerOrdini {

    /*
     * CREATE DATABASE magazzino;
     * USE magazzino;
     * 
     * CREATE TABLE ordini (
     * id INT AUTO_INCREMENT PRIMARY KEY,
     * cliente VARCHAR(50),
     * prodotto VARCHAR(50),
     * quantita INT,
     * stato VARCHAR(50)
     * );
     */

    private static DataBaseManagerOrdini istanzaDb;

    // rappresenta il canale aperto verso il database
    private Connection connection;

    // credenziali di accesso al database
    private static final String URL = "jdbc:mysql://localhost:3306/magazzino";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DataBaseManagerOrdini() {
    }

    // restituisce sempre la stessa istanza, la crea solo la prima volta
    public static DataBaseManagerOrdini getIstanzaDb() {
        if (istanzaDb == null) {
            istanzaDb = new DataBaseManagerOrdini();
        }
        return istanzaDb;
    }

    // apre la connessione reale
    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Errore di connessione: " + e.getMessage());
        }
    }

    // inserisce un nuovo ordine nella tabella ordini
    public void salvaOrdine(String cliente, String prodotto, int quantita, StatoOrdine stato) {
        try {
            String query = "INSERT INTO ordini (cliente, prodotto, quantita, stato) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, cliente);
            stmt.setString(2, prodotto);
            stmt.setInt(3, quantita);
            stmt.setString(4, stato.name());
            stmt.executeUpdate();
            System.out.println("Ordine Salvato!");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    // cerca un ordine nel database per id e stampa il risultato
    public void cercaOrdine(int idOrdine) {
        try {
            String query = "SELECT * FROM ordini WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, idOrdine);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Ordine Trovato: " + rs.getString("id")
                        + " | Cliente: " + rs.getString("cliente")
                        + " | Prodotto: " + rs.getString("prodotto")
                        + " | Quantità: " + rs.getInt("quantita")
                        + " | Stato: " + rs.getString("stato"));
            } else {
                System.out.println("Ordine non trovato.");
            }
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    // visualizza tutti gli ordini presenti nella tabella
    public void visualizzaTuttiOrdini() {
        try {
            String query = "SELECT * FROM ordini";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            boolean trovato = false;
            while (rs.next()) {
                trovato = true;
                System.out.println("ID: " + rs.getInt("id")
                        + " | Cliente: " + rs.getString("cliente")
                        + " | Prodotto: " + rs.getString("prodotto")
                        + " | Quantità: " + rs.getInt("quantita")
                        + " | Stato: " + rs.getString("stato"));
            }

            if (!trovato) {
                System.out.println("Nessun ordine presente.");
            }

        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    // aggiorna lo stato di un ordine esistente tramite id
    public void aggiornaStato(int idOrdine, String nuovoStato) {
        try {
            String query = "UPDATE ordini SET stato = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nuovoStato);
            stmt.setInt(2, idOrdine);

            int righeModificate = stmt.executeUpdate();
            if (righeModificate > 0) {
                System.out.println("Stato aggiornato correttamente.");
            } else {
                System.out.println("Nessun ordine trovato con ID: " + idOrdine);
            }

        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public Ordine getOrdineById(int id) {
    try {
        String query = "SELECT * FROM ordini WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Ordine ordine = new Ordine(
                rs.getString("cliente"),
                rs.getString("prodotto"),
                rs.getInt("quantita"),
                StatoOrdine.valueOf(rs.getString("stato"))
            );
            ordine.setId(rs.getInt("id"));
            return ordine;
        }
    } catch (Exception e) {
        System.out.println("Errore: " + e.getMessage());
    }
    return null;
}
}