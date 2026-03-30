import java.sql.*;
import java.util.Scanner;

/* CREATE DATABASE IF NOT EXISTS alitalia;

USE alitalia;
CREATE TABLE aerei (
    codice VARCHAR(10) PRIMARY KEY, 
    modello VARCHAR(100),
    numeroPosti INT
);

CREATE TABLE piloti (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    numeroBrevetto VARCHAR(50),
    oreVolo INT
) */



// ===== CONNESSIONE AL DATABASE =====
class DataBaseConnection {

    private static final String URL      = "jdbc:mysql://localhost:3306/alitalia";
    private static final String USER     = "root";
    private static final String PASSWORD = "";

    // restituisce una nuova connessione al db ogni volta che viene chiamato
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

// ===== CLASSE AEREO =====
class Aereo {

    // contatore statico: condiviso tra tutti gli oggetti Aereo, genera codice univoco
    private static int contatore = 0;
    private String modello;
    private int numeroPosti;
    private String codice; // generato automaticamente, non modificabile

    Aereo(String modello, int numeroPosti) {
        this.modello = modello;
        this.numeroPosti = numeroPosti;
        // genera codice univoco tipo AZ001, AZ002...
        this.codice = "AZ" + "00"+ (++contatore);
    }

    // getter
    public String getModello()  { return modello; }
    public int getNumeroPosti() { return numeroPosti; }
    public String getCodice()   { return codice; }

    // setter
    public void setModello(String modello) { this.modello = modello; }

    // setter con controllo: numeroPosti deve essere positivo
    public void setNumeroPosti(int numeroPosti) {
        if (numeroPosti <= 0) {
            System.out.println("Numero posti non valido.");
            return; // esce senza modificare
        }
        this.numeroPosti = numeroPosti;
    }

    // INSERT: salva l'aereo corrente nel db
    public void salva() {
        String query = "INSERT INTO aerei (codice, modello, numeroPosti) VALUES (?, ?, ?)";
        try {
            Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.codice);     // primo ?
            stmt.setString(2, this.modello);    // secondo ?
            stmt.setInt(3, this.numeroPosti);   // terzo ?
            stmt.executeUpdate();               // esegue la INSERT
            System.out.println("Aereo salvato: " + this.codice);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Errore salva aereo: " + e.getMessage());
        }
    }

    // SELECT: metodo statico, mostra tutti gli aerei dal db
    public static void mostraTutti() {
        String query = "SELECT * FROM aerei";
        try {
            Connection conn = DataBaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query); // rs contiene tutte le righe
            System.out.println("\n-- FLOTTA --");
            boolean trovato = false;
            // scorro le righe una alla volta
            while (rs.next()) {
                trovato = true;
                System.out.println("Codice: " + rs.getString("codice") +
                        " | Modello: " + rs.getString("modello") +
                        " | Posti: "   + rs.getInt("numeroPosti"));
            }
            if (!trovato) System.out.println("Nessun aereo presente.");
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Errore mostraTutti aerei: " + e.getMessage());
        }
    }

    // rappresentazione testuale dell'oggetto
    public String toString() {
        return "Codice: " + codice + " | Modello: " + modello + " | Posti: " + numeroPosti;
    }
}

// ===== CLASSE PILOTA =====
class Pilota {

    private String nome;
    private String numeroBrevetto;
    private int oreVolo;

    Pilota(String nome, String numeroBrevetto, int oreVolo) {
        this.nome = nome;
        this.numeroBrevetto = numeroBrevetto;
        this.oreVolo = oreVolo;
    }

    // getter
    public String getNome()           { return nome; }
    public String getNumeroBrevetto() { return numeroBrevetto; }
    public int getOreVolo()           { return oreVolo; }

    // setter
    public void setNome(String nome)                     { this.nome = nome; }
    public void setNumeroBrevetto(String numeroBrevetto) { this.numeroBrevetto = numeroBrevetto; }

    // setter con controllo: oreVolo deve essere maggiore di zero
    public void setOreVolo(int oreVolo) {
        if (oreVolo <= 0) {
            System.out.println("Ore volo non valide.");
            return; // esce senza modificare
        }
        this.oreVolo = oreVolo;
    }

