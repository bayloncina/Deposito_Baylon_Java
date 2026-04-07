import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        // ottiene l'unica istanza del gestore (Singleton) e registra i tre osservatori
        GestoreOrdini gestore = GestoreOrdini.getIstanza();
        gestore.registraOsservatore(new RepartoMagazzino());
        gestore.registraOsservatore(new RepartoSpedizioni());
        gestore.registraOsservatore(new SistemaNotifiche());

        Scanner scanner = new Scanner(System.in);
        int scelta = 0;

        do {
            System.out.println("\n--- MENU GESTIONE ORDINI ---");
            System.out.println("1 - Inserisci nuovo ordine");
            System.out.println("2 - Visualizza tutti gli ordini");
            System.out.println("3 - Cerca ordine per ID");
            System.out.println("4 - Aggiorna stato ordine");
            System.out.println("5 - Esci");
            System.out.print("Scelta: ");
            scelta = scanner.nextInt();
            scanner.nextLine(); // consuma il newline rimasto dopo nextInt()

            switch (scelta) {
                case 1:
                    // raccoglie i dati del nuovo ordine dall'utente
                    System.out.print("Cliente: ");
                    String cliente = scanner.nextLine();
                    System.out.print("Prodotto: ");
                    String prodotto = scanner.nextLine();
                    System.out.print("Quantità: ");
                    int quantita = scanner.nextInt();
                    scanner.nextLine();

                    // mostra il menu degli stati disponibili (enum StatoOrdine)
                    System.out.println("Stato:");
                    System.out.println("  1 - CREATO");
                    System.out.println("  2 - IN_PREPARAZIONE");
                    System.out.println("  3 - SPEDITO");
                    System.out.println("  4 - CONSEGNATO");
                    System.out.print("Scelta stato: ");
                    int sceltaStato = scanner.nextInt();
                    scanner.nextLine();

                    // converte la scelta numerica nel valore enum corrispondente
                    StatoOrdine stato = null;
                    switch (sceltaStato) {
                        case 1:
                            stato = StatoOrdine.CREATO;
                            break;
                        case 2:
                            stato = StatoOrdine.IN_PREPARAZIONE;
                            break;
                        case 3:
                            stato = StatoOrdine.SPEDITO;
                            break;
                        case 4:
                            stato = StatoOrdine.CONSEGNATO;
                            break;
                        default:
                            System.out.println("Scelta non valida, ordine non inserito.");
                            break;
                    }

                    // crea e inserisce l'ordine solo se lo stato è valido
                    // inserisciOrdine salva sul DB e notifica gli osservatori
                    if (stato != null) {
                        gestore.inserisciOrdine(new Ordine(cliente, prodotto, quantita, stato));
                    }
                    break;

                case 2:
                    // recupera e stampa tutti gli ordini presenti nel database
                    gestore.visualizzaTuttiOrdini();
                    break;

                case 3:
                    // cerca un ordine specifico tramite il suo id
                    System.out.print("ID ordine: ");
                    gestore.cercaOrdine(scanner.nextInt());
                    break;

                case 4:
                    // raccoglie l'id dell'ordine da aggiornare
                    System.out.print("ID ordine da aggiornare: ");
                    int idAggiorna = scanner.nextInt();
                    scanner.nextLine();

                    // mostra il menu degli stati disponibili
                    System.out.println("Nuovo stato:");
                    System.out.println("  1 - CREATO");
                    System.out.println("  2 - IN_PREPARAZIONE");
                    System.out.println("  3 - SPEDITO");
                    System.out.println("  4 - CONSEGNATO");
                    System.out.print("Scelta stato: ");
                    int sceltaNuovoStato = scanner.nextInt();
                    scanner.nextLine();

                    // converte la scelta numerica nel valore enum corrispondente
                    StatoOrdine nuovoStato = null;
                    switch (sceltaNuovoStato) {
                        case 1:
                            nuovoStato = StatoOrdine.CREATO;
                            break;
                        case 2:
                            nuovoStato = StatoOrdine.IN_PREPARAZIONE;
                            break;
                        case 3:
                            nuovoStato = StatoOrdine.SPEDITO;
                            break;
                        case 4:
                            nuovoStato = StatoOrdine.CONSEGNATO;
                            break;
                        default:
                            System.out.println("Scelta non valida, stato non aggiornato.");
                            break;
                    }

                    // aggiorna lo stato sul DB e notifica gli osservatori
                    // solo se la scelta è valida
                    if (nuovoStato != null) {
                        gestore.aggiornaStato(idAggiorna, nuovoStato);
                    }
                    break;

                case 5:
                    System.out.println("Uscita.");
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }

        } while (scelta != 5); // il loop continua finché l'utente non sceglie 5

        scanner.close(); // chiude lo scanner per liberare le risorse
    }
}