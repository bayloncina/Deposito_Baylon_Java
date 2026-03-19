import java.util.Scanner;

public class Esercizio4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci anno: ");
        float anno = scanner.nextInt();
        // divisibile per 4, non divisibile per 100, divisibile per 400
        if ((anno % 4 == 0 && anno % 100 != 0) || anno % 400 == 0) {
            System.out.println("Anno bisestile");
        } else {
            System.out.println("Anno non bisestile");
        }
        scanner.close();
    }

}
