import java.util.ArrayList;
import java.util.Scanner;

public class ConsegnaManager {
    private ArrayList<VeicoloConsegna> veicoli = new ArrayList<>();

    private Scanner scannerInt = new Scanner(System.in);
    private Scanner scannerStr = new Scanner(System.in);

    public void aggiungiVeicolo() {

        System.out.println("Tipo veicolo: 1. Furgone  2. Drone");
        int tipo = scannerInt.nextInt();
        scannerInt.nextLine();

        System.out.print("Targa: ");
        String targa = scannerStr.nextLine();
        System.out.print("Carico massimo (kg): ");
        float carico = scannerInt.nextFloat();
        scannerInt.nextLine();

        if (tipo == 1) {
            veicoli.add(new Furgone(targa, carico));
            System.out.println("Furgone aggiunto!");
        } else if (tipo == 2) {
            veicoli.add(new Drone(targa, carico));
            System.out.println("Drone aggiunto!");
        } else {
            System.out.println("Tipo non valido.");
        }
    }

    public void eseguiConsegna() {
        if (veicoli.isEmpty()) {
            System.out.println("Nessun veicolo disponibile.");
            return;
        }
        mostraVeicoli();
        System.out.print("Scegli veicolo (numero): ");
        int scelta = scannerInt.nextInt() - 1;
        scannerInt.nextLine();

        // controlla che la scelta sia dentro i limiti della lista
        if (scelta < 0 || scelta >= veicoli.size()) {
            System.out.println("Scelta non valida.");
            return;
        }

        System.out.print("Destinazione: ");
        String destinazione = scannerStr.nextLine();
        System.out.print("Peso pacco (kg): ");
        float peso = scannerInt.nextFloat();
        scannerInt.nextLine();

        // polimorfismo chiama consegnaPacco() della classe giusta

        VeicoloConsegna v = veicoli.get(scelta);
        v.consegnaPacco(destinazione, peso);

        // v è dichiarato come VeicoloConsegna
        // e VeicoloConsegna non conosce il metodo tracciaConsegna()
        // quindi verifica che il tipo di veicolo implementa iTracciabile
        if (v instanceof ITracciabile t) {
            System.out.print("Codice tracking: ");
            String codice = scannerStr.nextLine();
            t.tracciaConsegna(codice);
        }
    }

    public void mostraVeicoli() {
        if (veicoli.isEmpty()) {
            System.out.println("Nessun veicolo presente.");
            return;
        }
        System.out.println("\n======= VEICOLI =======");
        for (int i = 0; i < veicoli.size(); i++) {
            System.out.println((i + 1) + ". " + veicoli.get(i).toString());
        }
    }
}