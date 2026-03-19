import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EsercizioGestioneVotiExtra {

    public static void main(String[] args) {

        Scanner scObj = new Scanner(System.in);
        ArrayList<Integer> voti = new ArrayList<>();

        // chiama il metodo menu
        mostraMenu(scObj, voti);

        scObj.close();
    }

    // metodo per inserire i voti - riutilizzato anche dall'insegnante
    static void inserisciVoti(Scanner scObj, ArrayList<Integer> voti) {
        System.out.println("Inserisci i voti, scrivi 0 per terminare: ");
        int voto = scObj.nextInt();
        while (voto != 0) {
            voti.add(voto);
            System.out.println("Inserisci i voti, scrivi 0 per terminare: ");
            voto = scObj.nextInt();
        }
    }

    static void mostraMenu(Scanner scObj, ArrayList<Integer> voti) {
        boolean continua = true;

        do {
            System.out.println("MENU");
            System.out.println("1. Calcola media");
            System.out.println("2. Mostra voto più alto");
            System.out.println("3. Mostra voto più basso");
            System.out.println("4. Verifica promozione");
            System.out.println("5. Assegna voto (solo insegnante)");
            System.out.println("6. Esci");
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
                    // controlla se è un insegnante e poi chiama inserisciVoti
                    assegnaVoto(scObj, voti);
                    break;
                case 6:
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

    // controlla se è insegnante e richiama inserisciVoti
    static void assegnaVoto(Scanner scObj, ArrayList<Integer> voti) {

        String password = "12345";
        System.out.println("Inserisci password");
        String passwordInserita = scObj.next();

        if (passwordInserita.equals(password)) {
            // richiama il metodo 
            inserisciVoti(scObj, voti);
        } else {
            System.out.println("Non sei autorizzato ad assegnare voti!");
        }
    }
}