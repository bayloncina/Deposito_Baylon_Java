import java.sql.*;
import java.util.Scanner;

public class GestinaleVoti2 {

    // Parametri di connessione al database MySQL
    static final String HOST = "localhost";
    static final String PORT = "3306";
    static final String DB = "gestionale_scolastico";
    static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB;
    static final String USER = "root";
    static final String PSW = "";

    public static void main(String[] args) {

        // Crea il database e le tabelle se non esistono ancora
        inizializzaDB();

        Scanner scanner = new Scanner(System.in);
        int scelta = -1;

        // Il menu rimane attivo finché l'utente non sceglie 0 (Esci)
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║     GESTIONALE SCOLASTICO        ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Gestione Classi              ║");
            System.out.println("║  2. Gestione Studenti            ║");
            System.out.println("║  3. Gestione Voti                ║");
            System.out.println("║  0. Esci                         ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Scelta: ");

            try {
                scelta = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Inserisci un numero valido.");
                continue;
            }

            // Instrada l'utente al sottomenu corretto in base alla scelta
            switch (scelta) {
                case 1 -> menuClassi(scanner);
                case 2 -> menuStudenti(scanner);
                case 3 -> menuVoti(scanner);
                case 0 -> System.out.println("Arrivederci!");
                default -> System.out.println("Scelta non valida.");
            }
        } while (scelta != 0);

