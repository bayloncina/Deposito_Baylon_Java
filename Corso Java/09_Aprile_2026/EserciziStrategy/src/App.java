import java.util.Scanner;

import strategyFacile.*;
import strategyMedio.*;


public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("--------- Esercizio facile ----------");
        Operazione addizione = new Addizione();
        Operazione moltiplicazione = new Moltiplicazione();

        Calcolatore calc = new Calcolatore(moltiplicazione);

        calc.setOperazione(moltiplicazione);
        calc.eseguiOperazione(5, 10);
        calc.setOperazione(addizione);
        calc.eseguiOperazione(10, 2);
        System.out.println("--------- ----------------------- ----------");

        System.out.println("--------- Esercizio medio ----------");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci l'importo da pagare: ");
        double importo = scanner.nextDouble();

        System.out.println("Scegli il metodo di pagamento:");
        System.out.println("1 - Carta di credito");
        System.out.println("2 - PayPal");
        System.out.print("Scelta: ");
        int scelta = scanner.nextInt();

        PagamentoContext context;

        if (scelta == 1) {
            context = new PagamentoContext(new CartaDiCredito("Mario Rossi", 1234567890));
        } else {
            context = new PagamentoContext(new PayPal("mario@email.com"));
        }

        context.eseguiPagamento(importo);

        scanner.close();
        System.out.println("--------- ----------------------- ----------");
    }
}
