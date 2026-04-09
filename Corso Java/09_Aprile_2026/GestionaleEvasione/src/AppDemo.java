import decorator.NotificaDecorator;
import observer.CentroPriorita;
import observer.GestoreOrdine;
import strategy.EvasioneControllata;
import strategy.EvasioneNormale;
import strategy.EvasionePrioritaria;
import strategy.Ordine;

public class AppDemo {
    public static void main(String[] args) {

        // --- CREAZIONE ORDINI ---
        // ogni ordine parte con una strategia diversa, ma verrà sovrascritta
        // dal GestoreOrdine non appena il centro cambia stato
        Ordine ordine1 = new Ordine("Miriam",    "Laptop",     new EvasioneNormale(),     1000.0);
        Ordine ordine2 = new Ordine("Francesca", "Smartphone", new EvasioneControllata(),  500.0);
        Ordine ordine3 = new Ordine("Carlo",     "Tablet",     new EvasionePrioritaria(),  750.0);

        // --- SETUP OBSERVER ---
        // CentroPriorita è il Subject: tiene la lista degli observer e notifica tutti
        CentroPriorita centro = new CentroPriorita();

        // ogni GestoreOrdine è un Observer collegato al proprio ordine
        // quando riceve la notifica, aggiorna la strategia dell'ordine
        GestoreOrdine gestore1 = new GestoreOrdine(ordine1);
        GestoreOrdine gestore2 = new GestoreOrdine(ordine2);
        GestoreOrdine gestore3 = new GestoreOrdine(ordine3);

        // registrazione: da questo momento il centro notifica tutti e tre
        centro.aggiungiObserver(gestore1);
        centro.aggiungiObserver(gestore2);
        centro.aggiungiObserver(gestore3);

        // --- STATO: NORMALE ---
        // il centro notifica tutti i gestori tutti gli ordini usano EvasioneNormale (+5%)
        System.out.println("\n===== Stato: NORMALE =====");
        centro.cambiaStato("NORMALE"); 
        System.out.println(ordine1); ordine1.eseguiEvasione(); 
        System.out.println(ordine2); ordine2.eseguiEvasione(); 
        System.out.println(ordine3); ordine3.eseguiEvasione();

        // --- STATO: PRIORITA ---
        // il centro notifica tutti i gestori tutti gli ordini usano EvasionePrioritaria (+15%)
        System.out.println("\n===== Stato: PRIORITA =====");
        centro.cambiaStato("PRIORITA"); 
        System.out.println(ordine1); ordine1.eseguiEvasione(); 
        System.out.println(ordine2); ordine2.eseguiEvasione(); 
        System.out.println(ordine3); ordine3.eseguiEvasione();

        // --- STATO: CONTROLLO ---
        // il centro notifica tutti i gestori tutti gli ordini usano EvasioneControllata (-5%)
        System.out.println("\n===== Stato: CONTROLLO =====");
        centro.cambiaStato("CONTROLLO");
        System.out.println(ordine1); ordine1.eseguiEvasione(); 
        System.out.println(ordine2); ordine2.eseguiEvasione(); 
        System.out.println(ordine3); ordine3.eseguiEvasione(); 

        // --- DECORATOR su ordine1 ---
        // il centro torna a PRIORITA tutti aggiornano a EvasionePrioritaria
        // solo ordine1 viene avvolto con NotificaDecorator:
        // esegue la strategia normalmente e in più stampa una notifica al cliente
        // ordine2 e ordine3 evadono senza decorator per mostrare la differenza
        System.out.println("\n===== Stato: PRIORITA con NotificaDecorator su ordine1 =====");
        centro.cambiaStato("PRIORITA");
        ordine1.setStrategia(new NotificaDecorator(ordine1.getStrategia()));
        System.out.println(ordine1); ordine1.eseguiEvasione(); 
        System.out.println(ordine2); ordine2.eseguiEvasione(); 
        System.out.println(ordine3); ordine3.eseguiEvasione(); 
    }
}