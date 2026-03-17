import java.util.Scanner;

public class EserciziCondizioni2 {
    public static void main(String[] args) {

        // ----Esercizio1 -----
        // Chiedere utente di inserire username
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserisci Username ");
        // se username è vuoto
        String username = sc.nextLine();
        if (username.isEmpty()) {
            System.out.println("Username non valido!!!!");
            // se usernmane è minore di 5 caratteri username troppo corto
        } else if (username.length() < 5) {
            System.out.println("Username troppo corto");
            // se username è maggiore o uguale a 5 caratteri username ok
        } else if (username.length() >= 5) {
            System.out.println("Username valido");
        }
        // ----Esercizio 2 -----
        // inserire credenziali
        System.out.println("Inserisci Username ");
        String username2 = sc.nextLine();
        System.out.println("Inserisci password ");
        String password = sc.nextLine();
        // confrontare username e password se uguali accesso consentito, se diverse
        // accesso negato
        // se una delle due è diversa credenziali errate
        if (username2.equals("admin") & password.equals("1234")) {
            System.out.println("accesso consentito");
        } else if (username2.equals("admin") || password.equals("1234")) {
            System.out.println("Credenziali errate - hai usato username: " + username + " e password: " + password + "Ricontrolla per l'accesso");
        } else {
            System.out.println("Accesso negato");
        }

        // ---------------Esercizio3 --------------------------

        // chiedere codice sconto e totle spesa
        System.out.println("Inserisci il codice sconto");
        String codice = sc.nextLine().toUpperCase();

        System.out.println("Inserisci l'importo della spesa");
        String importoStringa = sc.nextLine();
        double importo = Double.parseDouble(importoStringa); // casting String / double

        double sconto = 0;
        // check per il codice sconto
        boolean codiceValido = true;

        if (codice.equals("SCONTO10")) {
            sconto = 10;
        } else if (codice.equals("SCONTO20")) {
            sconto = 20;
        } else if (importo > 100) {
                sconto = 30;
        } else {
            System.out.println("Codice non valido");
            codiceValido = false;
        }

        if (codiceValido) {
            double risparmio = importo * sconto / 100;
            double totale = importo - risparmio;
            System.out.println("Sconto applicato: " + sconto);
            System.out.println("Hai risparmiato: " + risparmio);
            System.out.println("Totale scontato: " + totale);
        }

        sc.close();

    }

}
