import java.util.Scanner;

public class Esercizio2 {

    public static void main(String[] args) {
        // richiedere all'utente di inserire 2 numeri
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci un numero intero: ");
        int num1 = scanner.nextInt();
        System.out.println("Inserisci un numero intero: ");
        int num2 = scanner.nextInt();
        // se il primo numero è maggiore "il primo numero è maggiore
        if (num1 > num2) {
            System.out.println(num1 + "è  maggiore di" + num2);
        }
        // se il secondo numero è maggiore "il secondo numero è maggiore
        else if (num2 > num1) {
            System.out.println(num2 + "è  maggiore di" + num1);
        } else {
            // se sono uguali " sono uguali "
            System.out.println("i numeri sono uguali");
        }
        scanner.close();
    }
}
