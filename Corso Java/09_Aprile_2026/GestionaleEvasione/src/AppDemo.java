import decorator.NotificaDecorator;
import observer.CentroPriorita;
import observer.GestoreOrdine;
import strategy.EvasioneControllata;
import strategy.EvasioneNormale;
import strategy.EvasionePrioritaria;
import strategy.Ordine;

public class AppDemo {
    public static void main(String[] args) {

        // creazione di tre ordini diversi
        Ordine ordine1 = new Ordine("Miriam", "Laptop", new EvasioneNormale(), 1000.0);
        Ordine ordine2 = new Ordine("Francesca", "Smartphone", new EvasioneControllata(), 500.0);
        Ordine ordine3 = new Ordine("Carlo", "Tablet", new EvasionePrioritaria(), 750.0);

        // creazione subject
        CentroPriorita centro = new CentroPriorita();

        // ogni ordine ha il suo observer collegato
        GestoreOrdine gestore1 = new GestoreOrdine(ordine1);
        GestoreOrdine gestore2 = new GestoreOrdine(ordine2);
        GestoreOrdine gestore3 = new GestoreOrdine(ordine3);

        // registrazione di tutti gli observer nel subject
        centro.aggiungiObserver(gestore1);
        centro.aggiungiObserver(gestore2);
        centro.aggiungiObserver(gestore3);

        // stato NORMALE tutti aggiornano a EvasioneNormale
        System.out.println("\n===== Stato: NORMALE =====");
        centro.cambiaStato("NORMALE");
        System.out.println(ordine1);
        ordine1.eseguiEvasione();
        System.out.println(ordine2);
        ordine2.eseguiEvasione();
        System.out.println(ordine3);
        ordine3.eseguiEvasione();

        // stato PRIORITA tutti aggiornano a EvasionePrioritaria
        System.out.println("\n===== Stato: PRIORITA =====");
        centro.cambiaStato("PRIORITA");
        System.out.println(ordine1);
        ordine1.eseguiEvasione();
        System.out.println(ordine2);
        ordine2.eseguiEvasione();
        System.out.println(ordine3);
        ordine3.eseguiEvasione();

        // stato CONTROLLO tutti aggiornano a EvasioneControllata
        System.out.println("\n===== Stato: CONTROLLO =====");
        centro.cambiaStato("CONTROLLO");
        System.out.println(ordine1);
        ordine1.eseguiEvasione();
        System.out.println(ordine2);
        ordine2.eseguiEvasione();
        System.out.println(ordine3);
        ordine3.eseguiEvasione();

        // decorator applicato solo su ordine1
        System.out.println("\n===== Stato: PRIORITA con NotificaDecorator su ordine1 =====");
        centro.cambiaStato("PRIORITA");
        ordine1.setStrategia(new NotificaDecorator(ordine1.getStrategia()));
        System.out.println(ordine1);
        ordine1.eseguiEvasione();
        System.out.println(ordine2);
        ordine2.eseguiEvasione();
        System.out.println(ordine3);
        ordine3.eseguiEvasione();

        // applica il decorator solo sull'ordine1 con stato PRIORITA
        System.out.println("\n===== Stato: PRIORITA con NotificaDecorator su ordine1 =====");
        centro.cambiaStato("PRIORITA");
        ordine1.setStrategia(new NotificaDecorator(ordine1.getStrategia()));
        ordine1.eseguiEvasione();
        ordine2.eseguiEvasione();
        ordine3.eseguiEvasione();
    }
}