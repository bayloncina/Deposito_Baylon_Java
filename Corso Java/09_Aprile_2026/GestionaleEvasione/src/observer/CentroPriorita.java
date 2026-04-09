package observer;

import java.util.ArrayList;
import java.util.List;

//subject
public class CentroPriorita {

    private String stato;
    private List<OrdineObserver> observerList = new ArrayList<>();


    public String getStato() {
    return stato;
}
    public void aggiungiObserver(OrdineObserver o) {
        observerList.add(o);
    }

    // rimuove un observer
    public void rimuoviObserver(OrdineObserver o) {
        observerList.remove(o);
    }

    // notifica tutti gli observer con il nuovo stato
    private void notificaObservers(String stato) {
        for (OrdineObserver o : observerList) {
            o.statoOrdine(stato);
        }
    }

    // cambia lo stato dell'ordine e notifica gli observer
    public void cambiaStato(String stato) {
        this.stato = stato;
        System.out.println("Stato centro aggiornato: " + stato);
        notificaObservers(stato);
    }

}
