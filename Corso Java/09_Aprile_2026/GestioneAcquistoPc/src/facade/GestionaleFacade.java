package facade;

import decorator.*;
import strategy.*;
import java.util.Scanner;

public class GestionaleFacade {

    private IComputer computer;
    private PagamentoContext pagamentoContext;
    private Scanner scanner = new Scanner(System.in);

    public void accendiSistema() {

        int scelta;

        do {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Scegli configurazione base");
            System.out.println("2. Aggiungi componente extra");
            System.out.println("3. Scegli metodo di pagamento");
            System.out.println("4. Riepilogo ordine");
            System.out.println("5. Conferma ordine");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");
            scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {

                case 1:
                    System.out.println("1. PC Ufficio");
                    System.out.println("2. PC Gaming");
                    System.out.print("Scelta: ");
                    int base = scanner.nextInt();
                    scanner.nextLine();
                    switch (base) {
                        case 1:
                            computer = new ComputerBaseUfficio();
                            break;
                        case 2:
                            computer = new ComputerBaseGaming();
                            break;
                        default:
                            System.out.println("Scelta non valida.");
                            continue;
                    }
                    System.out.println("Configurazione selezionata: " + computer.getDescrizione());
                    break;

                case 2:
                    if (computer == null) {
                        System.out.println("Scegli prima una configurazione base.");
                        break;
                    }
                    System.out.println("1. RAM Extra      (+€80)");
                    System.out.println("2. SSD Extra      (+€120)");
                    System.out.println("3. Scheda Video   (+€300)");
                    System.out.println("4. Raffreddamento (+€60)");
                    System.out.print("Scelta: ");
                    int extra = scanner.nextInt();
                    scanner.nextLine();
                    switch (extra) {
                        case 1:
                            computer = new RamExtra(computer);
                            break;
                        case 2:
                            computer = new SsdExtra(computer);
                            break;
                        case 3:
                            computer = new SchedaVideoExtra(computer);
                            break;
                        case 4:
                            computer = new RaffeddamentoExtra(computer);
                            break;
                        default:
                            System.out.println("Scelta non valida.");
                            continue;
                    }
                    System.out.println("Aggiornamento: " + computer.getDescrizione());
                    break;

                case 3:
                    System.out.println("1. Carta di Credito");
                    System.out.println("2. PayPal");
                    System.out.println("3. Bonifico");
                    System.out.print("Scelta: ");
                    int pag = scanner.nextInt();
                    scanner.nextLine();
                    switch (pag) {
                        case 1:
                            System.out.print("Nome titolare: ");
                            String nome = scanner.nextLine();
                            System.out.print("Numero carta: ");
                            int numero = scanner.nextInt();
                            scanner.nextLine();
                            pagamentoContext = new PagamentoContext(new CartaDiCredito(nome, numero));
                            break;
                        case 2:
                            System.out.print("Nome titolare: ");
                            String email = scanner.nextLine();
                            pagamentoContext = new PagamentoContext(new PayPal(email));
                            break;
                        case 3:
                            System.out.print("Numero conto ");
                            String nConto = scanner.nextLine();
                            pagamentoContext = new PagamentoContext(new Bonifico(nConto));
                            break;
                        default:
                            System.out.println("Scelta non valida.");
                            continue;
                    }
                    System.out.println("Metodo di pagamento impostato.");
                    break;

                case 4:
                    if (computer == null) {
                        System.out.println("Nessuna configurazione selezionata.");
                        break;
                    }
                    System.out.println("\n===== RIEPILOGO ORDINE =====");
                    System.out.println("Configurazione: " + computer.getDescrizione());
                    System.out.println("Prezzo totale:  € " + computer.getPrezzo());
                    System.out.println("============================");
                    break;

                case 5:
                    if (computer == null || pagamentoContext == null) {
                        System.out.println("Configura il computer e scegli un pagamento prima.");
                        break;
                    }
                    System.out.println("\n===== RIEPILOGO ORDINE =====");
                    System.out.println("Configurazione: " + computer.getDescrizione());
                    System.out.println("Prezzo totale:  € " + computer.getPrezzo());
                    System.out.println("============================");
                    System.out.println("\nConferma ordine in corso...");
                    pagamentoContext.eseguiPagamento(computer.getPrezzo());
                    System.out.println("Ordine confermato!");
                    scelta = 0; // esce dal menu dopo la conferma
                    break;

                case 0:
                    System.out.println("Uscita.");
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }

        } while (scelta != 0);

        scanner.close();
    }
}