        scanner.close();
    }

    // ─────────────────────────────────────────
    // MENU CLASSI
    // ─────────────────────────────────────────

    // Mostra il sottomenu per la gestione delle classi
    static void menuClassi(Scanner sc) {
        System.out.println("\n── Gestione Classi ──");
        System.out.println("1. Inserisci classe");
        System.out.println("2. Visualizza tutte le classi");
        System.out.println("3. Elimina classe");
        System.out.print("Scelta: ");

        try {
            int s = Integer.parseInt(sc.nextLine().trim());
            switch (s) {
                case 1 -> inserisciClasse(sc);
                case 2 -> visualizzaClassi();
                case 3 -> eliminaClasse(sc);
                default -> System.out.println("Scelta non valida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Inserisci un numero valido.");
        }
    }

    // Chiede i dati di una nuova classe e la inserisce nella tabella Classe
    static void inserisciClasse(Scanner sc) {
        System.out.print("Nome classe (es. Prima A): ");
        String nome = sc.nextLine().trim();
        System.out.print("Anno scolastico (es. 2024/2025): ");
        String anno = sc.nextLine().trim();
        System.out.print("Sezione (es. A): ");
        String sezione = sc.nextLine().trim();
        System.out.print("Capienza (numero alunni): ");
        int capienza;
        try {
            capienza = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Capienza non valida.");
            return;
        }

        // Usa PreparedStatement per evitare SQL injection e passare i parametri in modo
        // sicuro
        String sql = "INSERT INTO Classe (nome, anno_scolastico, sezione, capienza) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PSW);
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, anno);
            ps.setString(3, sezione);
            ps.setInt(4, capienza);
            ps.executeUpdate();
            System.out.println("Classe inserita con successo.");
        } catch (SQLException e) {
            System.err.println("Errore inserimento classe: " + e.getMessage());
        }
    }

    // Recupera e stampa tutte le classi presenti nel database
    static void visualizzaClassi() {
        String sql = "SELECT * FROM Classe";
        try (Connection conn = DriverManager.getConnection(URL, USER, PSW);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // Intestazione della tabella
            System.out.println("ID | Nome | Anno | Sezione | Capienza");
            System.out.println("----------------------------------------");

            boolean trovato = false;
            // Scorre ogni riga del risultato e stampa i valori colonna per colonna
            while (rs.next()) {
                trovato = true;
                System.out.println(
                        rs.getInt("id_classe") + " | " +
                                rs.getString("nome") + " | " +
                                rs.getString("anno_scolastico") + " | " +
                                rs.getString("sezione") + " | " +
                                rs.getInt("capienza"));
            }
            if (!trovato)
                System.out.println("Nessuna classe presente.");
        } catch (SQLException e) {
            System.err.println("Errore lettura classi: " + e.getMessage());
        }
    }

    // Mostra le classi esistenti e chiede l'ID di quella da eliminare
    static void eliminaClasse(Scanner sc) {
        visualizzaClassi();
        System.out.print("ID classe da eliminare: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            String sql = "DELETE FROM Classe WHERE id_classe = ?";
            try (Connection conn = DriverManager.getConnection(URL, USER, PSW);
                    PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                int righe = ps.executeUpdate();
                // executeUpdate restituisce il numero di righe modificate
                if (righe > 0)
                    System.out.println("Classe eliminata.");
                else
                    System.out.println("Classe non trovata.");
            } catch (SQLException e) {
                System.err.println("Errore eliminazione: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("ID non valido.");
        }
    }

    // ─────────────────────────────────────────
    // MENU STUDENTI
    // ─────────────────────────────────────────

    // Mostra il sottomenu per la gestione degli studenti
    static void menuStudenti(Scanner sc) {
        System.out.println("\n── Gestione Studenti ──");
        System.out.println("1. Inserisci studente");
        System.out.println("2. Visualizza tutti gli studenti");
        System.out.println("3. Elimina studente");
        System.out.print("Scelta: ");

        try {
            int s = Integer.parseInt(sc.nextLine().trim());
            switch (s) {
                case 1 -> inserisciStudente(sc);
                case 2 -> visualizzaStudenti();
                case 3 -> eliminaStudente(sc);
                default -> System.out.println("Scelta non valida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Inserisci un numero valido.");
        }
    }

    // Chiede i dati di un nuovo studente e lo inserisce nella tabella Studente
    static void inserisciStudente(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine().trim();
        System.out.print("Cognome: ");
        String cognome = sc.nextLine().trim();
        System.out.print("Data di nascita (YYYY-MM-DD): ");
        String data = sc.nextLine().trim();
        System.out.print("Codice fiscale (16 caratteri): ");
        // toUpperCase per uniformare il formato del codice fiscale
        String cf = sc.nextLine().trim().toUpperCase();
        if (cf.length() != 16) {
            System.out.println("Codice fiscale non valido (deve essere 16 caratteri).");
            return;
        }

        // Mostra le classi disponibili prima di chiedere l'ID
        visualizzaClassi();
        System.out.print("ID classe: ");
        int idClasse;
        try {
            idClasse = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID classe non valido.");
            return;
        }

        String sql = "INSERT INTO Studente (nome, cognome, data_di_nascita, codice_fiscale, id_classe) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PSW);
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, cognome);
            // Date.valueOf converte la stringa "YYYY-MM-DD" in java.sql.Date
            ps.setDate(3, Date.valueOf(data));
            ps.setString(4, cf);
            ps.setInt(5, idClasse);
            ps.executeUpdate();
            System.out.println("Studente inserito con successo.");
        } catch (SQLException e) {
            System.err.println("Errore inserimento studente: " + e.getMessage());
        }
    }

    // Recupera tutti gli studenti con una JOIN sulla tabella Classe per mostrare il
    // nome della classe
    static void visualizzaStudenti() {
        String sql = "SELECT s.id_studente, s.nome, s.cognome, s.data_di_nascita, " +
                "s.codice_fiscale, c.nome AS classe " +
                "FROM Studente s JOIN Classe c ON s.id_classe = c.id_classe " +
                "ORDER BY s.cognome, s.nome";
        try (Connection conn = DriverManager.getConnection(URL, USER, PSW);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // Intestazione della tabella
            System.out.println("ID | Nome | Cognome | Data nasc. | CF | Classe");
            System.out.println("--------------------------------------------------");

            boolean trovato = false;
            while (rs.next()) {
                trovato = true;
                System.out.println(
                        rs.getInt("id_studente") + " | " +
                                rs.getString("nome") + " | " +
                                rs.getString("cognome") + " | " +
                                rs.getDate("data_di_nascita") + " | " +
                                rs.getString("codice_fiscale") + " | " +
                                rs.getString("classe"));
            }
            if (!trovato)
                System.out.println("Nessuno studente presente.");
        } catch (SQLException e) {
            System.err.println("Errore lettura studenti: " + e.getMessage());
        }
    }

    // Elimina uno studente per ID; grazie a ON DELETE CASCADE vengono eliminati
    // anche i suoi voti
    static void eliminaStudente(Scanner sc) {
        visualizzaStudenti();
        System.out.print("ID studente da eliminare: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            String sql = "DELETE FROM Studente WHERE id_studente = ?";
            try (Connection conn = DriverManager.getConnection(URL, USER, PSW);
                    PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                int righe = ps.executeUpdate();
                if (righe > 0)
                    System.out.println("Studente eliminato (e i suoi voti per CASCADE).");
                else
                    System.out.println("Studente non trovato.");
            } catch (SQLException e) {
                System.err.println("Errore eliminazione: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("ID non valido.");
        }
    }

    // ─────────────────────────────────────────
    // MENU VOTI
    // ─────────────────────────────────────────

    // Mostra il sottomenu per la gestione dei voti
    static void menuVoti(Scanner sc) {
        System.out.println("\n── Gestione Voti ──");
        System.out.println("1. Inserisci voto");
        System.out.println("2. Visualizza voti (per studente)");
        System.out.println("3. Media voti di uno studente");
        System.out.println("4. Elimina voto");
        System.out.print("Scelta: ");

        try {
            int s = Integer.parseInt(sc.nextLine().trim());
            switch (s) {
                case 1 -> inserisciVoto(sc);
                case 2 -> visualizzaVoti(sc);
                case 3 -> mediaVoti(sc);
                case 4 -> eliminaVoto(sc);
                default -> System.out.println("Scelta non valida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Inserisci un numero valido.");
        }
    }

    // Chiede i dati di un nuovo voto e lo inserisce nella tabella Voto
    static void inserisciVoto(Scanner sc) {
        // Mostra gli studenti disponibili prima di chiedere l'ID
        visualizzaStudenti();
        System.out.print("ID studente: ");
        int idStudente;
        try {
            idStudente = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID non valido.");
            return;
        }
        System.out.print("Materia: ");
        String materia = sc.nextLine().trim();
        System.out.print("Data (YYYY-MM-DD): ");
        String data = sc.nextLine().trim();
        System.out.print("Voto (es. 7.5): ");
        float valore;
        try {
            valore = Float.parseFloat(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Voto non valido.");
            return;
        }
        // Validazione: il voto deve essere compreso tra 1 e 10
        if (valore < 1 || valore > 10) {
            System.out.println("Il voto deve essere compreso tra 1 e 10.");
            return;
        }

        String sql = "INSERT INTO Voto (materia, data, valore, id_studente) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PSW);
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, materia);
            ps.setDate(2, Date.valueOf(data));
            ps.setFloat(3, valore);
            ps.setInt(4, idStudente);
            ps.executeUpdate();
            System.out.println("Voto inserito con successo.");
        } catch (SQLException e) {
            System.err.println("Errore inserimento voto: " + e.getMessage());
        }
    }

    // Visualizza i voti: se l'ID è 0 mostra tutti, altrimenti filtra per studente
    static void visualizzaVoti(Scanner sc) {
        visualizzaStudenti();
        System.out.print("ID studente (0 = tutti): ");
        int idStudente;
        try {
            idStudente = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID non valido.");
            return;
        }

        // Query diversa a seconda che si voglia filtrare o no per studente
        String sql = idStudente == 0
                ? "SELECT v.id_voto, s.nome, s.cognome, v.materia, v.data, v.valore " +
                        "FROM Voto v JOIN Studente s ON v.id_studente = s.id_studente " +
                        "ORDER BY s.cognome, v.data"
                : "SELECT v.id_voto, s.nome, s.cognome, v.materia, v.data, v.valore " +
                        "FROM Voto v JOIN Studente s ON v.id_studente = s.id_studente " +
                        "WHERE v.id_studente = ? ORDER BY v.data";

        try (Connection conn = DriverManager.getConnection(URL, USER, PSW)) {
            PreparedStatement ps = conn.prepareStatement(sql);
            // Imposta il parametro solo se si sta filtrando per uno studente specifico
            if (idStudente != 0)
                ps.setInt(1, idStudente);
            ResultSet rs = ps.executeQuery();

            System.out.println("ID | Nome | Cognome | Materia | Data | Voto");
            System.out.println("--------------------------------------------");
            boolean trovato = false;
            while (rs.next()) {
                trovato = true;
                System.out.println(
                        rs.getInt("id_voto") + " | " +
                                rs.getString("nome") + " | " +
                                rs.getString("cognome") + " | " +
                                rs.getString("materia") + " | " +
                                rs.getDate("data") + " | " +
                                rs.getFloat("valore"));
            }
            if (!trovato)
                System.out.println("Nessun voto trovato.");
        } catch (SQLException e) {
            System.err.println("Errore lettura voti: " + e.getMessage());
        }
    }

    // Calcola la media dei voti per materia di uno studente usando AVG() e GROUP BY
    static void mediaVoti(Scanner sc) {
        visualizzaStudenti();
        System.out.print("ID studente: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());

            // AVG calcola la media, COUNT conta il numero di voti per ogni materia
            String sql = "SELECT s.nome, s.cognome, v.materia, AVG(v.valore) AS media, COUNT(*) AS n_voti " +
                    "FROM Voto v JOIN Studente s ON v.id_studente = s.id_studente " +
                    "WHERE v.id_studente = ? GROUP BY v.materia";

            try (Connection conn = DriverManager.getConnection(URL, USER, PSW);
                    PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                System.out.println("Materia | Media | Numero di Voti");
                System.out.println("----------------------------------");
                boolean trovato = false;
                while (rs.next()) {
                    trovato = true;
                    System.out.println(
                            rs.getString("materia") + " | " +
                                    rs.getDouble("media") + " | " +
                                    rs.getInt("n_voti"));
                }
                if (!trovato)
                    System.out.println("Nessun voto trovato per questo studente.");
            } catch (SQLException e) {
                System.err.println("Errore calcolo media: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("ID non valido.");
        }
    }

    // Mostra i voti di uno studente e poi elimina il voto scelto per ID
    static void eliminaVoto(Scanner sc) {
        System.out.print("ID studente per vedere i suoi voti: ");
        try {
            int idS = Integer.parseInt(sc.nextLine().trim());
            // Mostra solo i voti dello studente selezionato
            visualizzaVotiPerStudente(idS);
            System.out.print("ID voto da eliminare: ");
            int idV = Integer.parseInt(sc.nextLine().trim());
            String sql = "DELETE FROM Voto WHERE id_voto = ?";
            try (Connection conn = DriverManager.getConnection(URL, USER, PSW);
                    PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idV);
                int righe = ps.executeUpdate();
                if (righe > 0)
                    System.out.println("Voto eliminato.");
                else
                    System.out.println("Voto non trovato.");
            } catch (SQLException e) {
                System.err.println("Errore eliminazione voto: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("ID non valido.");
        }
    }

    // Metodo di supporto: visualizza solo i voti di uno studente specifico (usato
    // da eliminaVoto)
    static void visualizzaVotiPerStudente(int idStudente) {
        String sql = "SELECT id_voto, materia, data, valore FROM Voto WHERE id_studente = ? ORDER BY data";
        try (Connection conn = DriverManager.getConnection(URL, USER, PSW);
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idStudente);
            ResultSet rs = ps.executeQuery();
            System.out.println("ID | Materia | Data | Voto");
            System.out.println("----------------------------");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id_voto") + " | " +
                                rs.getString("materia") + " | " +
                                rs.getDate("data") + " | " +
                                rs.getFloat("valore"));
            }
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────
    // INIZIALIZZAZIONE DB
    // ─────────────────────────────────────────

    // Crea il database e le tre tabelle se non esistono già
    static void inizializzaDB() {
        // Si connette prima senza specificare il DB, poi lo crea e lo seleziona
        String baseUrl = "jdbc:mysql://" + HOST + ":" + PORT;
        try (Connection conn = DriverManager.getConnection(baseUrl, USER, PSW);
                Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB);
            stmt.executeUpdate("USE " + DB);

            // Tabella Classe: contiene le classi scolastiche
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Classe (" +
                            "  id_classe INT PRIMARY KEY AUTO_INCREMENT," +
                            "  nome VARCHAR(50) NOT NULL," +
                            "  anno_scolastico VARCHAR(9) NOT NULL," +
                            "  sezione CHAR(1) NOT NULL," +
                            "  capienza INT NOT NULL" +
                            ")");

            // Tabella Studente: ogni studente è associato a una classe (chiave esterna)
            // ON DELETE RESTRICT impedisce di eliminare una classe che ha studenti
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Studente (" +
                            "  id_studente INT PRIMARY KEY AUTO_INCREMENT," +
                            "  nome VARCHAR(50) NOT NULL," +
                            "  cognome VARCHAR(50) NOT NULL," +
                            "  data_di_nascita DATE NOT NULL," +
                            "  codice_fiscale CHAR(16) NOT NULL UNIQUE," +
                            "  id_classe INT NOT NULL," +
                            "  FOREIGN KEY (id_classe) REFERENCES Classe(id_classe)" +
                            "  ON DELETE RESTRICT ON UPDATE CASCADE" +
                            ")");

            // Tabella Voto: ogni voto è associato a uno studente (chiave esterna)
            // ON DELETE CASCADE elimina automaticamente i voti se lo studente viene
            // eliminato
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Voto (" +
                            "  id_voto INT PRIMARY KEY AUTO_INCREMENT," +
                            "  materia VARCHAR(100) NOT NULL," +
                            "  data DATE NOT NULL," +
                            "  valore FLOAT NOT NULL," +
                            "  id_studente INT NOT NULL," +
                            "  FOREIGN KEY (id_studente) REFERENCES Studente(id_studente)" +
                            "  ON DELETE CASCADE ON UPDATE CASCADE" +
                            ")");

            System.out.println("Database inizializzato correttamente.");
        } catch (SQLException e) {
            System.err.println("Errore inizializzazione DB: " + e.getMessage());
        }
    }
}