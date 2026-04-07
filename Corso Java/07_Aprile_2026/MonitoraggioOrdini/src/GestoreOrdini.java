import java.util.ArrayList;
import java.util.List;

//singleton
//observeble  mantiene la lista degli osservatori 
// e li notifica ogni volta che un ordine viene inserito o il suo stato cambia
public class GestoreOrdini {

    private static GestoreOrdini istanza;
    private List<OsservatoreOrdine> osservatori = new ArrayList<>();
    private DataBaseManagerOrdini db;

    private GestoreOrdini() {
        db = DataBaseManagerOrdini.getIstanzaDb();
        db.connect();
    }

    public static GestoreOrdini getIstanza() {
        if (istanza == null) {
            istanza = new GestoreOrdini();
        }
        return istanza;
    }

    // ---- gestione osservatori ----

    public void registraOsservatore(OsservatoreOrdine o) {
        osservatori.add(o);
    }

    public void rimuoviOsservatore(OsservatoreOrdine o) {
        osservatori.remove(o);
    }

    private void notificaOsservatori(Ordine ordine) {
        for (OsservatoreOrdine o : osservatori) {
            o.aggiorna(ordine);
        }
    }

    // ---- operazioni sugli ordini ----

    public void inserisciOrdine(Ordine ordine) {
        db.salvaOrdine(ordine.getCliente(), ordine.getProdotto(),
                ordine.getQuantita(), ordine.getStato());
        notificaOsservatori(ordine);
    }

    public void aggiornaStato(int id, StatoOrdine nuovoStato) {
    db.aggiornaStato(id, nuovoStato.name());
    Ordine ordine = db.getOrdineById(id); // recupera l'ordine completo
    if (ordine != null) {
        notificaOsservatori(ordine);
    }
}

    public void visualizzaTuttiOrdini() {
        db.visualizzaTuttiOrdini();
    }

    public void cercaOrdine(int id) {
        db.cercaOrdine(id);
    }
}