package fabbrica.produzione;

import java.util.Scanner;

import fabbrica.personale.OperaioDirigente;
import fabbrica.personale.OperaioSpeciale;

public class GestioneFabbrica {
    private Scanner scannerInt = new Scanner(System.in);
    private Scanner scannerStr = new Scanner(System.in);

    // unica macchina della fabbrica condivisa da tutti gli operai
    private Macchina macchina = new Macchina("Macchina Farmaceutica");

    // inizializzati a null verranno creati dopo la scelta del ruolo
    private OperaioSpeciale operaioSpeciale = null;
    private OperaioDirigente operaioDirigente = null;

    public void startFabbrica() {

        // chiede il ruolo prima di avviare il menu
        System.out.println("BENVENUTO IN FABBRICA");
        System.out.println("Che tipo di operaio sei?");
        System.out.println("1. Operaio Speciale");
        System.out.println("2. Operaio Dirigente");
        System.out.print("Scelta: ");
        int tipo = scannerInt.nextInt();
        scannerInt.nextLine();

        System.out.print("Inserisci il tuo nome: ");
        String nome = scannerStr.nextLine();

        // crea l'oggetto giusto in base al ruolo scelto
        if (tipo == 1) {
            operaioSpeciale = new OperaioSpeciale(nome);
            System.out.println("Benvenuto OperaioSpeciale " + nome + "!");
        } else if (tipo == 2) {
            operaioDirigente = new OperaioDirigente(nome);
            System.out.println("Benvenuto OperaioDirigente " + nome + "!");
        } else {
            System.out.println("Tipo non valido. Arrivederci!");
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\nFABBRICA FARMACEUTICA");
            // mostra opzioni diverse in base al ruolo
            // operaioSpeciale != null significa che l'utente è un OperaioSpeciale
            if (operaioSpeciale != null) {
                // menu per l'operaio speciale può accendere, produrre e spegnere
                System.out.println("1. Accendi la macchina");
                System.out.println("2. Produci farmaco");
                System.out.println("3. Spegni la macchina");
            } else {
                // menu per il dirigente può solo controllare lo stato
                System.out.println("1. Stampa stato macchina");
                System.out.println("2. Spegni la macchina");
            }
            System.out.println("0. Esci");
            System.out.print("Scelta: ");
            int scelta = scannerInt.nextInt();
            scannerInt.nextLine(); // pulizia buffer



            // esegue l'azione in base al ruolo e alla scelta
            if (operaioSpeciale != null) {
                switch (scelta) {
                    // lavora() eredita da Operaio accende la macchina
                    case 1 -> operaioSpeciale.lavora(macchina);
                    // eseguiMansione() chiede tipo prodotto e lo crea
                    case 2 -> operaioSpeciale.eseguiMansione(macchina);
                    // ferma() eredita da Operaio spegne la macchina
                    case 3 -> operaioSpeciale.ferma(macchina);
                    case 0 -> running = false;
                    default -> System.out.println("Scelta non valida.");
                }
            } else {
                switch (scelta) {
                    // eseguiMansione() del dirigente stampa lo stato
                    case 1 -> operaioDirigente.eseguiMansione(macchina);
                    // ferma() ereditato da Operaio anche il dirigente può spegnere la macchina
                    case 2 -> operaioDirigente.ferma(macchina);
                    case 0 -> running = false;
                    default -> System.out.println("Scelta non valida.");
                }
            }
        }

        System.out.println("Arrivederci!");
        scannerInt.close();
    }
}