import java.util.Scanner;

class Veicolo {
    // attributi del veicolo
    String marca;
    String modello;
    int anno;
    double prezzo;

    // costruttore completo
    Veicolo(String marca, String modello, int anno, double prezzo) {
        this.marca = marca;
        this.modello = modello;
        this.anno = anno;
        this.prezzo = prezzo;
    }

    // costruttore vuoto
    Veicolo() {
    }

    // stampa tutti gli attributi del veicolo
    public void stampaVeicolo() {
        System.out.println("Marca: " + marca + " Modello: " + modello + " Anno: " + anno + " Prezzo: euro" + prezzo);
    }
}

public class EsercizioClassi3 {
    public static void main(String[] args) {

        //TO DO chiedere a Mirko se gli scanner è meglio istanziarli nel metodo mostraMenu
        Scanner scObjInt = new Scanner(System.in);
        Scanner scObjStr = new Scanner(System.in);

        // passo veicolo vuoto
        Veicolo veicolo = new Veicolo();

        mostraMenu(scObjInt, scObjStr, veicolo);

        scObjInt.close();
        scObjStr.close();
    }

    //ritorna veicolo 
    private static Veicolo mostraMenu(Scanner scObjInt, Scanner scObjStr, Veicolo veicolo) {
        boolean continua = true;
        do {
            System.out.println("MENU");
            System.out.println("1. Inserisci veicolo");
            System.out.println("2. Visualizza veicolo");
            System.out.println("3. Modifica prezzo");
            System.out.println("4. Verifica età veicolo");
            System.out.println("5. Esci");
            System.out.print("Scelta: ");
            int scelta = scObjInt.nextInt();

            switch (scelta) {
                case 1:
                    // inserisci veicolo
                    // attribuisco a veicolo l'oggetto veicolo che ritorna dal metodo
                    //passando i parametri che servono
                    veicolo = inserisciVeicolo(scObjInt, scObjStr, veicolo);
                    break;
                case 2:
                    //visualizza Veicolo
                    visualizzaVeicolo(veicolo);
                    break;
                case 3:
                    //modifica prezzo veicolo
                    modificaPrezzo(scObjInt, veicolo);
                    break;
                case 4:
                    //verifica eta veicolo
                    verificaEtaVeicolo(veicolo);
                    break;
                case 5:
                    System.out.println("Arrivederci!");
                    continua = false;
                    break;
                default:
                    System.out.println("Scelta non valida!");
                    break;
            }
        } while (continua);
        return veicolo;
    }

//ritorna veicolo
    private static Veicolo inserisciVeicolo(Scanner scObjInt, Scanner scObjStr, Veicolo veicolo) {

            System.out.println("Inserisci la marca: ");
            String marca = scObjStr.nextLine();
            System.out.println("Inserisci il modello: ");
            String modello = scObjStr.nextLine();

            // anno deve essere superiore al 1900
            System.out.println("Inserisci l'anno: ");
            int anno = scObjInt.nextInt();
            while (anno <= 1900) {
                System.out.println("Anno non valido! Inserisci un anno superiore al 1900: ");
                anno = scObjInt.nextInt();
            }

            // prezzo deve essere maggiore di 0
            System.out.println("Inserisci il prezzo: ");
            double prezzo = scObjInt.nextDouble();
            while (prezzo <= 0) {
                System.out.println("Prezzo non valido! Inserisci un prezzo maggiore di 0: ");
                prezzo = scObjInt.nextDouble();
            }

            // crea il veicolo direttamente nel case
            veicolo = new Veicolo(marca, modello, anno, prezzo);
            veicolo.stampaVeicolo();
            System.out.println("Veicolo inserito!");
        return veicolo;
    }

    private static void visualizzaVeicolo(Veicolo veicolo) {
        if (veicolo == null) {
            System.out.println("Nessun veicolo inserito!");
        } else {
            veicolo.stampaVeicolo();
        }
    }

    private static void modificaPrezzo(Scanner scObjInt, Veicolo veicolo) {
        if (veicolo == null) {
            System.out.println("Nessun veicolo inserito!");
        } else {
            System.out.println("Inserisci il nuovo prezzo: ");
            double nuovoPrezzo = scObjInt.nextDouble();
            if (nuovoPrezzo > 0) {
                veicolo.prezzo = nuovoPrezzo;
                System.out.println("Prezzo aggiornato!");
            } else {
                System.out.println("Prezzo non valido!");
            }
        }
    }

    private static void verificaEtaVeicolo(Veicolo veicolo) {
        if (veicolo == null) {
            System.out.println("Nessun veicolo inserito!");
        } else {
            int eta = 2026 - veicolo.anno;
            if (eta < 5) {
                System.out.println("Nuovo (" + eta + " anni)");
            } else if (eta <= 15) {
                System.out.println("Usato (" + eta + " anni)");
            } else {
                System.out.println("Vecchio (" + eta + " anni)");
            }
        }
    }

}
