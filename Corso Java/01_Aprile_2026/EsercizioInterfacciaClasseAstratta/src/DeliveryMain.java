import java.util.Scanner;

public class DeliveryMain {
    static Scanner scannerInt = new Scanner(System.in);
    static ConsegnaManager manager = new ConsegnaManager();

    public static void main(String[] args) throws Exception {

        boolean running = true;
        while (running) {
            System.out.println("\n=== SERVIZIO CONSEGNE ===");
            System.out.println("1. Aggiungi veicolo");
            System.out.println("2. Esegui consegna");
            System.out.println("3. Visualizza veicoli");
            System.out.println("4. Esci");
            System.out.print("Scelta: ");
            int scelta = scannerInt.nextInt();
            scannerInt.nextLine();

            switch (scelta) {
                case 1 -> manager.aggiungiVeicolo();
                case 2 -> manager.eseguiConsegna();
                case 3 -> manager.mostraVeicoli();
                case 4 -> running = false;
                default -> System.out.println("Scelta non valida.");
            }
        }
        System.out.println("Arrivederci!");
        scannerInt.close();
    }
    }

