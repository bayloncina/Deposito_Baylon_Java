package IncapsulamentoEsercizioStudente.src;
import java.sql.*;

public class Studente {

    // attributi privati: accessibili solo tramite getter e setter (incapsulamento)
    private String nome;
    private int voto;
    
//l'id lo autoincremento ned db
    Studente(String nome, int voto) {
        this.nome = nome;
        this.voto = voto;
    }

    // getter: restituisce il nome dello studente
    public String getNome() { return nome; }

    // getter: restituisce il voto dello studente
    public int getVoto()    { return voto; }

    // setter: modifica il nome dello studente
    public void setNome(String nome) { this.nome = nome; }

    // setter: modifica il voto solo se è compreso tra 0 e 10
    public void setVoto(int voto) {
        // controllo validità del voto con operatore OR
        if (voto < 0 || voto > 10) {
            System.out.println("Voto non valido.");
            return; // esce dal metodo senza modificare il voto
        }
        this.voto = voto;
    }

    // salva lo studente corrente nel database (INSERT)
    public void salva() {
        // ? sono i parametri che verranno sostituiti dal PreparedStatement
        String query = "INSERT INTO studenti (nome, voto) VALUES (?, ?)";
        try {
            Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, this.nome); // sostituisce il primo ?
            stmt.setInt(2, this.voto);    // sostituisce il secondo ?

            stmt.executeUpdate(); // esegue la query
            System.out.println("Studente salvato: " + this.nome);

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Errore salva: " + e.getMessage());
        }
    }

    // metodo statico: non serve creare un oggetto per chiamarlo
    // mostra tutti gli studenti presenti nel database (SELECT)
    public static void mostraTutti() {
        String query = "SELECT * FROM studenti";
        try {
            Connection conn = DataBaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query); // rs contiene i risultati della query

            System.out.println("\nLISTA STUDENTI");

            boolean trovato = false;
            // scorro tutti i risultati riga per riga
            while (rs.next()) {
                trovato = true;
                System.out.println("ID: "    + rs.getInt("id") +
                        " | Nome: " + rs.getString("nome") +
                        " | Voto: " + rs.getInt("voto"));
            }

            if (!trovato) System.out.println("Nessuno studente presente.");

            // chiudo sempre ResultSet, Statement e Connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Errore mostraTutti: " + e.getMessage());
        }
    }

    // metodo statico: cerca uno studente per nome nel database (SELECT con WHERE)
    public static void cercaPerNome(String nome) {
        // uso PreparedStatement per evitare SQL injection
        String query = "SELECT * FROM studenti WHERE nome = ?";
        try {
            Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, nome); // sostituisce il ?

            ResultSet rs = stmt.executeQuery();

            // rs.next() restituisce true se ha trovato almeno un risultato
            if (rs.next()) {
                System.out.println("Trovato - ID: " + rs.getInt("id") +
                        " | Nome: " + rs.getString("nome") +
                        " | Voto: " + rs.getInt("voto"));
            } else {
                System.out.println("Studente non trovato.");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Errore cercaPerNome: " + e.getMessage());
        }
    }

    // metodo statico: modifica il voto di uno studente nel database (UPDATE)
    public static void modificaVoto(String nome, int nuovoVoto) {
        // controllo validità prima di fare la query
        if (nuovoVoto < 0 || nuovoVoto > 10) {
            System.out.println("Voto non valido.");
            return;
        }
        String query = "UPDATE studenti SET voto = ? WHERE nome = ?";
        try {
            Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, nuovoVoto); // nuovo voto
            stmt.setString(2, nome);   // nome dello studente da aggiornare

            // executeUpdate restituisce il numero di righe modificate
            int righe = stmt.executeUpdate();

            if (righe == 0) {
                // nessuna riga modificata = studente non trovato
                System.out.println("Studente non trovato.");
            } else {
                System.out.println("Voto aggiornato!");
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Errore modificaVoto: " + e.getMessage());
        }
    }

    public String toString() {
        return "Nome: " + nome + " | Voto: " + voto;
    }
}