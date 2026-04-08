import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestoreOrdini {

    // SINGLETON unica istanza della classe in tutto il programma
    private static GestoreOrdini istanza;

    // carrello lista delle bevande completate (pronte per la conferma)
    private List<Bevanda> carrello = new ArrayList<>();

    // bevandaTemporanea: bevanda su cui si sta lavorando (si possono ancora
    // aggiungere extra)
    private Bevanda bevandaTemporanea = null;

    // lista degli observer da notificare alla conferma dell'ordine
    private List<OrdineObserver> observerList = new ArrayList<>();

    // costruttore privato impedisce la creazione di istanze dall'esterno
    // (Singleton)
    private GestoreOrdini() {
    }

    // registra un nuovo observer nella lista
    public void aggiungiObserver(OrdineObserver o) {
        observerList.add(o);
    }

    // notifica tutti gli observer con descrizione e costo dell'ordine confermato
    private void notificaObservers(String descrizione, double costo) {
        for (OrdineObserver o : observerList) {
            o.onOrdineConfermato(descrizione, costo);
        }
    }

    // restituisce l'unica istanza — la crea solo la prima volta (Singleton)
    public static GestoreOrdini getInstance() {
        if (istanza == null) {
            istanza = new GestoreOrdini();
        }
        return istanza;
    }

    // imposta la bevanda temporanea (sovrascrive quella precedente se non ancora
    // salvata)
    public void setBevanda(Bevanda b) {
        bevandaTemporanea = b;
    }

    // restituisce la bevanda temporanea corrente (usata per aggiungere i
    // decoratori)
    public Bevanda getBevanda() {
        return bevandaTemporanea;
    }

    // sposta la bevanda temporanea nel carrello e la azzera
    // viene chiamata quando si crea una nuova bevanda o si conferma l'ordine
    public void aggiungiBevandaAlCarrello() {
        if (bevandaTemporanea != null) {
            carrello.add(bevandaTemporanea);
            bevandaTemporanea = null;
        }
    }

    // stampa tutte le bevande nel carrello con il totale
    public void visualizzaCorrente() {
        if (carrello.isEmpty()) {
            System.out.println("Carrello vuoto.");
            return;
        }
        System.out.println("\n--- CARRELLO ---");
        double totale = 0;
        for (Bevanda b : carrello) {
            System.out.printf("- %s: %.2f euro%n", b.getDescrizione(), b.getCosto());
            totale += b.getCosto();
        }
        System.out.printf("Totale: %.2f euro%n", totale);
        System.out.println("----------------");
    }

    // salva l'ordine su DB e notifica gli observer
    public void confermaOrdine() {
        // prima di confermare, sposta l'eventuale bevanda temporanea nel carrello
        aggiungiBevandaAlCarrello();

        if (carrello.isEmpty()) {
            System.out.println("Carrello vuoto.");
            return;
        }
        try {
            Connection conn = DBConnection.getIstanzaDb().getConnection();
            String sql = "INSERT INTO ordini (descrizione, costo) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            // costruisce la descrizione unendo tutte le bevande con " | "
            // e somma i costi
            double totale = 0;
            StringBuilder descrizione = new StringBuilder();
            for (Bevanda b : carrello) {
                totale += b.getCosto();
                descrizione.append(b.getDescrizione()).append(" | ");
            }

            ps.setString(1, descrizione.toString());
            ps.setDouble(2, totale);
            ps.executeUpdate();

            // notifica gli observer con il riepilogo dell'ordine
            notificaObservers(descrizione.toString(), totale);
            System.out.println("Ordine confermato e salvato!");

            // svuota il carrello per il prossimo ordine
            carrello.clear();
        } catch (SQLException e) {
            System.out.println("Errore DB: " + e.getMessage());
        }
    }

    // legge e stampa tutti gli ordini salvati nel DB, dal più vecchio al più
    // recente
    public void visualizzaStorico() {
        try {
            Connection conn = DBConnection.getIstanzaDb().getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ordini ORDER BY data_ordine");
            System.out.println("\n--- STORICO ORDINI ---");
            while (rs.next()) {
                System.out.printf("[%d] %s - %.2f euro (%s)%n",
                        rs.getInt("id"),
                        rs.getString("descrizione"),
                        rs.getDouble("costo"),
                        rs.getTimestamp("data_ordine"));
            }
            System.out.println("----------------------");
        } catch (SQLException e) {
            System.out.println("Errore DB: " + e.getMessage());
        }
    }
}