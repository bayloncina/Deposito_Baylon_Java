import java.util.Scanner;

public class EsercizioVoti {
    public static void main(String[] args) {

        Scanner scObj = new Scanner(System.in);

        System.out.println("Quanti voti vuoi inserire?");
        int numeroVoti = scObj.nextInt();
        int votoValido = 0;

        while (numeroVoti <= 0) {
            System.out.println("Inserisci un valore superiore a 0");
            numeroVoti = scObj.nextInt();
        }

        for (int i = 1; i <= numeroVoti; i++) {
            System.out.println("Inserisci il voto");
            int votoInserito = scObj.nextInt();
            // controlla se il voto è nel range valido
            if ((votoInserito >= 0 && votoInserito <= 30)) {
                votoValido++;// incrementa i voti validi
            } else {
                System.out.println("Voto non valido");
            }
            // valuta il voto
            if (votoInserito >= 24) {
                System.out.println("Ottimo");
            } else if (votoInserito >= 18) {
                System.out.println("Sufficiente");
            } else {
                System.out.println("Insufficiente");
            }

        }
        // stampa il totale dei voti validi
        System.out.println("Voti validi inseriti: " + votoValido);

    }
}
