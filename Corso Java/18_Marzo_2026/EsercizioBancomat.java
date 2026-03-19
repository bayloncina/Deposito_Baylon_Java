import java.util.Scanner;

public class EsercizioBancomat {
    public static void main(String[] args) {

        Scanner scObj = new Scanner(System.in);
        double saldo = 1000.00;
        double importo = 0;
        boolean continua = true;

        do {
            // mostra menu
            System.out.println("MENU");
            System.out.println("1 - Visualizza Saldo");
            System.out.println("2 - Preleva");
            System.out.println("3 - Deposita");
            System.out.println("4 - Esci");
            System.out.print("Scelta: ");
            int scelta = scObj.nextInt();

            switch (scelta) {
                case 1:
                    // visualizza saldo
                    System.out.println("Saldo disponibile euro " + saldo);
                    break;
                case 2:
                    // prelievo
                    System.out.println("Inserisci l'importo da prelevare");
                    importo = scObj.nextDouble();
                    // controllo che il saldo sia maggiore dell importo da prelevare
                    if (saldo < importo) {
                        System.out.println("Fondi insufficienti.");
                    } else {
                        saldo -= importo; // aggiorno saldo
                        System.out.println("Saldo disponibile " + saldo);
                    }
                    break;
                case 3:
                    // deposito
                    System.out.println("Inserisci l'importo da depositare");
                    importo = scObj.nextDouble();
                    saldo += importo; // aggiorno saldo
                    break;
                case 4:
                    // uscita
                    System.out.println("Arrivederci!");
                    continua = false;
                    break;
                default:
                    System.out.println("Scelta non valida");
                    break;
            }

        } while (continua);
        
        //stampo saldo disponibile
        System.out.printf("Saldo disponibile rimanente: euro " + saldo);
        scObj.close();
    }
}
