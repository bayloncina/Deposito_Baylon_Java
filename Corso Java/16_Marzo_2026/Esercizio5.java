import java.util.Scanner;

public class Esercizio5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci nome: ");
        String nome = scanner.next();
        System.out.println("Inserisci cognome: ");
        String cognome = scanner.next();
        System.out.println("Inserisci password: ");
        String password = scanner.next();

        System.out.println("Nome: " + nome);
        System.out.println("Cognome: " + cognome);
        System.out.println("Password: " + password);

        System.out.println("Inserisci 1 per cambiare il nome, 2 per il cognome e 3 per la password");
        int scelta = scanner.nextInt();

        switch (scelta) {
            case 1:
                System.out.println("Inserisci nuovo nome: ");
                nome = scanner.next();
                break;
            case 2:
                System.out.println("Inserisci nuovo cognome: ");
                cognome = scanner.next();
                break;
            case 3:
                System.out.println("Inserisci nuova password: ");
                password = scanner.next();
                break;
            default:
                System.out.println("Scelta non valida");
        }
        System.out.println("Nome: " + nome);
        System.out.println("Cognome: " + cognome);
        System.out.println("Password: " + password);
        scanner.close();
    }
}
