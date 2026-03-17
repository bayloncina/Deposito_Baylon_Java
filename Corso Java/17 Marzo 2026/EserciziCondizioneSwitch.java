import java.util.Scanner;

public class EserciziCondizioneSwitch {
    public static void main(String[] args) {
        // mostrare un menu all'utente
        // chiedere all'utente di inserire un numero
        Scanner scObj = new Scanner(System.in);
        System.out.println("MENU");
        System.out.println("Digita 1 per Visualizzare il profilo");
        System.out.println("Digita 2 per Modifica nome");
        System.out.println("Digita 3 per il Logout");
        System.out.print("Scelta: ");
        int scelta = scObj.nextInt();
        // gestire la scelta con lo switch
        
        switch (scelta) {
            case 1:
                System.out.println("Profilo utente visualizzato");
                break;
            case 2:
                System.out.println("Inserisci nuovo nome");
                String nome = scObj.nextLine();
                System.out.println("Nome aggiornato in: " + nome);
                break;
            case 3:
                System.out.println("Logout effettuato");
                break;
            default:
                System.out.println("Scelta non valida");
                break;
        }
        scObj.close();
    }
}
