import java.util.Scanner;

public class EsercizioParoleChiaveModificatoriFunzioni {

    public static void main(String[] args) {

        Scanner scObj = new Scanner(System.in);
        boolean continua = true;

        do {
            System.out.println("MENU");
            System.out.println("1. Addizione");
            System.out.println("2. Moltiplicazione interi");
            System.out.println("3. Moltiplicazione double");
            System.out.println("4. Somma naturali (ricorsione)");
            System.out.println("5. Passaggio per valore");
            System.out.println("6. Passaggio per riferimento");
            System.out.println("7. Esci");
            System.out.print("Scelta: ");
            int scelta = scObj.nextInt();

            switch (scelta) {
                case 1:
                    // chiede due interi e stampa la somma
                    System.out.println("Inserisci il primo numero: ");
                    int a = scObj.nextInt();
                    System.out.println("Inserisci il secondo numero: ");
                    int b = scObj.nextInt();
                    System.out.println("Risultato: " + addizione(a, b));
                    break;

                case 2:
                    // overloading di due interi
                    System.out.println("Inserisci il primo numero: ");
                    int n1 = scObj.nextInt();
                    System.out.println("Inserisci il secondo numero: ");
                    int n2 = scObj.nextInt();
                    System.out.println("Risultato: " + multiply(n1, n2));
                    break;

                case 3:
                    // overloading di tre double
                    System.out.println("Inserisci il primo numero: ");
                    double d1 = scObj.nextDouble();
                    System.out.println("Inserisci il secondo numero: ");
                    double d2 = scObj.nextDouble();
                    System.out.println("Inserisci il terzo numero: ");
                    double d3 = scObj.nextDouble();
                    System.out.println("Risultato: " + multiply(d1, d2, d3));
                    break;

                case 4:
                    // ricorsione chiede n e calcola la somma dei primi n numeri naturali
                    System.out.println("Inserisci n: ");
                    int n = scObj.nextInt();
                    System.out.println("Somma naturali di " + n + ": " + sommaNaturali(n));
                    break;

                case 5:
                    // dimostra che il primitivo non cambia fuori dal metodo
                    System.out.println("Inserisci un numero: ");
                    int numero = scObj.nextInt();
                    System.out.println("Prima: " + numero);
                    modificaValore(numero);
                    System.out.println("Dopo: " + numero); // non cambia
                    break;

                case 6:
                    // dimostra che l'array cambia fuori dal metodo
                    int[] array = {1, 2, 3};
                    System.out.println("Prima: " + array[0]);
                    modificaArray(array);
                    System.out.println("Dopo: " + array[0]); // cambia
                    break;

                case 7:
                    System.out.println("Arrivederci!");
                    continua = false;
                    break;

                default:
                    System.out.println("Scelta non valida!");
                    break;
            }

        } while (continua);

        scObj.close();
    }

    static int addizione(int num1, int num2) {
        return num1 + num2;
    }

    static int multiply(int num1, int num2) {
        return num1 * num2;
    }

    static double multiply(double num1, double num2, double num3) {
        return num1 * num2 * num3;
    }

    static int sommaNaturali(int n) {
        if (n == 1) {
            return 1; // caso base
        }
        // il risultato è n più la somma di tutti i numeri prima di me
        return n + sommaNaturali(n - 1); // caso ricorsivo
    }

    // la modifica non è visibile all'esterno passaggio per valore
    static void modificaValore(int x) {
        x = x + 10;
    }

    // la modifica è visibile all'esterno passaggio per riferimento
    static void modificaArray(int[] arr) {
        arr[0] = 99;
    }
}