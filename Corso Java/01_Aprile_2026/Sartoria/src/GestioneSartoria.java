import java.util.Scanner;

public class GestioneSartoria {
    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerStr = new Scanner(System.in);
    //creo sartoria
    static Sartoria sartoria = new Sartoria();

    public static void main(String[] args) {
        
        boolean running = true;
        while (running) {
            System.out.println("\n=== SARTORIA DI MIRIAM ===");
            System.out.println("--- CAPI ---");
            System.out.println("1. Aggiungi capo");
            System.out.println("2. Modifica capo");
            System.out.println("3. Rimuovi capo");
            System.out.println("4. Visualizza capi");
            System.out.println("--- FINITURE ---");
            System.out.println("5. Aggiungi finitura");
            System.out.println("6. Modifica finitura");
            System.out.println("7. Rimuovi finitura");
            System.out.println("8. Visualizza finiture");
            System.out.println("9. Esci");
            System.out.print("Scelta: ");
            int scelta = scannerInt.nextInt();
            scannerInt.nextLine();

            switch (scelta) {
                case 1 -> sartoria.aggiungiCapo();
                case 2 -> sartoria.modificaCapo();
                case 3 -> sartoria.rimuoviCapo();
                case 4 -> sartoria.visualizzaCapi();
                case 5 -> sartoria.aggiungiFinitura();
                case 6 -> sartoria.modificaFinitura();
                case 7 -> sartoria.rimuoviFinitura();
                case 8 -> sartoria.visualizzaFiniture();
                case 9 -> running = false;
                default -> System.out.println("Scelta non valida.");
            }
        }
        System.out.println("Arrivederci!");
        scannerInt.close();
    }

    
}