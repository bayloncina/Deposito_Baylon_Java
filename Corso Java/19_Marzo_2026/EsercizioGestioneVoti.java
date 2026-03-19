import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EsercizioGestioneVoti {



    public static void main(String[] args) {

        Scanner scObj = new Scanner(System.in);
        ArrayList<Integer> voti = new ArrayList<>();

        // inserisce i voti finché l'utente non scrive 0
        System.out.println("Inserisci i voti, scrivi 0 per termnare: ");
        int voto = scObj.nextInt();

        //controllo se l'utente ha terminato di inserire i voti
        while (voto != 0) {
            voti.add(voto);
            System.out.println("Inserisci i voti, scrivi 0 per termnare: ");
            voto = scObj.nextInt();
        }

        // chiama il metodo menu
        mostraMenu(scObj, voti);

        scObj.close();
    }

    // METODI

    // riceve scanner e voti come parametri
    static void mostraMenu(Scanner scObj, ArrayList<Integer> voti) {
        boolean continua = true;

        do {
            System.out.println("MENU");
            System.out.println("1. Calcola media");
            System.out.println("2. Mostra voto più alto");
            System.out.println("3. Mostra voto più basso");
            System.out.println("4. Verifica promozione");
            System.out.println("5. Esci");
            System.out.print("Scelta: ");
            int scelta = scObj.nextInt();

            switch (scelta) {
                case 1:
                    System.out.println("Media: " + calcolaMedia(voti));
                    break;
                case 2:
                    System.out.println("Voto più alto: " + votoMassimo(voti));
                    break;
                case 3:
                    System.out.println("Voto più basso: " + votoMinimo(voti));
                    break;
                case 4:
                    verificaPromozione(voti);
                    break;
                case 5:
                    System.out.println("Arrivederci!");
                    continua = false;
                    break;
                default:
                    System.out.println("Scelta non valida!");
                    break;
            }
        } while (continua);
    }

    static double calcolaMedia(ArrayList<Integer> voti) {
        int somma = 0;
        for (int i = 0; i < voti.size(); i++) {
            somma += voti.get(i);
        }
        // casting a double per ottenere la media con decimali
        // senza casting int/int restituirebbe un intero troncando i decimali
        return (double) somma / voti.size();
    }

    static int votoMassimo(ArrayList<Integer> voti) {
        return Collections.max(voti);
    }

    static int votoMinimo(ArrayList<Integer> voti) {
        return Collections.min(voti);
    }

    static void verificaPromozione(ArrayList<Integer> voti) {
        double media = calcolaMedia(voti);
        if (media >= 6) {
            System.out.println("Promosso! Media: " + media);
        } else {
            System.out.println("Bocciato! Media: " + media);
        }
    }
}