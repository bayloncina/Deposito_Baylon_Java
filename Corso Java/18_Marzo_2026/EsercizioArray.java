import java.util.Scanner;

public class EsercizioArray {
    public static void main(String[] args) {

        Scanner scObj = new Scanner(System.in);
        Scanner scObjInt = new Scanner(System.in);

        // chiedere quanti dolci vuoi ordinare max 10
        System.out.println("Quanti dolci vuoi ordinare? Non fare l'ingord* max 10 dolci!!!!");
        int numeroDolci = scObjInt.nextInt();

        // controllo che il numero inserito non sia maggiore di 10
        while (numeroDolci <= 0 || numeroDolci > 10) {
            System.out.println("Inserisci un numero tra 1 e 10");
            numeroDolci = scObjInt.nextInt();
        }

        // due array paralleli stessa dimensione
        String[] nomi = new String[numeroDolci];
        int[] quantita = new int[numeroDolci];

        for (int i = 0; i < numeroDolci; i++) {
            System.out.println("Insersci nome dolce: ");
            nomi[i] = scObj.nextLine();

            System.out.println("Inserisci la quantità: ");
            quantita[i] = scObj.nextInt();

            // valida che la quantità sia superiore a 0
            while (quantita[i] <= 0) {
                System.out.println("Errore! Inserisci una quantità superirore a 0");
                quantita[i] = scObjInt.nextInt();
            }
        }

        // stampa l'elenco degli ordini
        System.out.println("Hai ordinato : ");
        int totale = 0;
        for (int i = 0; i < numeroDolci; i++) {
            System.out.println(quantita[i] + " pezzi di " + nomi[i] );
            totale += quantita[i]; // somma le quantità
        }

        // stampa il totale
        System.out.println("Totale dolci ordinati: " + totale);

        scObj.close();
        scObjInt.close();

    }

}
