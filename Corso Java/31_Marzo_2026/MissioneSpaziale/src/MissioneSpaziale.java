import java.util.Scanner;

public class MissioneSpaziale {

    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    static StazioneSpaziale stazione = new StazioneSpaziale();

    public static void main(String[] args) {
        System.out.println("=== Benvenuto nella Missione Spaziale ===");

        // Meccanismo di identificazione del ruolo
        System.out.print("Qual è il tuo pianeta preferito? (risposta 'Marte' = Scienziato, altro = Ispettore): ");
        String risposta = scannerStr.nextLine();

        System.out.print("Inserisci il tuo nome: ");
        String nome = scannerStr.nextLine();
        System.out.print("Inserisci la tua email: ");
        String email = scannerStr.nextLine();

        // Crea l'astronauta nel ruolo corretto
        Astronauta astronauta;
        if (risposta.equalsIgnoreCase("Marte")) {
            astronauta = new Scienziato(nome, email);
            System.out.println("Ruolo assegnato: Scienziato");
        } else {
            astronauta = new Ispettore(nome, email);
            System.out.println("Ruolo assegnato: Ispettore");
        }
        boolean running = true;
        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Visualizza dati astronauta");
            System.out.println("2. Ripeti login (rigenera ossigeno)");
            System.out.println("3. Interagisci con il profilo");
            System.out.println("4. Esci");
            System.out.print("Scelta: ");

            String scelta = scannerStr.nextLine();

            switch (scelta) {
                case "1":
                    astronauta.visualizzaDati();
                    break;

                case "2":
                    float nuovoOssigeno = astronauta.login();
                    System.out.printf("Nuovo credito ossigeno: %.2f%n", nuovoOssigeno);
                    break;

                case "3":
                    // Verifica se è il momento di evolvere
                    // Controlla se l'astronauta è uno Scienziato (o una sua sottoclasse, quindi
                    // anche ScienziatoCapo)
                    // Se sì, crea automaticamente la variabile "scienziato" di tipo Scienziato
                    if (astronauta instanceof Scienziato scienziato) {

                        // isEsperto() restituisce true se azioniSvolte >= 3
                        // !(astronauta instanceof ScienziatoCapo) evita di creare un nuovo
                        // ScienziatoCapo
                        // se l'astronauta è già diventato ScienziatoCapo in precedenza
                        if (scienziato.isEsperto() && !(astronauta instanceof ScienziatoCapo)) {
                            System.out.println("Sei diventato ScienziatoCapo!");
                            // Sostituisce l'oggetto corrente con uno di tipo ScienziatoCapo
                            // passando lo scienziato attuale al costruttore per copiare nome ed email

                            astronauta = new ScienziatoCapo(scienziato);
                        }
                        // Chiama il metodo che gestisce le azioni dello Scienziato (o ScienziatoCapo)
                        // Passa "astronauta" aggiornato: se è appena evoluto, ora è già un
                        // ScienziatoCapo
                        gestisciScienziato(astronauta);
                    } else if (astronauta instanceof Ispettore ispettore) {
                        if (ispettore.isEsperto() && !(astronauta instanceof IspettoreEsperto)) {
                            System.out.println("Sei diventato IspettoreEsperto!");
                            astronauta = new IspettoreEsperto(ispettore);
                        }
                        gestisciIspettore(astronauta);
                    }
                    break;

                case "4":
                    System.out.println("Arrivederci, " + astronauta.getNome() + "!");
                    running = false;
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }
        }

        scannerStr.close();
    }

    static void gestisciScienziato(Astronauta a) {
        if (a instanceof ScienziatoCapo capo) {
            System.out.print("Quanti esperimenti vuoi aggiungere? ");
            int n = scannerInt.nextInt();
            String[] esps = new String[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Esperimento " + (i + 1) + ": ");
                esps[i] = scannerStr.nextLine();
            }
            capo.aggiungiTuttiEsperimenti(stazione, esps);
        } else if (a instanceof Scienziato s) {
            System.out.print("Nome esperimento da aggiungere: ");
            String esp = scannerStr.nextLine();
            s.aggiungiEsperimento(stazione, esp);
        }
    }

    static void gestisciIspettore(Astronauta a) {
        if (a instanceof IspettoreEsperto esperto) {
            esperto.stampaTutteValutazioni(stazione);
        } else if (a instanceof Ispettore i) {
            System.out.print("Inserisci una valutazione (1-5): ");
            int val = scannerInt.nextInt();
            i.inserisciValutazione(stazione, val);
        }
    }
}