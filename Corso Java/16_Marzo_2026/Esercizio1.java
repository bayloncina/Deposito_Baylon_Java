import java.util.Scanner;

public class Esercizio1 {
    public static void main(String[] args) {
        // chiedi utente di inserire un numero intero

        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci un numero intero: ");
        int numeroIntero = scanner.nextInt();

        // verifica se il numero è maggiore di zero e stampa "il numero è positivo"
        if (numeroIntero > 0) {
            System.out.println("il numero è positivo");
        }
        // verifica se il numero è minore di zero e stampa "il numero è negativo"
        else if (numeroIntero < 0) {
            System.out.println("il numero è negativo");
        } else {
            // se è uguale a 0 stampa "i numeri sono uguali"
            System.out.println("i numeri sono uguali");
        }
        scanner.close();
    }
}
