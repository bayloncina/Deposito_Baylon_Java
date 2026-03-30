import java.util.ArrayList;
import java.util.Scanner;

class Aereo {

    private static int contatore = 0;
    private String modello;
    private int numeroPosti;
    private String codice;

    Aereo(String modello, int numeroPosti) {
        this.modello = modello;
        this.numeroPosti = numeroPosti;
        this.codice = "AZ" + String.format("0", ++contatore);
        ;
    }

    //
    public String getModello() {
        return modello;
    }

    public int getNumeroPosti() {
        return numeroPosti;
    }

    public String getCodice() {
        return codice;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    // setter con controllo: numeroPosti deve essere positivo
    public void setNumeroPosti(int numeroPosti) {
        if (numeroPosti <= 0) {
            System.out.println("Numero posti non valido.");
            return;
        }
        this.numeroPosti = numeroPosti;
    }

    public String toString() {
        return "Codice: " + codice + " | Modello: " + modello + " | Posti: " + numeroPosti;
    }
}

class Pilota {
    private String nome;
    private String numeroBrevetto;
    private int oreVolo;

    Pilota(String nome, String numeroBrevetto, int oreVolo) {
        this.nome = nome;
        this.numeroBrevetto = numeroBrevetto;
        this.oreVolo = oreVolo;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroBrevetto() {
        return numeroBrevetto;
    }

    public int getOreVolo() {
        return oreVolo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroBrevetto(String numeroBrevetto) {
        this.numeroBrevetto = numeroBrevetto;
    }

    // setter con controllo: oreVolo deve essere maggiore di zero
    public void setOreVolo(int oreVolo) {
        if (oreVolo <= 0) {
            System.out.println("Ore volo non valide. Devono essere maggiori di zero.");
            return;
        }
        this.oreVolo = oreVolo;
    }

    public String toString() {
        return "Nome: " + nome + " | Brevetto: " + numeroBrevetto + " | Ore volo: " + oreVolo;
    }
}

class CompagniaAerea {
    private String nome;
    private ArrayList<Aereo> flotta = new ArrayList<>();
    private ArrayList<Pilota> piloti = new ArrayList<>();

    CompagniaAerea(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // aggiunge un aereo alla flotta
    public void aggiungiAereo(Aereo aereo) {
        flotta.add(aereo);
        System.out.println("Aereo aggiunto: " + aereo.getCodice());
    }

    // aggiunge un pilota
    public void aggiungiPilota(Pilota pilota) {
        piloti.add(pilota);
        System.out.println("Pilota aggiunto: " + pilota.getNome());
    }

    // stampa tutte le informazioni della compagnia
    public String toString() {
    String risultato = "\nCOMPAGNIA: " + nome;

    risultato += "\n-- FLOTTA --\n";
    if (flotta.isEmpty()) {
        risultato += "Nessun aereo presente.\n";
    } else {
        for (Aereo a : flotta) {
            risultato += a.toString() + "\n";
        }
    }

    risultato += "\n-- PILOTI --\n";
    if (piloti.isEmpty()) {
        risultato += "Nessun pilota presente.\n";
    } else {
        for (Pilota p : piloti) {
            risultato += p.toString() + "\n";
        }
    }

    return risultato;
}
}

public class GestioneCompagnia {
    public static void main(String[] args) {

        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        CompagniaAerea compagnia = new CompagniaAerea("Alitalia");

        // aerei di default
        compagnia.aggiungiAereo(new Aereo("Boeing 737", 180));
        compagnia.aggiungiAereo(new Aereo("Airbus A320", 150));

        // piloti di default
        compagnia.aggiungiPilota(new Pilota("Miriam", "BR001", 12));
        compagnia.aggiungiPilota(new Pilota("Lucia", "BR002", 8));

        mostraMenu(compagnia, scannerStr, scannerInt);
    }

    private static void mostraMenu(CompagniaAerea compagnia, Scanner scannerStr, Scanner scannerInt) {
        int scelta = 0;

        while (scelta != 4) {
            System.out.println("\nMENU");
            System.out.println("1. Mostra informazioni compagnia");
            System.out.println("2. Aggiungi aereo");
            System.out.println("3. Aggiungi pilota");
            System.out.println("4. Esci");
            System.out.print("Scegli un'operazione: ");

            scelta = scannerInt.nextInt();

            switch (scelta) {
                case 1:
                    System.out.println(compagnia.toString());
                    break;
                case 2:
                    aggiungiAereo(compagnia, scannerStr, scannerInt);
                    break;
                case 3:
                    aggiungiPilota(compagnia, scannerStr, scannerInt);
                    break;
                case 4:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }
    }

    // legge i dati e aggiunge un aereo alla compagnia
    private static void aggiungiAereo(CompagniaAerea compagnia, Scanner scannerStr, Scanner scannerInt) {
        System.out.print("Inserisci il modello: ");
        String modello = scannerStr.nextLine();
        System.out.print("Inserisci il numero di posti: ");
        int posti = scannerInt.nextInt();
        compagnia.aggiungiAereo(new Aereo(modello, posti));
    }

    // legge i dati e aggiunge un pilota alla compagnia
    private static void aggiungiPilota(CompagniaAerea compagnia, Scanner scannerStr, Scanner scannerInt) {
        System.out.print("Inserisci il nome: ");
        String nome = scannerStr.nextLine();
        System.out.print("Inserisci il numero di brevetto: ");
        String brevetto = scannerStr.nextLine();
        System.out.print("Inserisci le ore di volo: ");
        int ore = scannerInt.nextInt();
        compagnia.aggiungiPilota(new Pilota(nome, brevetto, ore));
    }
}