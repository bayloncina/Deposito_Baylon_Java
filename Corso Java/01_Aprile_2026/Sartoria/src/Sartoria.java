import java.util.ArrayList;
import java.util.Scanner;

public class Sartoria {


    //creo arrayList di capi e finiture
    private ArrayList<CapoPrincipale> capi = new ArrayList<>();
    private ArrayList<ComponenteFinitura> finiture = new ArrayList<>();

    private Scanner scannerInt = new Scanner(System.in);
    private Scanner scannerStr = new Scanner(System.in);

    //faccio scelgiere all'utente quale tipo oggetto vuole creare 
    public void aggiungiCapo() {
        System.out.println("Tipo capo: 1. Giacca  2. Pantalone  3. Gilet");
        int tipo = scannerInt.nextInt();
        scannerInt.nextLine();

        // campi comuni a tutti i capi
        System.out.print("Codice: ");
        String codice = scannerStr.nextLine();
        System.out.print("Nome: ");
        String nome = scannerStr.nextLine();
        System.out.print("Tessuto: ");
        String tessuto = scannerStr.nextLine();
        System.out.print("Colore: ");
        String colore = scannerStr.nextLine();
        System.out.print("Taglia: ");
        String taglia = scannerStr.nextLine().toUpperCase();
        System.out.print("Prezzo: ");
        double prezzo = scannerInt.nextDouble();
        scannerInt.nextLine();

        // campo specifico in base al tipo scelto
        switch (tipo) {
            case 1 -> {
                System.out.print("Numero bottoni: ");
                int bottoni = scannerInt.nextInt();
                capi.add(new Giacca(codice, nome, tessuto, colore, taglia, prezzo, bottoni));
            }
            case 2 -> {
                System.out.print("Tipo taglio: ");
                String taglio = scannerStr.nextLine();
                capi.add(new Pantalone(codice, nome, tessuto, colore, taglia, prezzo, taglio));
            }
            case 3 -> {
                System.out.print("Rever presente? (s/n): ");
                String risposta = scannerStr.nextLine();
                boolean rever;
                if (risposta.equalsIgnoreCase("s")) {
                    rever = true;
                } else {
                    rever = false;
                }
                capi.add(new Gilet(codice, nome, tessuto, colore, taglia, prezzo, rever));
            }
            default -> System.out.println("Tipo non valido.");
        }
        System.out.println("Capo aggiunto!");
    }

    public void modificaCapo() {
        System.out.print("Codice capo da modificare: ");
        String codice = scannerStr.nextLine();

        for (CapoPrincipale c : capi) {
            if (c.getCodice().equals(codice)) {

                // campi comuni
                System.out.print("Nuovo nome: ");
                c.setNome(scannerStr.nextLine());
                System.out.print("Nuovo tessuto: ");
                c.setTessuto(scannerStr.nextLine());
                System.out.print("Nuovo colore: ");
                c.setColore(scannerStr.nextLine());
                System.out.print("Nuova taglia: ");
                c.setTaglia(scannerStr.nextLine().toUpperCase());
                System.out.print("Nuovo prezzo: ");
                c.setPrezzo(scannerInt.nextDouble());
                scannerInt.nextLine();

                // campo specifico in base al tipo reale dell'oggetto
                if (c instanceof Giacca g) {
                    System.out.print("Nuovo numero bottoni: ");
                    g.setNumeroBottoni(scannerInt.nextInt());
                } else if (c instanceof Pantalone p) {
                    System.out.print("Nuovo tipo taglio: ");
                    p.setTipoTaglio(scannerStr.nextLine());
                } else if (c instanceof Gilet v) {
                    System.out.print("Rever presente? (s/n): ");
                    String risposta = scannerStr.nextLine();
                    boolean rever;
                    if (risposta.equalsIgnoreCase("s")) {
                        rever = true;
                    } else {
                        rever = false;
                    }
                    v.setReverPresente(rever);
                }

                System.out.println("Capo modificato: " + codice);
                return;
            }
        }
        System.out.println("Capo non trovato: " + codice);
    }

    public void rimuoviCapo() {

         System.out.print("Codice capo da rimuovere: ");
         String codice = scannerStr.nextLine();
        boolean rimosso = false;

        // scorre la lista cercando il capo con il codice corrispondente
        for (CapoPrincipale c : capi) {
            if (c.getCodice().equals(codice)) {
                capi.remove(c);
                rimosso = true;
                break; // esce dal for appena trovato e rimosso
            }
        }
        if (rimosso) {
            System.out.println("Capo rimosso: " + codice);
        } else {
            System.out.println("Capo non trovato: " + codice);
        }
    }

