import java.util.ArrayList;
import java.util.Scanner;

public class GestioneProduzione {
    public static void main(String[] args) {
        // inizailizzo due scanner per i diversi tipi di input
        Scanner scNum = new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);

        // dichiaro i due array per i diversi tipi di dati
        ArrayList<String> nomi = new ArrayList<>();
        ArrayList<Integer> quantita = new ArrayList<>();

        // chiamo il metodo per startare il programma
        // passo i paramentri necessari all'esecuzione del metodo
        mostraMenu(scNum, scStr, nomi, quantita);

        // chiudo gli scanner
        scNum.close();
        scStr.close();
    }

    // ------------- Metodi --------------

    static void mostraMenu(Scanner scNum, Scanner scStr, ArrayList<String> nomi,
            ArrayList<Integer> quantita) {
        boolean continua = true;

        do {
            System.out.println("MENU");
            System.out.println("1. Aggiungi prodotto");
            System.out.println("2. Visualizza produzione");
            System.out.println("3. Cerca prodotto");
            System.out.println("4. Modifica quantità");
            System.out.println("5. Calcola totale pezzi prodotti");
            System.out.println("6. Esci");
            System.out.print("Scelta: ");
            int scelta = scNum.nextInt();

            switch (scelta) {
                case 1:
                    // aggiungi prodotto
                    aggiungiprodotto(scNum, scStr, nomi, quantita);
                    break;
                case 2:
                    // visualizza prodotto
                    visualizzaProdotto(scStr, nomi, quantita);
                    break;
                case 3:
                    // cerca prodotto
                    cercaProdotto(scStr, nomi, quantita);
                    break;
                case 4:
                    // modifica quantità
                    modificaQuantita(scNum, scStr, nomi, quantita);
                    break;
                case 5:
                    // calcola totale pezzi
                    calcolaTotalePezzi(quantita);
                    break;
                case 6:
                    System.out.println("Arrivederci!");
                    continua = false;
                    break;
                default:
                    System.out.println("Scelta non valida!");
                    break;
            }
        } while (continua);
    }

    static void aggiungiprodotto(Scanner scNum, Scanner scStr, ArrayList<String> nomi, ArrayList<Integer> quantita) {
        System.out.println("Inserisci nome prodotto: ");
        String nome = scStr.nextLine();
        //aggiungo prodotto
        nomi.add(nome);

        System.out.println("Inserisci quantità: ");
        int qty = scNum.nextInt();
        //aggiungo quantita
        quantita.add(qty);

        System.out.println("Prodotto aggiunto!");
    }

    static void visualizzaProdotto(Scanner scStr, ArrayList<String> nomi, ArrayList<Integer> quantita) {
        // se la lista è vuota
        if (nomi.isEmpty()) {
            System.out.println("Nessun prodotto registrato");
        } else {
            System.out.println("Prodotti:");

        // scorre tutti i prodotti e stampa nome e quantità usando lo stesso indice
            for (int i = 0; i < nomi.size(); i++) {
                System.out.println((i + 1) + " - " + nomi.get(i) + " | Quantità: " + quantita.get(i));
            }
        }
    }

    static void cercaProdotto(Scanner scStr, ArrayList<String> nomi, ArrayList<Integer> quantita) {
        System.out.println("Inserisci il nome del prodotto da cercare: ");
        String nomeC = scStr.nextLine();
        if (nomi.contains(nomeC)) {
            // indexOf trova l'indice con quel valore
            int indice = nomi.indexOf(nomeC);
            System.out.println("Prodotto: " + nomi.get(indice) + " | Quantità: " + quantita.get(indice));
        } else {
            System.out.println("Prodotto non trovato!");
        }
    }

    static void modificaQuantita(Scanner scNum, Scanner scStr, ArrayList<String> nomi, ArrayList<Integer> quantita) {
        System.out.println("Inserisci il nome del prodotto da modificare: ");
        String nomeC = scStr.nextLine();
        if (nomi.contains(nomeC)) {
            // indexOf trova l'indice con quel valore
            int indice = nomi.indexOf(nomeC);
            System.out.println("Nuova quantità: ");
            int nuovaQty = scNum.nextInt();
            quantita.set(indice, nuovaQty);
            System.out.println("Quantità aggiornata!");
        } else {
            System.out.println("Prodotto non trovato!");
        }

    }

    static void calcolaTotalePezzi(ArrayList<Integer> quantita) {
        // istanzio la variabile a 0
        int totale = 0;
        for (int i = 0; i < quantita.size(); i++) {
            totale += quantita.get(i);
        }
        System.out.println("Totale pezzi prodotti: " + totale);
    }

}