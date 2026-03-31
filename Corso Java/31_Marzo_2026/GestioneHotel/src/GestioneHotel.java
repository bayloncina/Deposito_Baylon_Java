import java.util.ArrayList;
import java.util.Scanner;

public class GestioneHotel {

    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerStr = new Scanner(System.in);
    static ArrayList<Hotel> hotels = new ArrayList<>();

    public static void main(String[] args) {

        /*
         * Hotel hotel = new Hotel("Hotel Barletta");
         * 
         * // 2 camere normali
         * Camera c1 = new Camera(101, 80.0f);
         * Camera c2 = new Camera(102, 95.0f);
         * 
         * // 2 suite
         * Suite s1 = new Suite(201, 200.0f, "Jacuzzi e vista mare");
         * Suite s2 = new Suite(202, 250.0f, "Terrazza privata");
         * 
         * hotel.aggiungiCamera(c1);
         * hotel.aggiungiCamera(c2);
         * hotel.aggiungiCamera(s1);
         * hotel.aggiungiCamera(s2);
         * 
         * System.out.println("\n=== dettagli() senza parametri ===");
         * c1.dettagli();
         * s1.dettagli();
         * 
         * System.out.println("\n=== dettagli(boolean) con parametro ===");
         * c1.dettagli(true); // stampa anche il prezzo
         * c2.dettagli(false); // stampa solo il numero
         * s1.dettagli(true);
         * s2.dettagli(false);
         * 
         * System.out.println("\n=== Conteggio Suite ===");
         * int numeroSuite = Hotel.contaSuite(hotel.getCamere());
         * System.out.println("Suite presenti: " + numeroSuite);
         */

        // ------------------------------------------------------------------------------

        boolean continua = true;
        while (continua) {
            System.out.println("\n=== GESTORE HOTEL ===");
            System.out.println("1. Crea un nuovo hotel");
            System.out.println("2. Aggiungi camera a un hotel");
            System.out.println("3. Visualizza camere di un hotel");
            System.out.println("4. Conta suite di un hotel");
            System.out.println("5. Visualizza tutti gli hotel");
            System.out.println("6. Esci");
            System.out.print("Scelta: ");
            int scelta = scannerInt.nextInt();

            switch (scelta) {
                case 1 -> creaHotel();
                case 2 -> aggiungiCamera();
                case 3 -> visualizzaCamere();
                case 4 -> contaSuite();
                case 5 -> visualizzaTuttiHotel();
                case 6 -> continua = false;
                default -> System.out.println("Scelta non valida.");
            }
        }

        System.out.println("Arrivederci!");
        scannerInt.close();
    }

    // ── crea un nuovo hotel e lo aggiunge alla lista ──
    static void creaHotel() {
        System.out.print("Nome hotel: ");
        String nome = scannerStr.nextLine();
        hotels.add(new Hotel(nome));
        System.out.println("Hotel \"" + nome + "\" creato!");
    }

    // ── sceglie un hotel e aggiunge una camera normale o suite ──
    static void aggiungiCamera() {
        Hotel hotel = scegliHotel();
        if (hotel == null)
            return;

        System.out.println("Tipo camera: 1. Camera normale  2. Suite");
        int tipo = scannerInt.nextInt();
        scannerInt.nextLine();

        System.out.print("Numero camera: ");
        int numero = scannerInt.nextInt();
        scannerInt.nextLine();
        System.out.print("Prezzo: ");
        float prezzo = scannerInt.nextInt();
        scannerInt.nextLine();

        if (tipo == 1) {
            hotel.aggiungiCamera(new Camera(numero, prezzo));
        } else if (tipo == 2) {
            System.out.print("Inserisci i servizi extra: ");
            String extra = scannerStr.nextLine();
            hotel.aggiungiCamera(new Suite(numero, prezzo, extra));
        } else {
            System.out.println("Tipo non valido.");
        }
    }

    // ── stampa tutte le camere di un hotel scelto ──
    static void visualizzaCamere() {

        Hotel hotel = scegliHotel();
        if (hotel == null)
            return;

        System.out.println("\n=== Camere di " + hotel.getNome() + " ===");
        if (hotel.getCamere().isEmpty()) {
            System.out.println("Nessuna camera presente.");
            return;
        }
        for (Camera c : hotel.getCamere()) {
            c.dettagli(true); // mostra tutto, prezzo incluso
        }
    }

    // ── conta le suite di un hotel scelto ──
    static void contaSuite() {
        Hotel hotel = scegliHotel();
        if (hotel == null)
            return;
        // prende l'arrayList di camere e chiama il metodo contaSuite nella classe hotel
        int n = Hotel.contaSuite(hotel.getCamere());
        System.out.println("Suite in \"" + hotel.getNome() + "\": " + n);
    }

    // ── stampa la lista di tutti gli hotel con numero camere ──
    static void visualizzaTuttiHotel() {
        if (hotels.isEmpty()) {
            System.out.println("Nessun hotel creato.");
            return;
        }
        System.out.println("\n=== Tutti gli Hotel ===");
        for (int i = 0; i < hotels.size(); i++) {
            Hotel h = hotels.get(i);
            System.out.println((i + 1) + ". " + h.getNome() +
                    " numero camere: " + h.getCamere().size() +
                    " | suite: " + Hotel.contaSuite(h.getCamere()));
        }
    }

    // ── metodo di supporto: mostra la lista hotel e chiede quale scegliere ──
    static Hotel scegliHotel() {
        if (hotels.isEmpty()) {
            System.out.println("Nessun hotel disponibile. Creane uno prima.");
            return null;
        }
        System.out.println("Scegli un hotel:");
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getNome());
        }
        System.out.print("Numero: ");
        int scelta = scannerInt.nextInt() - 1;

        if (scelta < 0 || scelta >= hotels.size()) {
            System.out.println("Scelta non valida.");
            return null;
        }
        return hotels.get(scelta);
    }
}
