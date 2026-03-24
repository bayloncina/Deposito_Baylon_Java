import java.util.Scanner;

class Gioco {

    String titolo;
    String genere;
    double costoDiSviluppo;
    StatoProgetto stato; // "IN SVILUPPO", "IN TEST", "PUBBLICATO"

    // Costruttore parametrizzato: inizializza il gioco con tutti i dati
    Gioco(String titolo, String genere, double costoDiSviluppo, StatoProgetto stato) {
        this.titolo = titolo;
        this.genere = genere;
        this.costoDiSviluppo = costoDiSviluppo;
        this.stato = stato;
    }

    // Costruttore vuoto
    Gioco() {
    }

    // Stampa a schermo le informazioni del gioco
    void mostraGioco() {
        System.out.println("  Titolo: " + titolo +
                " | Genere: " + genere +
                " | Costo: euro " + costoDiSviluppo +
                " | Stato: " + stato);
    }
}

// Enum che rappresenta i possibili stati del progetto
enum StatoProgetto {
    IN_SVILUPPO,
    IN_TEST,
    PUBBLICATO
}

class Team {

    String nomeTeam;
    int numeroSviluppatori;
    Gioco giocoAssegnato;

    // Costruttore parametrizzato: inizializza il team con tutti i dati
    Team(String nomeTeam, int numeroSviluppatori, Gioco giocoAssegnato) {
        this.nomeTeam = nomeTeam;
        this.numeroSviluppatori = numeroSviluppatori;
        this.giocoAssegnato = giocoAssegnato;
    }

    // Costruttore vuoto
    Team() {
    }

    // Stampa a schermo le informazioni del team e del gioco assegnato
    void mostraTeam() {
        System.out.println("Team: " + nomeTeam + " | Sviluppatori: " + numeroSviluppatori);
        if (giocoAssegnato != null && giocoAssegnato.titolo != null) {
            giocoAssegnato.mostraGioco();
        } else {
            System.out.println("  Nessun gioco assegnato.");
        }
    }
}

public class EsercizioVideoludico {

    public static void main(String[] args) {

        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);

        // Creazione dei tre team con situazione iniziale
        Team team1 = new Team("Team Alpha", 5, new Gioco("ShadowRealm", "Action", 120000, StatoProgetto.IN_SVILUPPO));
        Team team2 = new Team("Team Beta", 3, new Gioco("StarDrift", "Simulation", 85000, StatoProgetto.IN_TEST));
        Team team3 = new Team("Team Gamma", 7, new Gioco("IronBastion", "Strategy", 200000, StatoProgetto.PUBBLICATO));

        visualizzaMenu(team1, team2, team3, scannerInt, scannerStr);

