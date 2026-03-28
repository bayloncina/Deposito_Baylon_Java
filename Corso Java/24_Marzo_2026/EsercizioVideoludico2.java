import java.util.ArrayList;
import java.util.Scanner;

class Sviluppatore {

    String nome;
    String ruolo;

    // Costruttore parametrizzato: inizializza lo sviluppatore con nome e ruolo
    Sviluppatore(String nome, String ruolo) {
        this.nome = nome;
        this.ruolo = ruolo;
    }

    // Costruttore vuoto
    Sviluppatore() {}

    // Stampa a schermo le informazioni dello sviluppatore
    void mostraSviluppatore() {
        System.out.println("    - " + nome + " (" + ruolo + ")");
    }
}

class Gioco {

    String titolo;
    String genere;
    double costoDiSviluppo;
    StatoProgetto stato;

    // Costruttore parametrizzato: inizializza il gioco con tutti i dati
    Gioco(String titolo, String genere, double costoDiSviluppo, StatoProgetto stato) {
        this.titolo = titolo;
        this.genere = genere;
        this.costoDiSviluppo = costoDiSviluppo;
        this.stato = stato;
    }

    // Costruttore vuoto
    Gioco() {}

    // Stampa a schermo le informazioni del gioco
    void mostraGioco() {
        System.out.println("  Titolo: " + titolo +
                " | Genere: " + genere +
                " | Costo: euro" + costoDiSviluppo +
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
    // ArrayList di sviluppatori: permette di gestire un numero variabile di membri
    ArrayList<Sviluppatore> sviluppatori;
    Gioco giocoAssegnato;

    // Costruttore parametrizzato: richiede almeno uno sviluppatore obbligatoriamente
    Team(String nomeTeam, ArrayList<Sviluppatore> sviluppatori, Gioco giocoAssegnato) {
        this.nomeTeam = nomeTeam;
        // Controllo: il team non può avere zero sviluppatori
        if (sviluppatori == null || sviluppatori.size() == 0) {
            System.out.println("Errore: un team deve avere almeno uno sviluppatore.");
            this.sviluppatori = new ArrayList<Sviluppatore>();
        } else {
            this.sviluppatori = sviluppatori;
        }
        this.giocoAssegnato = giocoAssegnato;
    }

    // Costruttore vuoto
    Team() {}

    // Stampa a schermo le informazioni del team, degli sviluppatori e del gioco assegnato
    void mostraTeam() {
        System.out.println("Team: " + nomeTeam + " | Sviluppatori totali: " + sviluppatori.size());
        System.out.println("  Membri:");
        // Si scorre la lista e si stampa ogni sviluppatore
        for (int i = 0; i < sviluppatori.size(); i++) {
            sviluppatori.get(i).mostraSviluppatore();
        }
        if (giocoAssegnato != null && giocoAssegnato.titolo != null) {
            giocoAssegnato.mostraGioco();
        } else {
            System.out.println("  Nessun gioco assegnato.");
        }
    }
}

public class EsercizioVideoludico2 {

    public static void main(String[] args) {

        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);

        // Creazione degli sviluppatori per il team 1
        ArrayList<Sviluppatore> membriTeam1 = new ArrayList<Sviluppatore>();
        membriTeam1.add(new Sviluppatore("Mario Rossi", "Programmatore"));
        membriTeam1.add(new Sviluppatore("Laura Bianchi", "Game Designer"));
        membriTeam1.add(new Sviluppatore("Luca Verdi", "Tester"));

        // Creazione degli sviluppatori per il team 2
        ArrayList<Sviluppatore> membriTeam2 = new ArrayList<Sviluppatore>();
        membriTeam2.add(new Sviluppatore("Anna Neri", "Programmatore"));
        membriTeam2.add(new Sviluppatore("Paolo Gialli", "Grafico"));
        membriTeam2.add(new Sviluppatore("Sara Blu", "Tester"));

        // Creazione degli sviluppatori per il team 3
        ArrayList<Sviluppatore> membriTeam3 = new ArrayList<Sviluppatore>();
        membriTeam3.add(new Sviluppatore("Giorgio Viola", "Programmatore"));
        membriTeam3.add(new Sviluppatore("Elena Rosa", "Game Designer"));
        membriTeam3.add(new Sviluppatore("Matteo Grigi", "Sound Designer"));

        // Creazione dei tre team con sviluppatori e giochi assegnati
        Team team1 = new Team("Team Alpha", membriTeam1, new Gioco("ShadowRealm", "Action", 120000, StatoProgetto.IN_SVILUPPO));
        Team team2 = new Team("Team Beta", membriTeam2, new Gioco("StarDrift", "Simulation", 85000, StatoProgetto.IN_TEST));
        Team team3 = new Team("Team Gamma", membriTeam3, new Gioco("IronBastion", "Strategy", 200000, StatoProgetto.PUBBLICATO));

        //passo i paramentri che servono per il metodo
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
            System.out.println("5 - Aggiungi sviluppatore a un team");
            System.out.println("6 - Esci");
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
                    aggiungiSviluppatore(team1, team2, team3, scannerInt, scannerStr);
                    break;
                case 6:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }

        } while (scelta != 6); // il loop continua finché l'utente non sceglie 6
    }

    private static void assegnaGioco(Team team1, Team team2, Team team3, Scanner scannerInt, Scanner scannerStr) {
        // Chiede all'utente quale team modificare
        Team team = scegliTeam(team1, team2, team3, scannerInt);
        if (team == null) return;

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

        // Si recupera lo stato corrispondente dall'enum
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

    private static void visualizzaTutti(Team team1, Team team2, Team team3) {
        System.out.println("\n--- Dati di tutti i team ---");
        team1.mostraTeam();
        System.out.println();
        team2.mostraTeam();
        System.out.println();
        team3.mostraTeam();
    }
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

    private static void modificaStato(Team team1, Team team2, Team team3, Scanner scannerInt) {
        // Chiede all'utente quale team modificare
        Team team = scegliTeam(team1, team2, team3, scannerInt);
        if (team == null) return;

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

    private static void aggiungiSviluppatore(Team team1, Team team2, Team team3, Scanner scannerInt, Scanner scannerStr) {
        // Chiede all'utente quale team modificare
        Team team = scegliTeam(team1, team2, team3, scannerInt);
        if (team == null) return;

        // Lettura dei dati del nuovo sviluppatore
        System.out.print("Nome dello sviluppatore: ");
        String nome = scannerStr.nextLine();

        System.out.print("Ruolo: ");
        String ruolo = scannerStr.nextLine();

        // Creazione del nuovo sviluppatore e aggiunta alla lista del team
        Sviluppatore nuovoSviluppatore = new Sviluppatore(nome, ruolo);
        team.sviluppatori.add(nuovoSviluppatore);

        System.out.println("Sviluppatore " + nome + " aggiunto al team " + team.nomeTeam + ".");
        System.out.println("Totale sviluppatori: " + team.sviluppatori.size());
    }

    /**
     * Chiede all'utente di scegliere uno dei tre team
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