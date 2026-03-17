import java.util.Scanner;

public class Esercizio3 {
    public static void main(String[] args) {
        // chiedere eta utente
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci un numero intero: ");
        int eta = scanner.nextInt();
        if (eta >= 18) {
            System.out.println("Sei maggiorenne");
        } else if (eta < 18) {
            System.out.println("Sei minorenne");
        } else {
            System.out.println("Inserisci un valore valido");
        }
        scanner.close();
    }
}
