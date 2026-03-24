import java.util.Scanner;

/**
 * Rappresenta un prodotto con nome, prezzo e quantità disponibile.
 */
class Prodotto {
    String nome;
    double prezzo;
    int quantita;

    // Costruttore vuoto (necessario per creare un prodotto senza dati iniziali)
    Prodotto() {
    }

    // Costruttore parametrizzato: inizializza il prodotto con tutti i dati
    Prodotto(String nome, double prezzo, int quantita) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    // Stampa a schermo le informazioni del prodotto
    void mostraProdotto() {
        System.out.println("  Prodotto: " + nome + " | Prezzo: euro " + prezzo + " | Quantità: " + quantita);
    }
}

/**
 * Rappresenta un negozio con un nome e un singolo prodotto associato.
 */
class Negozio {
    String nomeNegozio;
    Prodotto prodotto; // ogni negozio gestisce un solo prodotto

    // Costruttore vuoto
    Negozio() {
    }

    // Costruttore parametrizzato: crea il negozio con nome e prodotto vuoto
    Negozio(String nomeNegozio) {
        this.nomeNegozio = nomeNegozio;
        this.prodotto = new Prodotto(); // prodotto inizialmente senza dati
    }

    // Stampa a schermo il nome del negozio e i dati del prodotto associato
    void mostraNegozio() {
        System.out.println("Negozio: " + nomeNegozio);
        // se non c'è nessun prodotto associato stampa Nessun prodotto associato
        if (prodotto != null && prodotto.nome != null) {
            prodotto.mostraProdotto();
        } else {
            System.out.println("  Nessun prodotto associato.");
        }
    }
}

public class GestioneCommerciale {

    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerStr = new Scanner(System.in);

    public static void main(String[] args) {

        // Scanner per la lettura string e interi

        // Creazione dei tre negozi (situazione iniziale)
        Negozio negozio1 = new Negozio("Brigitte Abbigliamento");
        Negozio negozio2 = new Negozio("Munique Store");
        Negozio negozio3 = new Negozio("Happy Studio");

        // Assegnazione di un prodotto vuoto a ciascun negozio
        negozio1.prodotto = new Prodotto();
        negozio2.prodotto = new Prodotto();
        negozio3.prodotto = new Prodotto();

        // Avvio del menu interattivo
        visualizzaMenu(negozio1, negozio2, negozio3);
    }

    /**
     * Mostra il menu principale in loop finché l'utente non sceglie di uscire.
     */
    private static void visualizzaMenu(Negozio negozio1, Negozio negozio2, Negozio negozio3) {
        int scelta = 0;

        do {
            // Stampa le opzioni disponibili
            System.out.println("\nMENU GESTIONE NEGOZIO");
            System.out.println("1 - Inserisci prodotto in un negozio");
            System.out.println("2 - Visualizza dati di tutti i negozi");
            System.out.println("3 - Cerca il negozio con il prodotto più costoso");
            System.out.println("4 - Modifica quantità di un prodotto");
            System.out.println("5 - Vendi prodotti da un negozio");
            System.out.println("6 - Esci");
            System.out.print("Scelta: ");

            scelta = scannerInt.nextInt();

            // Esegue l'operazione corrispondente alla scelta dell'utente
            // passando i parametri necessari per eseguire il metodo
            switch (scelta) {
                case 1:
                    inserisciProdotto(negozio1, negozio2, negozio3);
                    break;
                case 2:
                    visualizzaTuttiNegozi(negozio1, negozio2, negozio3);
                    break;
                case 3:
                    cercaPiuCostoso(negozio1, negozio2, negozio3);
                    break;
                case 4:
                    modificaQuantita(negozio1, negozio2, negozio3);
                    break;
                case 5:
                    vendiProdotto(negozio1, negozio2, negozio3);
                    break;
                case 6:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }

        } while (scelta != 6); // il loop continua finché l'utente non sceglie 6
    }

    /**
     * Permette di inserire un nuovo prodotto nel negozio scelto.
     */
    private static void inserisciProdotto(Negozio n1, Negozio n2, Negozio n3) {
        // Chiede all'utente quale negozio modificare
        Negozio negozio = scegliNegozio(n1, n2, n3);
        // Lettura dei dati del nuovo prodotto
        System.out.print("Nome prodotto: ");
        String nome = scannerStr.nextLine();
        System.out.print("Prezzo: ");
        double prezzo = scannerInt.nextDouble();
        System.out.print("Quantità: ");
        int quantita = scannerInt.nextInt();

        // Creazione e assegnazione del nuovo prodotto al negozio
        negozio.prodotto = new Prodotto(nome, prezzo, quantita);
        System.out.println("Prodotto inserito con successo in " + negozio.nomeNegozio);
    }

