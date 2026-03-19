import java.util.Scanner;

public class EsercizioCalcolatrice {

    public static void main(String[] args) {

        /* EXTRA: aggiugnere la potenza e le operazioni per + di 2 addendi */

        Scanner scObjInt = new Scanner(System.in);

        boolean continua = true;
        do {
            System.out.println("MENU - Scegli l'operazione da effettuare: ");
            System.out.println("1. Somma");
            System.out.println("2. Sottrazione");
            System.out.println("3. Moltiplicazione");
            System.out.println("4. Divisione");
            System.out.println("4. Divisione");
            System.out.println("5. Esci");
            System.out.print("Scelta: ");
            int scelta = scObjInt.nextInt();

            switch (scelta) {
                case 1:
                    // inserisciNumeri gestisce quanti numeri vuole l'utente
                    // somma accetta un array quindi può sommare qualsiasi quantità di numeri
                    System.out.println("Risultato: " + addizione(inserisciNumeri(scObjInt)));
                    break;
                case 2:
                    int[] n = inserisciNumeri(scObjInt);
                    System.out.println("Risultato: " + sottrazione(n[0], n[1]));
                    break;

                case 3:
                    System.out.println("Risultato: " + moltiplicazione(inserisciNumeri(scObjInt)));
                    break;
                case 4:
                    int[] n2 = inserisciNumeri(scObjInt);
                    // controllo divisione per zero prima di chiamare il metodo
                    if (n2[1] == 0) {
                        System.out.println("Errore: divisione per 0!");
                    } else {
                        System.out.println("Risultato: " + divisione(n2[0], n2[1]));
                    }
                    break;
                case 5:
                    System.out.println("Arrivederci");
                    continua = false;
                    break;
                default:
                    System.out.println("Scelta non valida");
                    break;
            }
        } while (continua);

        scObjInt.close();
    }

    // chiede quanti numeri vuole inserire, li raccoglie in un array e lo
    // restituisce
    static int[] inserisciNumeri(Scanner sc) {
        System.out.println("Quanti numeri vuoi inserire? ");
        int quantita = sc.nextInt();

        // crea array della dimensione scelta dall'utente
        int[] numeri = new int[quantita];

        // riempie l'array con i numeri inseriti uno alla volta
        for (int i = 0; i < quantita; i++) {
            System.out.println("Inserisci numero " + (i + 1) + ": ");
            numeri[i] = sc.nextInt();
        }
        return numeri;
    }

    // metodo che accetta un array di interi e restituisce la somma di tutti gli
    // elementi
    static int addizione(int[] numeri) {
        // variabile accumulatore inizializzata a 0
        int somma = 0;
        // scorre tutti gli elementi dell'array dall'indice 0 fino all'ultimo
        for (int i = 0; i < numeri.length; i++) {
            somma += numeri[i]; // aggiunge ogni elemento al risultato accumulato
        }
        return somma;
    }

    static int sottrazione(int numero, int numero2) {
        return numero - numero2;
    }

    static int moltiplicazione(int[] numeri) {
        int molti = 1; // 0*0 = 0 
        // scorre tutti gli elementi dell'array dall'indice 0 fino all'ultimo
        for (int i = 0; i < numeri.length; i++) {
            molti *= numeri[i]; // moltiplica ogni elemento al risultato accumulato
        }
        return molti;
    }

    static double divisione(int numero, int numero2) {
        return (double) numero / numero2;
    }

    static double potenza(int base, int esponente) {
        // Math.pow è il metodo di Java per calcolare la potenza
        return Math.pow(base, esponente);
    }
}