    public void visualizzaCapi() {
        if (capi.isEmpty()) {
            System.out.println("Nessun capo presente.");
            return;
        }
        System.out.println("\n======= CAPI PRINCIPALI =======");
        for (CapoPrincipale c : capi) {
            c.mostraDettagli();
            System.out.println();
        }
    }

    // ===================== FINITURE =====================
//faccio scelgiere all'utente quale tipo oggetto vuole creare 
    public void aggiungiFinitura() {
        System.out.println("Tipo finitura: 1. Cravatta  2. Papillon  3. Pochette");
        int tipo = scannerInt.nextInt();
        scannerInt.nextLine();

        // campi comuni a tutte le finiture
        System.out.print("Codice: ");
        String codice = scannerStr.nextLine();
        System.out.print("Nome: ");
        String nome = scannerStr.nextLine();
        System.out.print("Materiale: ");
        String materiale = scannerStr.nextLine();
        System.out.print("Colore: ");
        String colore = scannerStr.nextLine();
        System.out.print("Prezzo: ");
        double prezzo = scannerInt.nextDouble();
        scannerInt.nextLine();

        // campo specifico in base al tipo scelto
        switch (tipo) {
            case 1 -> {
                System.out.print("Larghezza (cm): ");
                double larghezza = scannerInt.nextDouble();
                finiture.add(new Cravatta(codice, nome, materiale, colore, prezzo, larghezza));
            }
            case 2 -> {
                System.out.print("Tipo chiusura: ");
                String chiusura = scannerStr.nextLine();
                finiture.add(new Papillon(codice, nome, materiale, colore, prezzo, chiusura));
            }
            case 3 -> {
                System.out.print("Piega decorativa: ");
                String piega = scannerStr.nextLine();
                finiture.add(new Pochette(codice, nome, materiale, colore, prezzo, piega));
            }
            default -> System.out.println("Tipo non valido.");
        }
        System.out.println("Finitura aggiunta!");
    }

    public void modificaFinitura() {
        System.out.print("Codice finitura da modificare: ");
        String codice = scannerStr.nextLine();

        for (ComponenteFinitura f : finiture) {
            if (f.getCodice().equals(codice)) {

                // campi comuni
                System.out.print("Nuovo nome: ");
                f.setNome(scannerStr.nextLine());
                System.out.print("Nuovo materiale: ");
                f.setMateriale(scannerStr.nextLine());
                System.out.print("Nuovo colore: ");
                f.setColore(scannerStr.nextLine());
                System.out.print("Nuovo prezzo: ");
                f.setPrezzo(scannerInt.nextDouble());
                scannerInt.nextLine();

                // campo specifico in base al tipo reale dell'oggetto
                if (f instanceof Cravatta cr) {
                    System.out.print("Nuova larghezza (cm): ");
                    cr.setLarghezza(scannerInt.nextDouble());
                } else if (f instanceof Papillon pa) {
                    System.out.print("Nuovo tipo chiusura: ");
                    pa.setTipoChiusura(scannerStr.nextLine());
                } else if (f instanceof Pochette po) {
                    System.out.print("Nuova piega decorativa: ");
                    po.setPiegaDecorativa(scannerStr.nextLine());
                }

                System.out.println("Finitura modificata: " + codice);
                return;
            }
        }
        System.out.println("Finitura non trovata: " + codice);
    }

    public void rimuoviFinitura() {
        System.out.print("Codice finitura da rimuovere: ");
        String codice = scannerStr.nextLine();
        boolean rimosso = false;

        for (ComponenteFinitura f : finiture) {
            if (f.getCodice().equals(codice)) {
                finiture.remove(f);
                rimosso = true;
                break; // esce dal for appena trovato e rimosso
            }
            if (rimosso) {
                System.out.println("Capo rimosso: " + codice);
            } else {
                System.out.println("Capo non trovato: " + codice);
            }
        }
    }

    public void visualizzaFiniture() {
        if (finiture.isEmpty()) {
            System.out.println("Nessuna finitura presente.");
            return;
        }
        System.out.println("\n======= COMPONENTI FINITURA =======");
        for (ComponenteFinitura f : finiture) {
            f.mostraDettagli();
            System.out.println();
        }
    }

}