        scannerInt.close();
        scannerStr.close();
    }

    /**
     * Mostra il menu principale in loop finché l'utente non sceglie di uscire.
     */
    private static void visualizzaMenu(Team team1, Team team2, Team team3, Scanner scannerInt, Scanner scannerStr) {
        int scelta = 0;

        do {
            // Stampa le opzioni disponibili
            System.out.println("\nGESTIONE SVILUPPO VIDEOLUDICO");
            System.out.println("1 - Assegna gioco a un team");
            System.out.println("2 - Visualizza tutti i team");
            System.out.println("3 - Cerca il gioco con costo di sviluppo più alto");
            System.out.println("4 - Modifica stato di un gioco");
            System.out.println("5 - Esci");
            System.out.print("Scelta: ");

            scelta = scannerInt.nextInt();

            // Esegue l'operazione corrispondente alla scelta dell'utente
            switch (scelta) {
                case 1:
                    assegnaGioco(team1, team2, team3, scannerInt, scannerStr);
                    break;
                case 2:
                    visualizzaTutti(team1, team2, team3);
                    break;
                case 3:
                    cercaCostoMaggiore(team1, team2, team3);
                    break;
                case 4:
                    modificaStato(team1, team2, team3, scannerInt);
                    break;
                case 5:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }

        } while (scelta != 5); // il loop continua finché l'utente non sceglie 5
    }

    /**
     * Assegna un nuovo gioco al team scelto dall'utente.
     */
    private static void assegnaGioco(Team team1, Team team2, Team team3, Scanner scannerInt, Scanner scannerStr) {
        // Chiede all'utente quale team modificare
        Team team = scegliTeam(team1, team2, team3, scannerInt);
        if (team == null)
            return;

        // Lettura dei dati del nuovo gioco
        System.out.print("Titolo del gioco: ");
        String titolo = scannerStr.nextLine();

        System.out.print("Genere: ");
        String genere = scannerStr.nextLine();

        System.out.print("Costo di sviluppo: ");
        double costo = scannerInt.nextDouble();

        // Scelta dello stato tramite menu
        System.out.println("Scegli stato del progetto:");
        System.out.println("1 - IN SVILUPPO");
        System.out.println("2 - IN TEST");
        System.out.println("3 - PUBBLICATO");
        System.out.print("Scelta: ");
        int sceltaStato = scannerInt.nextInt();

        // Si recupera lo stato corrispondente dall'enum tramite i valori disponibili
        StatoProgetto stato;
        switch (sceltaStato) {
            case 1:
                stato = StatoProgetto.IN_SVILUPPO;
                break;
            case 2:
                stato = StatoProgetto.IN_TEST;
                break;
            case 3:
                stato = StatoProgetto.PUBBLICATO;
                break;
            default:
                // Se la scelta non è valida si imposta IN_SVILUPPO come default
                stato = StatoProgetto.IN_SVILUPPO;
                System.out.println("Scelta non valida. Impostato di default: IN SVILUPPO");
        }

        // Creazione e assegnazione del nuovo gioco al team
        team.giocoAssegnato = new Gioco(titolo, genere, costo, stato);
        System.out.println("Gioco assegnato con successo al team " + team.nomeTeam + ".");
    }

    /**
     *Stampa le informazioni di tutti e tre i team.
     */
    private static void visualizzaTutti(Team team1, Team team2, Team team3) {
        System.out.println("\n--- Dati di tutti i team ---");
        team1.mostraTeam();
        System.out.println();
        team2.mostraTeam();
        System.out.println();
        team3.mostraTeam();
    }

    /**
     * Confronta i costi di sviluppo e stampa il team
     * che ha il gioco con il costo più alto.
     */
    private static void cercaCostoMaggiore(Team team1, Team team2, Team team3) {
        // Si parte assumendo che il primo team abbia il gioco più costoso
        Team piuCostoso = team1;

        // Si confronta con gli altri due e si aggiorna se si trova un costo maggiore
        if (team2.giocoAssegnato.costoDiSviluppo > piuCostoso.giocoAssegnato.costoDiSviluppo) {
            piuCostoso = team2;
        }
        if (team3.giocoAssegnato.costoDiSviluppo > piuCostoso.giocoAssegnato.costoDiSviluppo) {
            piuCostoso = team3;
        }

        System.out.println("\nIl team con il gioco più costoso è: " + piuCostoso.nomeTeam);
        piuCostoso.giocoAssegnato.mostraGioco();
    }

    /**
     * Permette di modificare lo stato del gioco
     * assegnato al team scelto dall'utente.
     */
    private static void modificaStato(Team team1, Team team2, Team team3, Scanner scannerInt) {
        // Chiede all'utente quale team modificare
        Team team = scegliTeam(team1, team2, team3, scannerInt);
        if (team == null)
            return;

        // Mostra il menu degli stati disponibili
        System.out.println("Scegli nuovo stato per '" + team.giocoAssegnato.titolo + "':");
        System.out.println("1 - IN SVILUPPO");
        System.out.println("2 - IN TEST");
        System.out.println("3 - PUBBLICATO");
        System.out.print("Scelta: ");
        int sceltaStato = scannerInt.nextInt();

        // Si aggiorna lo stato del gioco con il valore enum corrispondente
        switch (sceltaStato) {
            case 1:
                team.giocoAssegnato.stato = StatoProgetto.IN_SVILUPPO;
                break;
            case 2:
                team.giocoAssegnato.stato = StatoProgetto.IN_TEST;
                break;
            case 3:
                team.giocoAssegnato.stato = StatoProgetto.PUBBLICATO;
                break;
            default:
                System.out.println("Scelta non valida. Stato non modificato.");
                return;
        }

        System.out.println("Stato aggiornato a: " + team.giocoAssegnato.stato);
    }


    /**
     * Metodo di utilità - Chiede all'utente di scegliere uno dei tre team
     * e restituisce l'oggetto Team corrispondente.
     * Restituisce null se la scelta non è valida.
     */
    private static Team scegliTeam(Team team1, Team team2, Team team3, Scanner scannerInt) {
        System.out.println("       SELEZIONA TEAM         ");
        System.out.println("1 - " + team1.nomeTeam);
        System.out.println("2 - " + team2.nomeTeam);
        System.out.println("3 - " + team3.nomeTeam);
        System.out.print("Scelta: ");
        int scelta = scannerInt.nextInt();

        // Ritorna il team selezionato oppure null se il numero non è 1, 2 o 3
        switch (scelta) {
            case 1:
                return team1;
            case 2:
                return team2;
            case 3:
                return team3;
            default:
                System.out.println("Scelta non valida.");
                return null;
        }
    }
}