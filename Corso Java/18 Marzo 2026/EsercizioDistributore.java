import java.util.Scanner;

public class EsercizioDistributore {
    public static void main(String[] args) {

        Scanner scObj = new Scanner(System.in);
        double creditoDisponibile = 10.00;
        boolean continua = true;

        do {
            // mostra menu e credito disponibile
            System.out.println("MENU");
            System.out.println("Credito disponibile euro " + creditoDisponibile);
            System.out.println("1. Caffè - euro 1.50");
            System.out.println("2. Cappuccino - euro 2.00");
            System.out.println("3. Tè - euro 1.00");
            System.out.println("4. Acqua - euro 0.50");
            System.out.println("5. Esci");
            System.out.print("Scelta: ");
            int scelta = scObj.nextInt();

            switch (scelta) {
                case 1:
                    if (creditoDisponibile >= 1.50) {
                        creditoDisponibile -= 1.50;
                        System.out.println("Caffè erogato!");
                    } else {
                        System.out.println("Credito insufficiente!");
                    }
                    break;
                case 2:
                    if (creditoDisponibile >= 2.00) {
                        creditoDisponibile -= 2.00;
                        System.out.println("Cappuccino erogato!");
                    } else {
                        System.out.println("Credito insufficiente!");
                    }
                    break;
                case 3:
                    if (creditoDisponibile >= 1.00) {
                        creditoDisponibile -= 1.00;
                        System.out.println("Tè erogato!");
                    } else {
                        System.out.println("Credito insufficiente!");
                    }
                    break;
                case 4:
                    if (creditoDisponibile >= 0.50) {
                        creditoDisponibile -= 0.50;
                        System.out.println("Acqua erogata!");
                    } else {
                        System.out.println("Credito insufficiente!");
                    }
                    break;
                case 5:
                    System.out.println("Arrivederci!");
                    continua = false;
                    break;
                default:
                    System.out.println("Scelta non valida");
                    break;
            }

            // controllo credito esaurito
            if (creditoDisponibile < 0.50 && continua) {
                System.out.println("Credito esaurito, arrivederci!");
                continua = false;
            }

        } while (continua);

        System.out.printf("Credito rimanente: euro " + creditoDisponibile);
        scObj.close();
    }
}