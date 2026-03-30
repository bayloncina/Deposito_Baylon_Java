import java.util.ArrayList;
import java.util.Scanner;

class Auto {

    String targa;
    String modello;

    Auto(String targa, String modello) {
        this.targa = targa;
        this.modello = modello;
    }

    public String toString() {
        return "Targa: " + targa + " Modello: " + modello;
    }
}

class Officina {

    ArrayList<Auto> autoList = new ArrayList<>();

    public void aggiungiAuto(Auto auto) {
        autoList.add(auto);
        System.out.println("Auto aggiunta: " + auto.modello);
    }

    public String toString() {
        if (autoList.isEmpty()) {
            return "Nessuna auto presente in officina";
        }
        String risultato = "\nAUTO IN OFFICINA\n";
        for (Auto auto : autoList) {
            risultato += auto.toString() + "\n";
        }
        return risultato;
    }
}

public class GestioneAutoOfficina {
    public static void main(String[] args) {

        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        Officina officina = new Officina();
        mostraMenu(officina, scannerStr, scannerInt);
    }

    

    private static void mostraMenu(Officina officina, Scanner scannerStr, Scanner scannerInt) {
        int scelta = 0;

        while (scelta != 3) {
            System.out.println("\nMENU");
            System.out.println("1. Mostra tutte le auto");
            System.out.println("2. Aggiungi un'auto");
            System.out.println("3. Esci");
            System.out.print("Scegli un'operazione: ");

            scelta = scannerInt.nextInt();

            switch (scelta) {
                case 1:
                    officina.toString();
                    break;

                case 2:
                    System.out.print("Inserisci la targa: ");
                    String targa = scannerStr.nextLine();
                    System.out.print("Inserisci il modello: ");
                    String modello = scannerStr.nextLine();
                    officina.aggiungiAuto(new Auto(targa, modello));
                    break;

                case 3:
                    System.out.println("Arrivederci!");
                    break;

                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }
    }
}