    /**
     * Stampa le informazioni di tutti e tre i negozi.
     */
    private static void visualizzaTuttiNegozi(Negozio n1, Negozio n2, Negozio n3) {
        System.out.println("I negozi presenti: ");
        n1.mostraNegozio();
        n2.mostraNegozio();
        n3.mostraNegozio();
    }

    /**
     * Confronta i prezzi dei prodotti e stampa il negozio
     * che ha il prodotto con il prezzo più alto.
     */
    private static void cercaPiuCostoso(Negozio n1, Negozio n2, Negozio n3) {
        // Si parte assumendo che il primo negozio abbia il prodotto più costoso
        Negozio piuCostoso = n1;

        // Si confronta con gli altri due e si aggiorna se si trova un prezzo maggiore
        if (n2.prodotto.prezzo > piuCostoso.prodotto.prezzo) {
            piuCostoso = n2;
        }
        if (n3.prodotto.prezzo > piuCostoso.prodotto.prezzo) {
            piuCostoso = n3;
        }

        System.out.println("Il negozio con il prodotto più costoso è: " + piuCostoso.nomeNegozio);
        piuCostoso.prodotto.mostraProdotto();
    }

    /**
     * Permette di aggiornare la quantità disponibile
     * del prodotto in un negozio scelto dall'utente.
     */
    private static void modificaQuantita(Negozio n1, Negozio n2, Negozio n3) {
        Negozio negozio = scegliNegozio(n1, n2, n3);
        if (negozio == null)
            return;

        // Mostra il prodotto attuale e chiede la nuova quantità
        System.out.print("Nuova quantità per '" + negozio.prodotto.nome + "': ");
        int nuovaQuantita = scannerInt.nextInt();

        // Aggiornamento del campo quantita
        negozio.prodotto.quantita = nuovaQuantita;
        System.out.println("Quantità aggiornata.");
    }

    /**
     * Gestisce la vendita di una certa quantità del prodotto
     * di un negozio scelto dall'utente.
     */
    private static void vendiProdotto(Negozio n1, Negozio n2, Negozio n3) {
        Negozio negozio = scegliNegozio(n1, n2, n3);
        if (negozio == null)
            return;

        // Mostra le informazioni attuali prima di procedere
        System.out.println("Prodotto: " + negozio.prodotto.nome
                + " (quantità disponibile: " + negozio.prodotto.quantita + ")");
        System.out.print("Quante unità vuoi vendere? ");
        int qta = scannerInt.nextInt();

        if (qta <= 0) {
            // Quantità non valida (zero o negativa)
            System.out.println("Quantità non valida.");
        } else if (qta > negozio.prodotto.quantita) {
            // Non ci sono abbastanza unità in magazzino
            System.out.println("Quantità insufficiente in magazzino.");
        } else {
            // Si sottrae la quantità venduta (qta) dalla quantità attualmente disponibile
            // in magazzino
            // e si salva il risultato nel campo quantita del prodotto del negozio
            // selezionato
            negozio.prodotto.quantita = negozio.prodotto.quantita - qta;

            // Si stampa un messaggio con la quantità rimasta in magazzino dopo la vendita
            System.out.printf("Vendita effettuata. Quantità rimanente: " + negozio.prodotto.quantita + " di "
                    + negozio.prodotto.nome);
        }
    }

    /**
     * Metodo di utilità - Chiede all'utente di scegliere uno dei tre negozi
     * e restituisce l'oggetto Negozio corrispondente.
     * Restituisce null se la scelta non è valida.
     */
    private static Negozio scegliNegozio(Negozio n1, Negozio n2, Negozio n3) {
        System.out.println("SELEZIONA NEGOZIO");
        System.out.println("Digita 1 per " + n1.nomeNegozio);
        System.out.println("Digita 2 per " + n2.nomeNegozio);
        System.out.println("Digita 3 per " + n3.nomeNegozio);
        System.out.print("Scelta: ");
        int scelta = scannerInt.nextInt();

        // In base alla scelta dell'utente, ritorna il negozio corrispondente
        switch (scelta) {
            case 1:
                return n1;
            case 2:
                return n2;
            case 3:
                return n3;
            default:
                // Se il numero inserito non è 1, 2 o 3, la scelta non è valida
                System.out.println("Scelta non valida.");
                return null;
        }
    }
}