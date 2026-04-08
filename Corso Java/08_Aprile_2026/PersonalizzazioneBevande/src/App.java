import java.util.Scanner;

import bevande.*;
import db.*;
import decorator.*;
import observer.*;

public class App {
    public static void main(String[] args) {

        // apre la connessione al database MySQL una sola volta all'avvio
        DBConnection.getIstanzaDb().connect();

        Scanner sc = new Scanner(System.in);

        // ottiene l'unica istanza del gestore ordini (Singleton)
        GestoreOrdini gestore = GestoreOrdini.getInstance();

        // registra l'observer che stampa il riepilogo alla conferma dell'ordine
        gestore.aggiungiObserver(new StampaOrdine());

        boolean esci = false;
        while (!esci) {
            System.out.println("\n========= BAR MENU =========");
            System.out.println("1 - Crea nuova bevanda");
            System.out.println("2 - Aggiungi ingrediente extra");
            System.out.println("3 - Visualizza ordine corrente");
            System.out.println("4 - Conferma ordine");
            System.out.println("5 - Visualizza storico ordini");
            System.out.println("6 - Esci");
            System.out.print("Scelta: ");

            int scelta = sc.nextInt();

            switch (scelta) {
                case 1 -> {
                    // se c'era una bevanda temporanea in lavorazione, la sposta nel carrello
                    gestore.aggiungiBevandaAlCarrello();

                    // chiede all'utente quale bevanda base creare
                    System.out.println("Scegli bevanda: ");
                    System.out.println("1 - Caffè");
                    System.out.println("2 - Tè");
                    System.out.println("3 - Cioccolata");
                    int b = sc.nextInt();

                    // crea la bevanda base scelta (Decorator: componente base)
                    Bevanda bev = switch (b) {
                        case 2 -> new Te();
                        case 3 -> new Cioccolata();
                        default -> new Caffe();
                    };

                    // imposta la nuova bevanda come temporanea
                    gestore.setBevanda(bev);
                    System.out.println("Bevanda creata: " + bev.getDescrizione());
                }
                case 2 -> {
                    // controlla che esista una bevanda temporanea su cui lavorare
                    if (gestore.getBevanda() == null) {
                        System.out.println("Prima crea una bevanda (opzione 1).");
                        break;
                    }

                    // chiede quale ingrediente extra aggiungere
                    System.out.println("Scegli extra: ");
                    System.out.println("1 - Latte ");
                    System.out.println("2 - Zucchero ");
                    System.out.println("3 - Panna");
                    System.out.println("4 - Cannella");
                    int e = sc.nextInt();

                    // wrappa la bevanda temporanea nel decoratore scelto (Decorator pattern)
                    Bevanda decorata = switch (e) {
                        case 2 -> new ZuccheroDec(gestore.getBevanda());
                        case 3 -> new PannaDec(gestore.getBevanda());
                        case 4 -> new CannellaDec(gestore.getBevanda());
                        default -> new LatteDec(gestore.getBevanda());
                    };

                    // aggiorna la bevanda temporanea con quella decorata
                    gestore.setBevanda(decorata);
                    System.out.println("Aggiunto! Bevanda: " + decorata.getDescrizione());
                }

                // mostra tutte le bevande nel carrello con il totale
                case 3 -> gestore.visualizzaCorrente();

                // salva l'ordine su DB e notifica gli observer
                case 4 -> gestore.confermaOrdine();

                // legge e stampa lo storico ordini dal DB
                case 5 -> gestore.visualizzaStorico();
                case 6 -> esci = true;

                default -> System.out.println("Scelta non valida.");
            }
        }

        sc.close();
        System.out.println("Arrivederci!");
    }
}