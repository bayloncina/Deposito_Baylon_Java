import java.util.ArrayList;
import java.util.Scanner;

public class Zoo {

    // liste separate per tipo di animale
    private ArrayList<Cane> cani = new ArrayList<>();
    private ArrayList<Gatto> gatti = new ArrayList<>();

    // aggiunge un cane alla lista
    public void aggiungiCane(Cane cane) {
        cani.add(cane);
    }

    // aggiunge un gatto alla lista
    public void aggiungiGatto(Gatto gatto) {
        gatti.add(gatto);
    }

    // stampa tutte le liste con nome, età e verso di ogni animale
    public void stampaAnimali() {
        System.out.println("\n===== ZOO =====");

        System.out.println("\n-- CANI --");
        for (Cane c : cani) {
            System.out.print(c.toString() + " | Verso: ");
            c.faiVerso(); // chiama il faiVerso() di Cane → "Bau Bau"
        }

        System.out.println("\n-- GATTI --");
        for (Gatto g : gatti) {
            System.out.print(g.toString() + " | Verso: ");
            g.faiVerso(); // chiama il faiVerso() di Gatto → "Miao"
        }
    }

    public static void main(String[] args) {

        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        // array di tipo Animale che contiene sia Cane che Gatto
        // possibile grazie al polimorfismo: Cane e Gatto estendono Animale
        Animale[] animali = {
                new Cane("Rex", 3),
                new Gatto("Wanda", 2),
                new Cane("Bobby", 5),
                new Gatto("Luna", 1)
        };

        // scorro l'array e stampo nome, età e verso di ogni animale
        // Java capisce automaticamente quale faiVerso() chiamare
        // in base al tipo reale dell'oggetto (Cane o Gatto)
        System.out.println("ARRAY ANIMALI");
        for (Animale a : animali) {
            System.out.print(a.toString() + " | Verso: ");
            a.faiVerso();
        }

        Zoo zoo = new Zoo();
        mostraMenu(zoo, scannerStr, scannerInt);
    }

    private static void mostraMenu(Zoo zoo, Scanner scannerStr, Scanner scannerInt) {
        int scelta = 0;

        while (scelta != 4) {
            System.out.println("\nMENU ZOO");
            System.out.println("1. Aggiungi cane");
            System.out.println("2. Aggiungi gatto");
            System.out.println("3. Mostra tutti gli animali");
            System.out.println("4. Esci");
            System.out.print("Scegli: ");

            scelta = scannerInt.nextInt();

            switch (scelta) {
                case 1:
                    // leggo nome ed età e creo un nuovo oggetto Cane
                    System.out.print("Nome del cane: ");
                    String nomeCane = scannerStr.nextLine();
                    System.out.print("Età: ");
                    int etaCane = scannerInt.nextInt();
                    zoo.aggiungiCane(new Cane(nomeCane, etaCane));
                    break;

                case 2:
                    // leggo nome ed età e creo un nuovo oggetto Gatto
                    System.out.print("Nome del gatto: ");
                    String nomeGatto = scannerStr.nextLine();
                    System.out.print("Età: ");
                    int etaGatto = scannerInt.nextInt();
                    zoo.aggiungiGatto(new Gatto(nomeGatto, etaGatto));
                    break;

                case 3:
                    // mostra tutte le liste dello zoo
                    zoo.stampaAnimali();
                    break;

                case 4:
                    System.out.println("Arrivederci!");
                    break;

                default:
                    System.out.println("Opzione non valida.");
            }
        }
    }
}