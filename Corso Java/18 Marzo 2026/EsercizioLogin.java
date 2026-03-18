import java.util.Scanner;

public class EsercizioLogin {
    public static void main(String[] args) {

        Scanner scObj = new Scanner(System.in);

        String password = "java123";
        int tentativi = 3;
        boolean accessoConsentito = false;

        // WHILE - gestisce i tentativi di inserimento password
        while (tentativi > 0 && !accessoConsentito) {
            System.out.println("Inserisci password (" + tentativi + " tentativi rimasti): ");
            String passwordInserita = scObj.nextLine();

            if (passwordInserita.equals(password)) {
                // password corretta
                accessoConsentito = true;
            } else {
                // password errata, decrementa i tentativi
                tentativi--;
                System.out.println("Password errata!");
            }
        }

        // DO-WHILE chiede conferma solo se l'accesso è stato consentito
        if (accessoConsentito) {
            String scelta;
            do {
                System.out.println("Vuoi procedere all'accesso? (s/n): ");
                scelta = scObj.nextLine().toLowerCase();
            } while (!scelta.equals("s") && !scelta.equals("n")); // ripete finché input non è valido

            if (scelta.equals("s")) {
                // utente conferma
                System.out.println("Accesso al sistema effettuato!");
            } else {
                // utente annulla
                System.out.println("Accesso annullato!");
            }
        }

        // tentativi esauriti senza accesso riuscito
        if (tentativi == 0) {
            System.out.println("Accesso bloccato!");
        }

        scObj.close();
    }
}