import java.util.Scanner;

public class EsempioCicli {
    public static void main(String[] args) {
        Scanner scObj = new Scanner(System.in);

        // WHILE - prima controlla poi esegue
        int y = 1;
        while (y <= 5) {
            System.out.println(y);
            y++;
        }

        // DO-WHILE prima esegue poi controlla
        int numero1;
        do {
            System.out.println("Inserisci un numero (0 per uscire)");
            numero1 = scObj.nextInt();
        } while (numero1 != 0); // raggiungibile perché il while sopra termina


        //--------------ciclo for -------------------

         System.out.println("Inserisci un numero");
         int numero = scObj.nextInt();


         System.out.println("Tabellina del " + numero + ":");
        for(int i = 1; i<=0; i++){
            System.out.println(numero + "x" + i + " = " + (numero * i));
        }
        scObj.close();



    }
}