    // INSERT: salva il pilota corrente nel db
    public void salva() {
        String query = "INSERT INTO piloti (nome, numeroBrevetto, oreVolo) VALUES (?, ?, ?)";
        try {
            Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.nome);           // primo ?
            stmt.setString(2, this.numeroBrevetto); // secondo ?
            stmt.setInt(3, this.oreVolo);           // terzo ?
            stmt.executeUpdate();                   // esegue la INSERT
            System.out.println("Pilota salvato: " + this.nome);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Errore salva pilota: " + e.getMessage());
        }
    }

    // SELECT: metodo statico, mostra tutti i piloti dal db
    public static void mostraTutti() {
        String query = "SELECT * FROM piloti";
        try {
            Connection conn = DataBaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query); // rs contiene tutte le righe
            System.out.println("\n-- PILOTI --");
            boolean trovato = false;
            // scorro le righe una alla volta
            while (rs.next()) {
                trovato = true;
                System.out.println("Nome: "     + rs.getString("nome") +
                        " | Brevetto: " + rs.getString("numeroBrevetto") +
                        " | Ore volo: " + rs.getInt("oreVolo"));
            }
            if (!trovato) System.out.println("Nessun pilota presente.");
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Errore mostraTutti piloti: " + e.getMessage());
        }
    }

    // rappresentazione testuale dell'oggetto
    public String toString() {
        return "Nome: " + nome + " | Brevetto: " + numeroBrevetto + " | Ore volo: " + oreVolo;
    }
}

// ===== CLASSE COMPAGNIA AEREA =====
// senza ArrayList: tutto delegato al db
class CompagniaAerea {

    private String nome;

    CompagniaAerea(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // salva l'aereo direttamente nel db tramite il metodo salva()
    public void aggiungiAereo(Aereo aereo) {
        aereo.salva();
    }

    // salva il pilota direttamente nel db tramite il metodo salva()
    public void aggiungiPilota(Pilota pilota) {
        pilota.salva();
    }

    // legge dal db e stampa tutte le informazioni della compagnia
    public void stampaInfo() {
        System.out.println("\nCOMPAGNIA: " + nome);
        Aereo.mostraTutti();   // SELECT sulla tabella aerei
        Pilota.mostraTutti();  // SELECT sulla tabella piloti
    }
}

// ===== MAIN =====
public class App {
    public static void main(String[] args) {

        // test connessione: se fallisce termina il programma
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

        CompagniaAerea compagnia = new CompagniaAerea("Alitalia");

        // dati di default inseriti nel db all'avvio
        compagnia.aggiungiAereo(new Aereo("Boeing 737",  180));
        compagnia.aggiungiAereo(new Aereo("Airbus A320", 150));
        compagnia.aggiungiPilota(new Pilota("Mario Rossi",   "BR001", 1200));
        compagnia.aggiungiPilota(new Pilota("Lucia Bianchi", "BR002",  800));

        mostraMenu(compagnia, scannerStr, scannerInt);
    }

    private static void mostraMenu(CompagniaAerea compagnia, Scanner scannerStr, Scanner scannerInt) {
        int scelta = 0;

        while (scelta != 4) {
            System.out.println("\nMENU");
            System.out.println("1. Mostra informazioni compagnia");
            System.out.println("2. Aggiungi aereo");
            System.out.println("3. Aggiungi pilota");
            System.out.println("4. Esci");
            System.out.print("Scegli un'operazione: ");

            scelta = scannerInt.nextInt();

            switch (scelta) {
                case 1:
                    compagnia.stampaInfo();
                    break;
                case 2:
                    aggiungiAereo(compagnia, scannerStr, scannerInt);
                    break;
                case 3:
                    aggiungiPilota(compagnia, scannerStr, scannerInt);
                    break;
                case 4:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }
    }

    // legge modello e posti e aggiunge l'aereo alla compagnia
    private static void aggiungiAereo(CompagniaAerea compagnia, Scanner scannerStr, Scanner scannerInt) {
        System.out.print("Inserisci il modello: ");
        String modello = scannerStr.nextLine();
        System.out.print("Inserisci il numero di posti: ");
        int posti = scannerInt.nextInt();
        compagnia.aggiungiAereo(new Aereo(modello, posti));
    }

    // legge nome, brevetto e ore volo e aggiunge il pilota alla compagnia
    private static void aggiungiPilota(CompagniaAerea compagnia, Scanner scannerStr, Scanner scannerInt) {
        System.out.print("Inserisci il nome: ");
        String nome = scannerStr.nextLine();
        System.out.print("Inserisci il numero di brevetto: ");
        String brevetto = scannerStr.nextLine();
        System.out.print("Inserisci le ore di volo: ");
        int ore = scannerInt.nextInt();
        compagnia.aggiungiPilota(new Pilota(nome, brevetto, ore));
    }
}